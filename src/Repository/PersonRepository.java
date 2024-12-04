package Repository;

import DatabaseConnectionManager.IMBankConnectionManager;
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
}
