package Repository;

import DatabaseConnectionManager.IMBankConnectionManager;
import Model.CardInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardInfoRepository {
    public void createCardInfo(CardInfo cardInfo) throws SQLException {
        String sql = "INSERT INTO Card_Info (Card_PIN, Bank_Account_Number_ID) VALUES (?, ?)";
        try (Connection conn = IMBankConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cardInfo.getCardPIN());
            ps.setInt(2, cardInfo.getBankAccountNumberID());
            ps.executeUpdate();
        }
    }
}
