package me.matt11matthew.atherialrunes.tools;

import com.mysql.jdbc.PreparedStatement;
import me.matt11matthew.atherialrunes.tools.sql.ConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Utils {

    public static String getString(String table, String value1, String value2) {
        PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("SELECT * FROM "+table+" WHERE `" + value1.split("=")[0].trim() + "` ='" + value1.split("=")[1].trim() + "'");
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

    public static int getInt(String table, String value1, String value2) {
        PreparedStatement pst = null;
        try {
            pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("SELECT * FROM "+table+" WHERE `" + value1.split("=")[0].trim() + "` ='" + value1.split("=")[1].trim() + "'");
            pst.execute();
            ResultSet rs = pst.getResultSet();

            if (!(rs.next())) {
                return 0;
            }
            return rs.getInt(value2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
