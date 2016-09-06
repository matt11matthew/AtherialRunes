package me.matt11matthew.atherialrunes.database.table.tables.player;


import me.matt11matthew.atherialrunes.database.table.Table;

public class PlayerdataTable extends Table {

	@Override
	public String getName() {
		return "playerdata";
	}

	@Override
	public void createTable() {
		create("CREATE TABLE IF NOT EXISTS `" + getName() + "` ("
				+ "`uuid` VARCHAR(50) NOT NULL,"
				+ " `ign` varchar(20) NOT NULL,"
				+ " `rank` varchar(20) NOT NULL,"
				+ " `channel` INT,"
				+ " `combat` INT,"
				+ " `level` INT,"
				+ "`vanish` varchar(20) NOT NULL,"
				+ " `exp` INT,"
				+ " `skillpoints` INT,"
				+ " `gold` INT,"
				+ " `silver` INT,"
				+ " `copper` INT,"
				+ " `shard` varchar(20) NOT NULL)"
				+ " ENGINE=InnoDB DEFAULT CHARSET=latin1;");
	}
	

}
//uuid, ign, rank, channel, combat, level, vanish, exp, shard