package me.matt11matthew.atherialrunes.database.table.tables.player;

import me.matt11matthew.atherialrunes.database.table.Table;

public class BanDataTable extends Table {

	@Override
	public String getName() {
		return "bandata";
	}

	@Override
	public void createTable() {
		create("CREATE TABLE IF NOT EXISTS `" + getName() + "` (`uuid` VARCHAR(50) NOT NULL, `ign` varchar(20) NOT NULL, `time` BIGINT, `banreason` varchar(2000) NOT NULL, `unbanreason` varchar(2000) NOT NULL, `bannedby` varchar(100) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
	}
}
