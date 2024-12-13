package DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IMBankConnectionManager {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    //private static final String URL = "jdbc:mysql://localhost:8080/bankingsystem";
    private static final String URL = "jdbc:mysql://localhost:3306/bankingsystem";
    private static final String USERNAME = "root";
    //private static final String PASSWORD = "Nening123!";
    private static final String PASSWORD = "jasper123";
    private static Connection con;

    //MGA KULANG
    // password dapat at least 8 characters long (document filter)
    // age and email validation
    // change password
    //remove edit btn
    //welcome name of person
    // dapat wala ni disable and deposit or withdraw button pag close sa window
    //bank acc id no must be 8 characters long , same sa card pin na 4 char lang
    // adjust and column header sa tables fixed set reziable to false
    //write data sa receipt
    //dli ma spam and submit sa popupwin2
    //refactor ang code sa views gamit ang factory

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            try {
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Can't get connection.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        }
        return con;
    }
}
