package me.matt11matthew.atherialrunes.database;

import java.sql.Connection;
import java.sql.DriverManager;


import me.matt11matthew.atherialrunes.Constants;


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
				con = DriverManager.getConnection(Constants.SQL_URL, Constants.SQL_USER, Constants.SQL_PASSWORD);
			}
		} catch(Exception e) {
			System.out.println("Couldn't connect to the database!");
		}
		return con;
	}
	
}