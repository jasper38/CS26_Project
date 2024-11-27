package Repository;

import DatabaseConnectionManager.IMBankConnectionManager;
import Model.BankAccount;

import java.sql.*;

public class BankAccountRepository {
    public int createBankAccount(BankAccount bankAccount) throws SQLException {
        String sql = "INSERT INTO Bank_Accounts (Customer_ID, Bank_Account_Balance, Bank_Account_Type, Date_Opened) VALUES (?, ?, ?, NOW())";

        try (Connection conn = IMBankConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, bankAccount.getCustomerID());
            ps.setFloat(2, bankAccount.getBankAccountBalance());
            ps.setString(3, bankAccount.getBankAccountType());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return getLastInsertedID(ps);
            } else {
                throw new SQLException("Failed to register Bank Account; no rows affected.");
            }
        }
    }

    private int getLastInsertedID(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No ID obtained for Bank Account.");
            }
        }
    }

    public float getBankAccountBalance(int bankAccountNumberID) throws SQLException {
        String sql = "SELECT Bank_Account_Balance "
                + "FROM Bank_Accounts "
                + "WHERE Bank_Account_Number_ID = ?";

        float bankAccountBalance = 0;
        DatabaseMetaData BankConnectionManager;
        try (Connection conn = IMBankConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bankAccountNumberID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bankAccountBalance = rs.getFloat("Bank_Account_Balance");
            }
            return bankAccountBalance;
        }
    }

    public int getBankAccountNumberID(int customerID) throws SQLException {
        String sql = "SELECT Bank_Account_Number_ID "
                + "FROM Bank_Accounts "
                + "WHERE Customer_ID = ?";
        int bankAccountID = 0;
        try (Connection conn = IMBankConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bankAccountID = rs.getInt("Bank_Account_Number_ID");
            }
            return bankAccountID;
        }
    }
}
