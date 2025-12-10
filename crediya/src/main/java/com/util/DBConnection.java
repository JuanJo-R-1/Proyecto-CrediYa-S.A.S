package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null | connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/";
            String user = "";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    public static void close() {
        try {
            if (connection )
        } catch (Exception e) {
            
        }
    }
}
