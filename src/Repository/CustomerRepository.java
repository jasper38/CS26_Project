package Repository;

import DTO.LogInRequestDTO;
import Utility.IMBankConnectionManager;
import Model.Customer;

import java.sql.*;

public class CustomerRepository {
    public int createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customers (Username, Password, Email, Person_ID) VALUES (?, ?, ?, ?)";

        try (Connection conn = IMBankConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getEmail());
            ps.setInt(4, customer.getPersonID());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return getLastInsertedID(ps);
            } else {
                throw new SQLException("Failed to insert Customer; no rows affected.");
            }
        }
    }

    private int getLastInsertedID(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No ID obtained.");
            }
        }
    }

    public LogInRequestDTO findCustomerByUsername(LogInRequestDTO logInRequestDTO) throws SQLException {
        String sql = "SELECT Username, Password FROM Customers WHERE Username = ?";
        try (Connection conn = IMBankConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, logInRequestDTO.getUsername());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    return new LogInRequestDTO(username, password);
                } else {
                    return null;
                }
            }
        }
    }

    public String updateCustomerUsernameAndEmail(String sessionUsername, String username, String email) throws SQLException {
        String sql = "UPDATE Customers SET Username = ?, Email = ? WHERE Username = ?";
        try (Connection conn = IMBankConnectionManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, sessionUsername);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return getUpdatedUsername(username);
            } else {
                throw new SQLException("Failed to update Customer; no rows affected.");
            }
        }
    }

    public String getUpdatedUsername(String username) throws SQLException {
        String sql = "SELECT Username FROM Customers WHERE Username = ?";
        try (Connection conn = IMBankConnectionManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Username");
            } else {
                throw new SQLException("No username obtained.");
            }
        }
    }
}
