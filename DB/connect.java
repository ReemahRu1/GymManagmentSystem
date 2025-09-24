/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagementsystem.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASSWORD = "1234";

    private static connect instance;
    private Connection connection;

    private connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    // Singleton pattern: Get the single instance of the connect class
    public static connect getInstance() {
        if (instance == null) {
            instance = new connect();
        }
        return instance;
    }

    // Get the connection (check if it's closed, and reconnect if needed)
    public Connection getConnection() {
        try {
            // If the connection is null or closed, create a new one
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to reconnect to the database.");
        }
        return connection;
    }

    
}

