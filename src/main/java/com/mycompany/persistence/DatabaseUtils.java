package com.mycompany.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    static Connection db;
    public static boolean connected = false;

    public static boolean connect() {
        String url = "jdbc:postgresql://localhost/java_radio";

        // try {
		// 	Class.forName("org.postgresql.Driver");
		// 	System.out.println("Congrats - Seems your PostgreSQL JDBC Driver Registered!");
		// } catch (ClassNotFoundException e) {
		// 	System.out.println("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
        //     e.printStackTrace();
        //     return connected;
		// }

        try {
            db = DriverManager.getConnection(url);
            System.out.println("Connection successful.");
            connected = true;
            return connected;
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
            return connected;
        }
    }

}