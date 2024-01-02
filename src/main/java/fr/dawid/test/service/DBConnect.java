package fr.dawid.test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static final String url = "jdbc:mariadb://localhost:3307/db_instant-faking-java";

    private static final String user = "root";

    private static final String pwd = "";

    private static Connection connection;

    private DBConnect() { }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url , user, pwd);
            } catch (SQLException e) {
                System.out.println("Something goes wrong while try to access DB : " + e.getMessage());
                connection = null;
            }
        }
        return connection;
    }
}
