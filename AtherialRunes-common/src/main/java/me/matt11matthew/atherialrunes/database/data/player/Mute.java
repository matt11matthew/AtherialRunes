package me.matt11matthew.atherialrunes.database.data.player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.database.table.tables.player.MuteDataTable;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;

public class Mute {
	
	public static HashMap<String, Mute> players = new HashMap<String, Mute>();
	
	private String reason;
	private String unmuteReason;
	private long time;
	private String by;
	private String uuid;
	private String name;
	
	public static void save(Mute mute) {
		new MuteDataTable().updateValue("UPDATE `" + new MuteDataTable().getName() + "` SET `time`='" + mute.getTime() + "' WHERE `uuid`='" + UUIDData.getUUID(mute.getName()) + "';");
		new MuteDataTable().updateValue("UPDATE `" + new MuteDataTable().getName() + "` SET `reason`='" + mute.getReason() + "' WHERE `uuid`='" + UUIDData.getUUID(mute.getName()) + "';");
		new MuteDataTable().updateValue("UPDATE `" + new MuteDataTable().getName() + "` SET `unmutereason`='" + mute.getUnmuteReason() + "' WHERE `uuid`='" + UUIDData.getUUID(mute.getName()) + "';");
		new MuteDataTable().updateValue("UPDATE `" + new MuteDataTable().getName() + "` SET `mutedby`='" + mute.getBy() + "' WHERE `uuid`='" + UUIDData.getUUID(mute.getName()) + "';");
		if (players.containsKey(mute.getUUID())) {
			players.remove(mute.getUUID());
		}
	}
	
	public static Mute getMute(String name) {
		String uuid = UUIDData.getUUID(name);
		if (players.containsKey(uuid)) {
			return players.get(uuid);
		}
		Mute mute = new Mute(name);
		PreparedStatement pst = null;
		try {
			AtherialPlayer player = new AtherialPlayer(name);
			pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("SELECT * FROM `" + new MuteDataTable().getName() + "` WHERE `uuid` ='" + player.getUUID() + "'");
			pst.execute();
			ResultSet rs = pst.getResultSet();
			if (!rs.next()) {
				new MuteDataTable().insert("INSERT INTO " + new MuteDataTable().getName()
						+ "(uuid, ign, time, reason, unmutereason, mutedby) "
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
						+ "', reason='" + "none"
						+ "', unmutereason='" + "none"
						+ "', mutedby='" + "none" + "'");
				mute.setBy("none");
				mute.setReason("none");
				mute.setUnmuteReason("none");
				mute.setTime(0);
				players.put(uuid, mute);
			} else {
				long time = rs.getLong("time");
				String reason = rs.getString("reason");
				String unMuteReason = rs.getString("unmutereason");
				String by = rs.getString("mutedby");
				mute.setBy(by);
				mute.setReason(reason);
				mute.setUnmuteReason(unMuteReason);
				mute.setTime(time);
				players.put(uuid, mute);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players.get(uuid);
	}
	
	public Mute(String name) {
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

	public String getUnmuteReason() {
		return unmuteReason;
	}

	public void setUnmuteReason(String unmuteReason) {
		this.unmuteReason = unmuteReason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public boolean isMuted() {
		return ((time > 0) || (time == -1)) ? true : false;
	}
	
	public boolean isPerm() {
		return (time == -1) ? true : false;
	}
	
	public boolean canUnmute() {
		if (isPerm()) {
			return false;
		}
		return (System.currentTimeMillis() >= time) ? true : false;
	}
}
