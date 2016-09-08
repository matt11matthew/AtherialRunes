package me.matt11matthew.atherialrunes.database.table.tables.player;

import org.bukkit.entity.Player;

import me.matt11matthew.atherialrunes.database.data.player.PlayerData;
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
				+ " `exp` BIGINT,"
				+ " `skillpoints` INT,"
				+ " `gold` INT,"
				+ " `silver` INT,"
				+ " `copper` INT,"
				+ " `nick` varchar(20) NOT NULL,"
				+ " `admin` varchar(20) NOT NULL,"
				+ " `notoriety` INT,"
				+ " `shard` varchar(20) NOT NULL)"
				+ " ENGINE=InnoDB DEFAULT CHARSET=latin1;");
	}
	
	public void save(Player player) {
		new PlayerData(player.getName()).save(player.getUniqueId().toString());
	}
}
//uuid, ign, rank, channel, combat, level, vanish, exp, shard