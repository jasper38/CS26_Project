package Repository;

import Controller.IMBankController;
import DatabaseConnectionManager.IMBankConnectionManager;
import Model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    public int createTransactionRequest(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO Transaction " +
                     "(Bank_Account_Number_ID, Bank_ID, Transaction_Type, Amount, Transaction_DateTime, Request_Status, OTP) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = IMBankConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transaction.getBankAccountNumberID());
            ps.setInt(2, transaction.getAffiliatedBankID());
            ps.setString(3, transaction.getTransactionType());
            ps.setDouble(4, transaction.getAmount());
            ps.setDate(5, transaction.getTransactionDateTime());
            ps.setString(6, transaction.getRequestStatus());
            ps.setString(7, transaction.getOTP());

            return ps.executeUpdate();
        }
    }

    public List<Transaction> getAllTransactions(int bankAccountNumberID) throws SQLException {
        String sql = "SELECT * FROM Transaction WHERE Bank_Account_Number_ID = ?";
        try(Connection conn = IMBankConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bankAccountNumberID);
            ResultSet rs = ps.executeQuery();
            List<Transaction> transactions = new ArrayList<>();
            while(rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(rs.getInt("Transaction_ID"));
                transaction.setBankAccountNumberID(rs.getInt("Bank_Account_Number_ID"));
                transaction.setAffiliatedBankID(rs.getInt("Bank_ID"));
                transaction.setTransactionType(rs.getString("Transaction_Type"));
                transaction.setAmount(rs.getInt("Amount"));
                transaction.setTransactionDateTime(rs.getDate("Transaction_DateTime"));
                transaction.setRequestStatus(rs.getString("Request_Status"));
                transaction.setOTP(rs.getString("OTP"));
                transactions.add(transaction);
            }
            return transactions;
        }
    }
}
