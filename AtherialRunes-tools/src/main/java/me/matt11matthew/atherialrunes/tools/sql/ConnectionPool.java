package me.matt11matthew.atherialrunes.tools.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {

    private static Connection con;

    public static boolean refresh = false;

    public static Connection getConnection() {
        try {
            if ((refresh) || (con == null) || (con.isClosed())) {
                refresh = false;
                if (con != null) {
                    con.close();
                }
                con = DriverManager.getConnection("jdbc:mysql://192.99.37.67:3306/atherialrunes", "matt11matthew", "pass");
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't connect to the database!");
        }
        return con;
    }
}