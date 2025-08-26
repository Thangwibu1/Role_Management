package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=UserRoleDB;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASS = "123456";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fail to load JDBC driver");
        }
        return conn;
    }
}
