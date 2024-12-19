package Repository;

import Utility.IMBankConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlagRepository {
    public int setFlagToTrueIfExceeded(int sessionBankAccountNumberID) throws SQLException {
        String sql = "UPDATE Charges_If_Exceeding_Maintaining_Balance ci " +
                     "JOIN Bank_Accounts ba ON ci.Bank_Account_Number_ID = ba.Bank_Account_Number_ID " +
                     "SET hasExceeded = 'true' " +
                     "WHERE ci.Bank_Account_Number_ID = ?";
        try(Connection conn = IMBankConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sessionBankAccountNumberID);
            return ps.executeUpdate();
        }
    }

    public int setFlagToFalseIfExceeded(int sessionBankAccountNumberID) throws SQLException {
        String sql = "UPDATE Bank_Accounts SET Bank_Account_Balance = 5000 + Bank_Account_Balance WHERE Bank_Account_Number_ID = ?";
        try(Connection conn = IMBankConnectionManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sessionBankAccountNumberID);
            return ps.executeUpdate();
        }
    }

    public String getHasExceededFlag(int sessionBankAccountNumberID) throws SQLException {
        String sql = "SELECT hasExceeded FROM Charges_If_Exceeding_Maintaining_Balance WHERE Bank_Account_Number_ID = ?";
        try(Connection conn = IMBankConnectionManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sessionBankAccountNumberID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("hasExceeded");
            } else {
                return null;
            }
        }
    }
}
