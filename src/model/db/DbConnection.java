package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private final static String URL = "jdbc:postgresql://localhost:5432/bank_db";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "12345";

    public static Connection getInstance() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
