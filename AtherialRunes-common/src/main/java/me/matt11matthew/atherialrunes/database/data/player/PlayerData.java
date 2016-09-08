package me.matt11matthew.atherialrunes.database.data.player;

import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.database.data.Data;
import me.matt11matthew.atherialrunes.database.table.tables.player.PlayerdataTable;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PlayerData implements Data {
	
	private String name;
	private String uuid;
	
	public static HashMap<String, AtherialPlayer> players = new HashMap<String, AtherialPlayer>();
	
	public PlayerData(String name) {
		this.name = name;
		this.uuid = getUUID(name);
	}
	
	public String getUUID(String name) {
		return UUIDData.uuids.get(name);
	}

	public void save(String uuid) {
		AtherialPlayer player = players.get(uuid);
		if (player.isNewPlayer()) {
			new PlayerdataTable().insert("INSERT INTO " + new PlayerdataTable().getName()
					+ "(uuid, ign, rank, channel, combat, level, vanish, exp, skillpoints, gold, silver, copper, nick, admin, notoriety, shard) "
					+ "VALUES("
					+ "'" + player.getUUID()
					+ "', '" + player.getName()
					+ "', '"+ player.getRank()
					+ "', '"+ player.getChatChannel()
					+ "', '"+ player.getCombatTime()
					+ "', '"+ player.getLevel()
					+ "', '"+ player.isVanished()
					+ "', '"+ player.getEXP()
					+ "', '"+ player.getSkillPoints()
					+ "', '"+ player.getGold()
					+ "', '"+ player.getSilver()
					+ "', '"+ player.getCopper()
					+ "', '"+ player.getNick()
					+ "', '"+ player.isInAdminMode()
					+ "', '"+ player.getNotoriety()
					+ "', '" + player.getShard() + "') "
					+ "ON DUPLICATE KEY UPDATE "
					+ "uuid='" + player.getUUID()
					+ "', ign='" + player.getName()
					+ "', rank='" + player.getRank()
					+ "', channel='" + player.getChatChannel()
					+ "', combat='" + player.getCombatTime()
					+ "', level='" + player.getLevel()
					+ "', vanish='" + player.isVanished()
					+ "', exp='" + player.getEXP()
					+ "', skillpoints='" + player.getSkillPoints()
					+ "', gold='" + player.getGold()
					+ "', silver='" + player.getSilver()
					+ "', copper='" + player.getCopper()
					+ "', nick='" + player.getNick()
					+ "', admin='" + player.isInAdminMode()
					+ "', notoriety='" + player.getNotoriety()
					+ "', shard='" + player.getShard() + "'");
		} else {
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `rank`='" + player.getRank() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `shard`='" + player.getShard() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `channel`='" + player.getChatChannel() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `combat`='" + player.getCombatTime() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `vanish`='" + player.isVanished() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `level`='" + player.getLevel() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `exp`='" + player.getEXP() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `skillpoints`='" + player.getSkillPoints() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `gold`='" + player.getGold() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `silver`='" + player.getSilver() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `copper`='" + player.getCopper() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `nick`='" + player.getNick() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `admin`='" + player.isInAdminMode() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerdataTable().updateValue("UPDATE `" + new PlayerdataTable().getName() + "` SET `notoriety`='" + player.getNotoriety() + "' WHERE `uuid`='" + getUUID(name) + "';");
		}
		players.remove(uuid);
	}

	public void update() {
		PreparedStatement pst = null;
		try {
			AtherialPlayer player = new AtherialPlayer(name);
			pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("SELECT * FROM `" + new PlayerdataTable().getName() + "` WHERE `uuid` ='" + player.getUUID() + "'");
			pst.execute();
			ResultSet rs = pst.getResultSet();
			if (!rs.next()) {
				player.setRank("DEFAULT");
				player.setShard("?");
				player.setSkillPoints(0);
				player.setChatChannel(1);
				player.setEXP(0);
				player.setLevel(1);
				player.setVanished(false);
				player.setNewPlayer(true);
				player.setCombatTime(0);
				player.setGold(0);
				player.setCopper(0);
				player.setSilver(0);
				player.setNick(player.getName());
				player.setAdminMode(false);
			} else {
				player.setNewPlayer(false);
				String rank = rs.getString("rank");
				String shard = rs.getString("shard");
				int channel = rs.getInt("channel");
				int combatTime = rs.getInt("combat");
				long exp = rs.getLong("exp");
				int level = rs.getInt("level");
				int skillpoints = rs.getInt("skillpoints");
				int gold = rs.getInt("gold");
				int silver = rs.getInt("silver");
				int copper = rs.getInt("copper");
				boolean vanished = Boolean.parseBoolean(rs.getString("vanish"));
				player.setChatChannel(channel);
				player.setSkillPoints(skillpoints);
				player.setRank(rank);
				player.setShard(shard);
				player.setEXP(exp);
				player.setLevel(level);
				player.setVanished(vanished);
				player.setCopper(copper);
				player.setSilver(silver);
				player.setGold(gold);
				player.setCombatTime(combatTime);
				player.setNick(rs.getString("nick"));
				player.setAdminMode(Boolean.parseBoolean(rs.getString("admin")));
				
			}
			players.put(uuid, player);	
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return;
	}
	
	public String getUUID() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public static AtherialPlayer getAtherialPlayer(String name) {
		if (players.containsKey(UUIDData.getUUID(name))) {
			return players.get(UUIDData.getUUID(name));
		} else {
			new PlayerData(name).update();
			AtherialPlayer player = players.get(UUIDData.getUUID(name));
			return player;
		}
	}

}
