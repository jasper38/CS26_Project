package Repository;

import DatabaseConnectionManager.IMBankConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AffiliatedBankRepository {

    public int getAffiliatedBankID(String bankName) throws SQLException {
        String sql = "SELECT Bank_ID FROM Affiliated_Banks WHERE Bank_Name = ?";
        int bankID = 0;
        try(Connection conn = IMBankConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, bankName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                bankID = rs.getInt("Bank_ID");
            } else {
                throw new SQLException("ID not found");
            }
        }
        return bankID;
    }
}
