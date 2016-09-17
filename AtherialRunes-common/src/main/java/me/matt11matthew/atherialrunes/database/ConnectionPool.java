package me.matt11matthew.atherialrunes.database;

import me.matt11matthew.atherialrunes.Constants;

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
				if (Constants.LOCALHOST) {
					con = DriverManager.getConnection(Constants.LOCALHOST_SQL_URL, Constants.LOCALHOST_SQL_USER, Constants.LOCALHOST_SQL_PASSWORD);
				} else {
					con = DriverManager.getConnection(Constants.SQL_URL, Constants.SQL_USER, Constants.SQL_PASSWORD);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Couldn't connect to the database!");
		}
		return con;
	}
	
}