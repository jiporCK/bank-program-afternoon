package model.db;

import util.LoadConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getInstance() {
        LoadConfig.loadProperties();
        try {
            return DriverManager.getConnection(
                    LoadConfig.properties.getProperty("DB_URL"),
                    LoadConfig.properties.getProperty("DB_USERNAME"),
                    LoadConfig.properties.getProperty("DB_PASSWORD")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
