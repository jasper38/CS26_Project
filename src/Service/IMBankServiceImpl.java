package Service;

import DTO.*;
import Model.*;
import Repository.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IMBankServiceImpl implements IMBankService {

    private final AffiliatedBankRepository affiliatedBankRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CardInfoRepository cardInfoRepository;
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;

    // session variables for seamless retrieval of data from db
    private String sessionUsername = "";
    private int bankAccountNumberID = 0;
    private int customerID = 0;

    public IMBankServiceImpl(AffiliatedBankRepository affiliatedBankRepository,
                             BankAccountRepository bankAccountRepository, CardInfoRepository cardInfoRepository,
                             CustomerRepository customerRepository, PersonRepository personRepository,
                             TransactionRepository transactionRepository) {
        super();
        this.affiliatedBankRepository = affiliatedBankRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.cardInfoRepository = cardInfoRepository;
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
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

        System.out.print(personID+ "" + customerID + "" + bankAccountNumberID);
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
        bankAccountNumberID = bankAccountRepository.getBankAccountNumberID(sessionUsername);
        customerID = customerRepository.getCustomerID(sessionUsername);
        return new LogInResult(true, "Login successful");
    }

    @Override
    public float getBankAccountBalance() throws SQLException {
        return bankAccountRepository.getBankAccountBalance(bankAccountNumberID);
    }

    @Override
    public boolean verifyBankAccountCredentials(int bankAccountNumberID, int cardPIN) throws SQLException {
        CardInfo bankAccountCredentials = cardInfoRepository.getCardInfo(bankAccountNumberID, cardPIN);

        if(bankAccountCredentials == null) { return false; }
        return (bankAccountCredentials.getBankAccountNumberID() == bankAccountNumberID &&
                bankAccountCredentials.getCardPIN() == cardPIN);
    }

    @Override
    public int createTransaction(String transactionType ,String selectedBank, int amount) throws SQLException {
        Transaction transactionRequest = new Transaction();
        transactionRequest.setBankAccountNumberID(bankAccountNumberID);
        int bankID = affiliatedBankRepository.getAffiliatedBankID(selectedBank);
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
            throw new SQLException("Transaction could not be created");
        }
    }

    @Override
    public List<TransactionHistoryDTO> getTransactions() throws SQLException{
        List<TransactionHistoryDTO> transactions = transactionRepository.getAllTransactions(bankAccountNumberID);
        if(transactions == null) {
            throw new SQLException("Transaction could not be retrieved");
        }
        return transactions;
    }

    @Override
    public int generateOTP() {
        return (int) (Math.random() * 900000) + 100000;
    }
}
