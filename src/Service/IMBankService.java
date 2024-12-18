package Service;

import DTO.*;

import java.sql.SQLException;
import java.util.List;

public interface IMBankService {
    boolean registerPerson(RegistrationRequestDTO registrationRequestDTO) throws SQLException;
    LogInResult verifyLogIn(LogInRequestDTO logInRequest) throws SQLException;
    String getName() throws SQLException;
    float getBankAccountBalance() throws SQLException;
    boolean verifyBankAccountCredentials(int bankAccountNumberID, int cardPIN) throws SQLException;
    boolean verifyNumberOfTransactions() throws SQLException;
    int createTransaction(String transactionType, String selectedBank, int amount) throws SQLException;
    List<TransactionHistoryDTO> getTransactions() throws SQLException;
    void deleteTransactionsByIds(List<Integer> ids) throws SQLException;
    UserProfileDTO getUserProfile() throws SQLException;
    int updateUserProfile(String username, String contactNum, String email) throws SQLException;
    boolean updatePassword(String newPassword) throws SQLException;
    int getTransactionID() throws SQLException;
    int cancelPendingTransaction() throws SQLException;
    void logoutUserSession();
    int generateOTP();
}
