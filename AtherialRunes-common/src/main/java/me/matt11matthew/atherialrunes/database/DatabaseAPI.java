package me.matt11matthew.atherialrunes.database;

import me.matt11matthew.atherialrunes.database.data.player.PlayerData;
import me.matt11matthew.atherialrunes.database.data.player.UUIDData;
import me.matt11matthew.atherialrunes.database.table.TableManager;

public class DatabaseAPI {
	
	private static DatabaseAPI instance;
	
	public static void loadDatabaseAPI() {
		new DatabaseAPI().enable();
		TableManager.loadAllTables();
	}
	
	public void enable() {
		instance = this;
		ConnectionPool.getConnection();
	}
	
	public static DatabaseAPI getInstance() {
		return instance;
	}
	
	public UUIDData getUUIDData() {
		return new UUIDData();
	}
	
	public PlayerData getPlayerData(String name) {
		return new PlayerData(name);
	}
}
