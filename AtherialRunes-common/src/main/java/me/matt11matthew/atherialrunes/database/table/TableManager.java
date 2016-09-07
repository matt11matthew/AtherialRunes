package me.matt11matthew.atherialrunes.database.table;

import me.matt11matthew.atherialrunes.database.table.tables.player.*;

public class TableManager {
	
	//public static HashMap<String, Table> tables = new HashMap<String, Table>();
	
	public static void loadAllTables() {
		new PlayerdataTable().createTable();
		new BanDataTable().createTable();
		new MuteDataTable().createTable();
		new UUIDTable().createTable();
		new PlayerLocalDataTable().createTable();
		new PlayerdataBackupTable().createTable();
	}
	
	public static void registerTable(Table table) {
		table.createTable();
	}

	
	
}
