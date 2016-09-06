package me.matt11matthew.atherialrunes.database.table.tables.player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.matt11matthew.atherialrunes.database.table.ConnectionPool;
import me.matt11matthew.atherialrunes.database.table.Table;

public class UUIDTable extends Table {

	@Override
	public String getName() {
		return "uuid";
	}

	@Override
	public void createTable() {
		create("CREATE TABLE IF NOT EXISTS `" + getName() + "` (`ign` VARCHAR(50) NOT NULL, `uuid` varchar(100) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
	}

	private void save(String uuid, String name) {
		PreparedStatement pst = null;
		try {
			pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("SELECT * FROM `" + getName() + "` WHERE `ign` ='" + name + "'");
			pst.execute();
			ResultSet rs = pst.getResultSet();
			if (!rs.next()) {
				insert("INSERT INTO " + getName()
				+ "(ign, uuid) "
				+ "VALUES("
				+ "'" + name
				+ "', '" + uuid + "') "
				+ "ON DUPLICATE KEY UPDATE "
				+ "ign='" + name
				+ "', uuid='" + uuid + "'");
			} else {
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getUUID(String name) {
		try {
			return new UUIDTable().getString("ign=" + name, "uuid");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}