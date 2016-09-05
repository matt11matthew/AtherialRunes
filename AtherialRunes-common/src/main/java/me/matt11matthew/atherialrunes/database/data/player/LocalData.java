package me.matt11matthew.atherialrunes.database.data.player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.database.data.Data;
import me.matt11matthew.atherialrunes.database.table.tables.player.PlayerLocalDataTable;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;
import me.matt11matthew.atherialrunes.player.LocalPlayer;

public class LocalData implements Data {

	public void save(Player player) {
		AtherialPlayer a = PlayerData.getAtherialPlayer(player.getName());
		String inventory = "wip";
		if (a.isNewPlayer()) {
			new PlayerLocalDataTable().insert("INSERT INTO " + new PlayerLocalDataTable().getName()
					+ "(uuid, ign, level, food, location, hp, maxhp, inventory) "
					+ "VALUES("
					+ "'" + a.getUUID()
					+ "', '" + a.getName()
					+ "', '"+ player.getLevel()
					+ "', '"+ player.getFoodLevel()
					+ "', '"+ parseLocation(player.getLocation())
					+ "', '"+ (int) player.getHealth()
					+ "', '"+ (int) player.getMaxHealth()
					+ "', '" + inventory + "') "
					+ "ON DUPLICATE KEY UPDATE "
					+ "uuid='" + a.getUUID()
					+ "', ign='" + player.getName()
					+ "', level='" + player.getLevel()
					+ "', food='" + player.getFoodLevel()
					+ "', location='" + parseLocation(player.getLocation())
					+ "', hp='" + (int) player.getHealth()
					+ "', maxhp='" + (int) player.getMaxHealth()
					+ "', inventory='" + inventory + "'");
		} else {
			String name = player.getName();
			new PlayerLocalDataTable().updateValue("UPDATE `" + new PlayerLocalDataTable().getName() + "` SET `level`='" + player.getLevel() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerLocalDataTable().updateValue("UPDATE `" + new PlayerLocalDataTable().getName() + "` SET `food`='" + player.getFoodLevel() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerLocalDataTable().updateValue("UPDATE `" + new PlayerLocalDataTable().getName() + "` SET `location`='" + parseLocation(player.getLocation()) + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerLocalDataTable().updateValue("UPDATE `" + new PlayerLocalDataTable().getName() + "` SET `hp`='" + (int) player.getHealth() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerLocalDataTable().updateValue("UPDATE `" + new PlayerLocalDataTable().getName() + "` SET `maxhp`='" + (int) player.getMaxHealth() + "' WHERE `uuid`='" + getUUID(name) + "';");
			new PlayerLocalDataTable().updateValue("UPDATE `" + new PlayerLocalDataTable().getName() + "` SET `inventory`='" + inventory + "' WHERE `uuid`='" + getUUID(name) + "';");
		}
		
	}

	public String parseLocation(Location l) {
		return ("world:" + l.getWorld().getName() + "," + "x:" + (int) l.getX() + "," + "y:" + (int) l.getY() + "," + "z:" + (int) l.getZ() + "," + "yaw:" + l.getYaw() + "," + "pitch:" + l.getPitch() + ",");
	}

	private String getUUID(String name) {
		return UUIDData.getUUID(name);
	}

	public void update(Player player) {
		PreparedStatement pst = null;
		try {
			LocalPlayer l = new LocalPlayer(player);
			pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("SELECT * FROM `" + new PlayerLocalDataTable().getName() + "` WHERE `uuid` ='" + player.getUniqueId().toString() + "'");
			pst.execute();
			ResultSet rs = pst.getResultSet();
			if (!rs.next()) {
				l.setFoodLevel(player.getFoodLevel());
				l.setLevel(player.getLevel());
				l.setHP((int) player.getHealth());
				l.setMaxHP((int) player.getMaxHealth());
				l.setInventory("wip");
				l.setLocation(parseLocation(player.getLocation()));
			} else {
				l.setFoodLevel(rs.getInt("food"));
				l.setLevel(rs.getInt("level"));
				l.setHP(rs.getInt("hp"));
				l.setMaxHP(rs.getInt("maxhp"));
				l.setInventory(rs.getString("inventory"));
				l.setLocation(rs.getString("location"));
			}
			l.load(player);
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
}
