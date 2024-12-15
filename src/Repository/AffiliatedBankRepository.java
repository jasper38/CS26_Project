package Repository;

import Utility.IMBankConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AffiliatedBankRepository {

    public int getAffiliatedBankID(String bankName) throws SQLException {
        String sql = "SELECT Bank_ID FROM Affiliated_Banks WHERE Bank_Name = ?";
        try(Connection conn = IMBankConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, bankName);
            ResultSet rs = ps.executeQuery();

            int bankID = 0;
            if(rs.next()){
                bankID = rs.getInt("Bank_ID");
            } else {
                throw new SQLException("ID not found");
            }
            return bankID;
        }
    }

    public Double getBankCharge(int bankID) throws SQLException {
        String sql = "SELECT Charge FROM Affiliated_Banks WHERE Bank_ID = ?";
        try(Connection conn = IMBankConnectionManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, bankID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getDouble("Charge");
            } else {
                throw new SQLException("ID not found");
            }
        }
    }
}
