package Service;

import DTO.LogInRequestDTO;
import DTO.LogInResult;
import DTO.RegistrationRequestDTO;
import Model.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface IMBankService {
    boolean registerPerson(RegistrationRequestDTO registrationRequestDTO) throws SQLException;
    LogInResult verifyLogIn(LogInRequestDTO logInRequest) throws SQLException;
    float getBankAccountBalance() throws SQLException;
    boolean verifyBankAccountCredentials(int bankAccountNumberID, int cardPIN) throws SQLException;
    int createTransaction(String transactionType, String selectedBank, int amount) throws SQLException;
    List<Transaction> getTransactions() throws SQLException;
    int generateOTP();
}
