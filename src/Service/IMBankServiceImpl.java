package Service;

import DTO.*;
import Model.*;
import Repository.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class IMBankServiceImpl implements IMBankService {

    private final AffiliatedBankRepository affiliatedBankRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CardInfoRepository cardInfoRepository;
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;
    private final FlagRepository flagRepository;

    // session variables for seamless retrieval of data from db
    private String sessionUsername = "";
    private String sessionName = "";
    private int sessionBankAccountNumberID = 0;
    private float currentBalance = 0;

    public IMBankServiceImpl(AffiliatedBankRepository affiliatedBankRepository,
                             BankAccountRepository bankAccountRepository, CardInfoRepository cardInfoRepository,
                             CustomerRepository customerRepository, PersonRepository personRepository,
                             TransactionRepository transactionRepository, FlagRepository flagRepository) {
        super();
        this.affiliatedBankRepository = affiliatedBankRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.cardInfoRepository = cardInfoRepository;
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
        this.flagRepository = flagRepository;
    }

    @Override
    public boolean registerPerson(RegistrationRequestDTO registration) throws SQLException {
        RegistrationRequestFactory registrationRequestFactory = new RegistrationRequestFactory();
        Person person = registrationRequestFactory.savePerson(registration);
        int personID = personRepository.savePerson(person);

        Customer customer = registrationRequestFactory.createCustomer(registration);
        customer.setPersonID(personID);
        int customerID = customerRepository.createCustomer(customer);

        BankAccount bankAccount = registrationRequestFactory.createBankAccount(registration);
        bankAccount.setCustomerID(customerID);
        if (registration.getBankAccountType().equals("Savings")) {
            bankAccount.setBankAccountBalance(500f);
        } else if (registration.getBankAccountType().equals("Checkings")) {
            bankAccount.setBankAccountBalance(10000f);
        }
        int bankAccountNumberID = bankAccountRepository.createBankAccount(bankAccount);

        CardInfo cardInfo = registrationRequestFactory.createCard();
        cardInfo.setCardPIN(1234);
        cardInfo.setBankAccountNumberID(bankAccountNumberID);
        cardInfoRepository.createCardInfo(cardInfo);

        return (personID > -1 && customerID > -1 && bankAccountNumberID > -1);
    }

    @Override
    public LogInResult verifyLogIn(LogInRequestDTO logInRequest) throws SQLException {
        LogInRequestDTO credentials = customerRepository.findCustomerByUsername(logInRequest);

        if (credentials == null) {
            return new LogInResult(false, "User not found");
        }
        if (!credentials.getPassword().equals(logInRequest.getPassword())) {
            return new LogInResult(false, "Invalid username or password");
        }
        sessionUsername = credentials.getUsername();
        sessionBankAccountNumberID = bankAccountRepository.getBankAccountNumberID(sessionUsername);
        sessionName = personRepository.getPersonFirstName(sessionUsername);
        return new LogInResult(true, "Login successful");
    }

    @Override
    public String getName() throws SQLException {
        return sessionName;
    }

    @Override
    public float getBankAccountBalance() throws SQLException {
        currentBalance = bankAccountRepository.getBankAccountBalance(sessionBankAccountNumberID);
        return currentBalance;
    }

    @Override
    public boolean verifyBankAccountCredentials(int bankAccountNumberID, int cardPIN) throws SQLException {
        CardInfo bankAccountCredentials = cardInfoRepository.getCardInfo(bankAccountNumberID, cardPIN);

        if(bankAccountCredentials == null) { return false; }
        return (bankAccountCredentials.getBankAccountNumberID() == sessionBankAccountNumberID &&
                bankAccountCredentials.getCardPIN() == cardPIN);
    }

    @Override
    public boolean verifyNumberOfTransactions() throws SQLException {
        int numOfTransactions = transactionRepository.getNumberOfTransactions(sessionUsername);
        return (numOfTransactions > 0);
    }

    @Override
    public int createTransaction(String transactionType ,String selectedBank, int amount) throws SQLException {

        int bankID = affiliatedBankRepository.getAffiliatedBankID(selectedBank);
        double bankCharge = affiliatedBankRepository.getBankCharge(bankID);

        if((amount + bankCharge) > currentBalance) {
            return 0;
        }

        Transaction transactionRequest = new Transaction();
        transactionRequest.setBankAccountNumberID(sessionBankAccountNumberID);
        transactionRequest.setAffiliatedBankID(bankID);
        transactionRequest.setTransactionType(transactionType);
        transactionRequest.setAmount(amount);
        transactionRequest.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));
        transactionRequest.setRequestStatus("Pending");
        transactionRequest.setOTP(String.valueOf(generateOTP()));

        int rowsAffected = transactionRepository.createTransactionRequest(transactionRequest);
        if(rowsAffected > 0) {
            return Integer.parseInt(transactionRequest.getOTP());
        } else {
            return 0;
        }
    }

    @Override
    public List<TransactionHistoryDTO> getTransactions() throws SQLException{
        List<TransactionHistoryDTO> transactions = transactionRepository.getAllTransactions(sessionBankAccountNumberID);
        if(transactions == null) {
            throw new SQLException("Transaction could not be retrieved");
        }
        return transactions;
    }

    @Override
    public void deleteTransactionsByIds(List<Integer> ids) throws SQLException {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("No IDs provided for deletion.");
        }
        transactionRepository.deleteByIds(ids);
    }

    @Override
    public UserProfileDTO getUserProfile() throws SQLException {
        UserProfileDTO userProfileDTO = personRepository.getUserProfile(sessionBankAccountNumberID);
        if(userProfileDTO == null) {
            throw new SQLException("User profile could not be retrieved");
        }
        return userProfileDTO;
    }

    @Override
    public int updateUserProfile(String username, String contactNum, String email) throws SQLException {
        String updatedUsername = customerRepository.updateCustomerUsernameAndEmail(sessionUsername, username, email);
        int rowsAffected = personRepository.updateContactNo(updatedUsername, contactNum);
        sessionUsername = updatedUsername;
        return rowsAffected;
    }

    @Override
    public boolean updatePassword(String newPassword) throws SQLException {
        int rowsAffected = customerRepository.updateCustomerPassword(sessionUsername, newPassword);
        return rowsAffected != 0;
    }

    @Override
    public int getTransactionID() throws SQLException {
        int transactionID = transactionRepository.getPendingTransactions(sessionBankAccountNumberID);
        if(transactionID > 0) {
            return 1;
        }
        return transactionID;
    }

    @Override
    public int cancelPendingTransaction() throws SQLException {
        return transactionRepository.updatePendingStatusIfDurationExceedsHour(sessionBankAccountNumberID);
    }

    @Override
    public boolean checkIfAmountExceeded(float currentBankAccountBalance, float amountToTransact, String selectedBank) throws SQLException {

        int bankID = affiliatedBankRepository.getAffiliatedBankID(selectedBank);
        double bankCharge = affiliatedBankRepository.getBankCharge(bankID);

        float notExceedingAmount = currentBankAccountBalance - amountToTransact;
        if(notExceedingAmount < 500 && notExceedingAmount >= 0){
            System.out.println("Invoked.3");
            float balanceAfterDeduction = notExceedingAmount - 200;
            if(balanceAfterDeduction >= 0){
                System.out.println("Invoked.4");
                if(balanceAfterDeduction - bankCharge >= 0){
                    System.out.println("Invoked.5");
                    return true;
                } else{
                    System.out.println("Invoked.6");
                    return false;
                }
            } else {
                System.out.println("Invoked.7");
                return false; // less than na siya sa currentBankAccountBalance, meaning negative na dri iyang account balance
            }
        } else {
            System.out.println("Invoked.8");
            return notExceedingAmount - (float) bankCharge > 0;
        }
    }

    @Override
    public boolean updateToTrue() throws SQLException {
        return flagRepository.setFlagToTrueIfExceeded(sessionBankAccountNumberID) > 0;
    }

    @Override
    public String getHasExceededFlag() throws SQLException {
        String flag = flagRepository.getHasExceededFlag(sessionBankAccountNumberID);
        if(flag == null) {
            return "";
        }
        return flag;
    }

    @Override
    public int updateBankAccountBalance(float amount) throws SQLException {
        return bankAccountRepository.updateBankAccountBalance(sessionBankAccountNumberID, amount);
    }

    @Override
    public void logoutUserSession() {
        if(!sessionUsername.isEmpty() && sessionBankAccountNumberID != 0){
            sessionUsername = null;
            sessionBankAccountNumberID = 0;
        }
    }

    @Override
    public int generateOTP() {
        return (int) (Math.random() * 900000) + 100000;
    }
}
