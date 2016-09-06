package me.matt11matthew.atherialrunes.database.table.tables.player;

import me.matt11matthew.atherialrunes.database.table.Table;

public class MuteDataTable extends Table {

	@Override
	public String getName() {
		return "mutedata";
	}

	@Override
	public void createTable() {
		create("CREATE TABLE IF NOT EXISTS `" + getName() + "` (`uuid` VARCHAR(50) NOT NULL, `ign` varchar(20) NOT NULL, `time` BIGINT, `reason` varchar(2000) NOT NULL, `unmutereason` varchar(2000) NOT NULL, `mutedby` varchar(100) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
	}
}
