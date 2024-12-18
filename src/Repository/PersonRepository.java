package Repository;

import DTO.UserProfileDTO;
import Utility.IMBankConnectionManager;
import Model.Person;

import java.sql.*;

public class PersonRepository {
    public int savePerson(Person person) throws SQLException {
        String sql = "INSERT INTO Person (first_name, last_name, age, sex, birthdate, phone_no, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = IMBankConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setInt(3, person.getAge());
            stmt.setString(4, person.getSex());
            stmt.setDate(5, person.getBirthdate());
            stmt.setString(6, person.getPhoneNum());
            stmt.setString(7, person.getAddress());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("ID not found");
                    }
                }
            } else {
                throw new SQLException("Insert failed, no rows affected");
            }
        } catch (SQLException se) {
            return -1;
        }
    }

    public UserProfileDTO getUserProfile(int bankAccount_NumberID) throws SQLException {
        String sql = "SELECT CONCAT(p.First_Name, ' ', p.Last_Name) AS fullName, " +
                     "p.Birthdate AS birthdate, " +
                     "YEAR(CURDATE()) - YEAR(p.Birthdate) AS age, " +
                     "p.Sex AS sex, " +
                     "c.Username AS username, " +
                     "p.Phone_No AS contactNumber, " +
                     "c.Email AS email, " +
                     "b.Bank_Account_Number_ID AS bankAccountNumberID " +
                     "FROM Person p " +
                     "JOIN Customers c ON p.Person_ID = c.Person_ID " +
                     "JOIN Bank_Accounts b ON c.Customer_ID = b.Customer_ID " +
                     "WHERE b.Bank_Account_Number_ID = ?";
        try (Connection conn = IMBankConnectionManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bankAccount_NumberID);

            UserProfileDTO userProfileDTO = null;
            ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    userProfileDTO = new UserProfileDTO();
                    userProfileDTO.setFullName(rs.getString("fullName"));
                    userProfileDTO.setBirthDate(rs.getString("birthdate"));
                    userProfileDTO.setAge(rs.getInt("age"));
                    userProfileDTO.setSex(rs.getString("sex"));
                    userProfileDTO.setUsername(rs.getString("username"));
                    userProfileDTO.setContactNumber(rs.getString("contactNumber"));
                    userProfileDTO.setEmail(rs.getString("email"));
                    userProfileDTO.setBankAccountNumberID(rs.getInt("bankAccountNumberID"));
                }
            return userProfileDTO;
        }
    }

    public String getPersonFirstName(String username) throws SQLException {
        String sql = "SELECT p.First_Name " +
                     "FROM Person p " +
                     "JOIN Customers c ON c.Person_ID = p.Person_ID " +
                     "WHERE username = ?";
        try (Connection conn = IMBankConnectionManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("First_Name");
            } else {
                throw new SQLException("First name not found");
            }
        }
    }

    public int updateContactNo(String username, String contactNum) throws SQLException {
        String sql = "UPDATE Person p " +
                     "JOIN Customers c ON p.Person_ID = c.Person_ID " +
                     "SET p.Phone_No = ? " +
                     "WHERE c.Username = ?";
        try (Connection conn = IMBankConnectionManager.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, contactNum);
            ps.setString(2, username);

            return ps.executeUpdate();
        }
    }
}
