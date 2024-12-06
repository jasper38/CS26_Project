package Service;

import DTO.*;

import java.sql.SQLException;
import java.util.List;

public interface IMBankService {
    boolean registerPerson(RegistrationRequestDTO registrationRequestDTO) throws SQLException;
    LogInResult verifyLogIn(LogInRequestDTO logInRequest) throws SQLException;
    float getBankAccountBalance() throws SQLException;
    boolean verifyBankAccountCredentials(int bankAccountNumberID, int cardPIN) throws SQLException;
    int createTransaction(String transactionType, String selectedBank, int amount) throws SQLException;
    List<TransactionHistoryDTO> getTransactions() throws SQLException;
    UserProfileDTO getUserProfile() throws SQLException;
    int getTransactionID() throws SQLException;
    int cancelPendingTransaction() throws SQLException;
    int generateOTP();
}
