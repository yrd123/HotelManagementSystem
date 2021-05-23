package com.company;

import java.sql.*;

public class PostgreSQLConnection {
    private static Connection conn;

    public static Connection getConnection() throws Exception{
        String url = "jdbc:postgresql://localhost:5432/HotelManagementSystem";
        String hostUsername = "postgres";
        String hostPassword = "postgres";

        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, hostUsername, hostPassword);
        //s = conn.createStatement();
        return conn;
    }
}
