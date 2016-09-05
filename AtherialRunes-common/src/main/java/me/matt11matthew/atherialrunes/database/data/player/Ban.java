package me.matt11matthew.atherialrunes.database.data.player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.database.table.tables.player.BanDataTable;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;

public class Ban {
	
	public static HashMap<String, Ban> players = new HashMap<String, Ban>();
	
	private String reason;
	private String unbanReason;
	private long time;
	private String by;
	private String uuid;
	private String name;
	
	public static void save(Ban ban) {
		new BanDataTable().updateValue("UPDATE `" + new BanDataTable().getName() + "` SET `time`='" + ban.getTime() + "' WHERE `uuid`='" + UUIDData.getUUID(ban.getName()) + "';");
		new BanDataTable().updateValue("UPDATE `" + new BanDataTable().getName() + "` SET `banreason`='" + ban.getReason() + "' WHERE `uuid`='" + UUIDData.getUUID(ban.getName()) + "';");
		new BanDataTable().updateValue("UPDATE `" + new BanDataTable().getName() + "` SET `unbanreason`='" + ban.getUnbanReason() + "' WHERE `uuid`='" + UUIDData.getUUID(ban.getName()) + "';");
		new BanDataTable().updateValue("UPDATE `" + new BanDataTable().getName() + "` SET `bannedby`='" + ban.getBy() + "' WHERE `uuid`='" + UUIDData.getUUID(ban.getName()) + "';");
		if (players.containsKey(ban.getUUID())) {
			players.remove(ban.getUUID());
		}
	}
	
	public static Ban getBan(String name) {
		String uuid = UUIDData.getUUID(name);
		if (players.containsKey(uuid)) {
			return players.get(uuid);
		}
		Ban ban = new Ban(name);
		PreparedStatement pst = null;
		try {
			AtherialPlayer player = new AtherialPlayer(name);
			pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("SELECT * FROM `" + new BanDataTable().getName() + "` WHERE `uuid` ='" + player.getUUID() + "'");
			pst.execute();
			ResultSet rs = pst.getResultSet();
			if (!rs.next()) {
				new BanDataTable().insert("INSERT INTO " + new BanDataTable().getName()
						+ "(uuid, ign, time, banreason, unbanreason, bannedby) "
						+ "VALUES("
						+ "'" + player.getUUID()
						+ "', '" + player.getName()
						+ "', '" + 0
						+ "', '" + "none"
						+ "', '" + "none"
						+ "', '" + "none" + "') "
						+ "ON DUPLICATE KEY UPDATE "
						+ "uuid='" + player.getUUID()
						+ "', ign='" + player.getName()
						+ "', time='" + 0
						+ "', banreason='" + "none"
						+ "', unbanreason='" + "none"
						+ "', bannedby='" + "none" + "'");
				ban.setBy("none");
				ban.setReason("none");
				ban.setUnbanReason("none");
				ban.setTime(0);
				players.put(uuid, ban);
			} else {
				long time = rs.getLong("time");
				String banreason = rs.getString("banreason");
				String unbanReason = rs.getString("unbanreason");
				String bannedBy = rs.getString("bannedby");
				ban.setBy(bannedBy);
				ban.setReason(banreason);
				ban.setUnbanReason(unbanReason);
				ban.setTime(time);
				players.put(uuid, ban);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players.get(uuid);
	}
	
	public Ban(String name) {
		this.name = name;
		this.uuid = UUIDData.getUUID(name);
	}

	public String getName() {
		return name;
	}

	public String getUUID() {
		return uuid;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getUnbanReason() {
		return unbanReason;
	}

	public void setUnbanReason(String unbanReason) {
		this.unbanReason = unbanReason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public boolean isBanned() {
		return ((time > 0) || (time == -1)) ? true : false;
	}
	
	public boolean isPerm() {
		return (time == -1) ? true : false;
	}
	
	public boolean canUnban() {
		if (isPerm()) {
			return false;
		}
		return (System.currentTimeMillis() >= time) ? true : false;
	}
}
