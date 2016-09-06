package me.matt11matthew.atherialrunes.database.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class Table {

	public abstract String getName();
	
	public abstract void createTable();
	
	public Connection getConnection() {
		return ConnectionPool.getConnection();
	}
	
	public void insert(String value) {
		PreparedStatement pst = null;
		try {
			pst = (PreparedStatement) getConnection().prepareStatement(value); 
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateValue(String value) {
		PreparedStatement pst = null;
		try {
			pst = (PreparedStatement) getConnection().prepareStatement(value); 
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create(String values) {
		PreparedStatement pst = null;
		try {
			pst = getConnection().prepareStatement(values);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public String getString(String value1, String value2) {
		PreparedStatement pst = null;
		try {
			pst = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM " + getName() + " WHERE `" + value1.split("=")[0].trim() + "` ='" + value1.split("=")[1].trim() + "'");
			pst.execute();
			ResultSet rs = pst.getResultSet();

			if (!(rs.next())) {
				return null;
			}
			return rs.getString(value2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	} 
	

}
