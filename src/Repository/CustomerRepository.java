package Repository;

import DTO.LogInRequestDTO;
import DatabaseConnectionManager.IMBankConnectionManager;
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
                if (rs.next()) { // Expecting only one result
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    return new LogInRequestDTO(username, password);
                } else {
                    return null; // No match found
                }
            }
        }
    }

    public int getCustomerID(String username) throws SQLException {
        String sql = "SELECT Customer_ID FROM Customers WHERE Username = ?";
        int ID = 0;
        try(Connection conn = IMBankConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                ID = rs.getInt("Customer_ID");
            }
        }
        return ID;
    }
}
