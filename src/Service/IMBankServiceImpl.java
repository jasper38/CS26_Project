package Service;

import DTO.LogInRequestDTO;
import DTO.RegistrationRequestDTO;
import DTO.RegistrationRequestFactory;
import Model.BankAccount;
import Model.CardInfo;
import Model.Customer;
import Model.Person;
import Repository.*;

import java.sql.SQLException;

public class IMBankServiceImpl implements IMBankService {
    private String sessionUsername = "";
    private final AffiliatedBankRepository affiliatedBankRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CardInfoRepository cardInfoRepository;
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;

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

        return (personID > -1 && customerID > -1 && bankAccountNumberID > -1);
    }

    @Override
    public boolean verifyLogIn(LogInRequestDTO logInRequest) throws SQLException, IllegalArgumentException {
        LogInRequestDTO credentials = customerRepository.findCustomerByUsername(logInRequest);
        if (credentials == null)
            throw new IllegalArgumentException("User Not Found");
        else if (!credentials.getUsername().equals(logInRequest.getUsername())
                && !credentials.getPassword().equals(logInRequest.getPassword())) {
            throw new IllegalArgumentException("Invalid Username or Password");
        }
        sessionUsername = credentials.getUsername();
        return true;
    }

    @Override
    public float getBankAccountBalance() throws SQLException {
        int customerID = customerRepository.getCustomerID(sessionUsername);
        int bankAccountNumberID = bankAccountRepository.getBankAccountNumberID(customerID);
        System.out.println(bankAccountNumberID);
        return bankAccountRepository.getBankAccountBalance(bankAccountNumberID);
    }
}
