package Repository;

import DatabaseConnectionManager.IMBankConnectionManager;
import Model.CardInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public CardInfo getCardInfo(int bankAccountNumberID, int cardPIN) throws SQLException {
        String sql = "SELECT ci.Bank_Account_Number_ID, ci.Card_PIN " +
                     "FROM Card_Info ci " +
                     "INNER JOIN Bank_Accounts ba ON ci.Bank_Account_Number_ID = ba.Bank_Account_Number_ID " +
                     "WHERE ci.Bank_Account_Number_ID = ? AND ci.Card_PIN = ?";
        CardInfo cardInfo;
        try (Connection conn = IMBankConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bankAccountNumberID);
            ps.setInt(2, cardPIN);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cardInfo = new CardInfo();
                cardInfo.setBankAccountNumberID(rs.getInt("Bank_Account_Number_ID"));
                cardInfo.setCardPIN(rs.getInt("Card_PIN"));
            } else {
                throw new SQLException("Card not found");
            }
            return cardInfo;
        }
    }
}
