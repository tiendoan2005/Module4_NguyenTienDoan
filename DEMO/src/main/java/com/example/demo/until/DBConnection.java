package com.example.demo.until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/students?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "123456789A";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
