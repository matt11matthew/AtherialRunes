package me.matt11matthew.atherialrunes.database.table;

import me.matt11matthew.atherialrunes.database.table.tables.player.BanDataTable;
import me.matt11matthew.atherialrunes.database.table.tables.player.MuteDataTable;
import me.matt11matthew.atherialrunes.database.table.tables.player.PlayerLocalDataTable;
import me.matt11matthew.atherialrunes.database.table.tables.player.PlayerdataBackupTable;
import me.matt11matthew.atherialrunes.database.table.tables.player.PlayerdataTable;
import me.matt11matthew.atherialrunes.database.table.tables.player.UUIDTable;

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
