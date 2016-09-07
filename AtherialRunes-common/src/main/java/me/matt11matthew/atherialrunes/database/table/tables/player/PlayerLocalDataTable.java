package me.matt11matthew.atherialrunes.database.table.tables.player;

import org.bukkit.entity.Player;

import me.matt11matthew.atherialrunes.database.data.player.LocalData;
import me.matt11matthew.atherialrunes.database.table.Table;

public class PlayerLocalDataTable extends Table {

	@Override
	public String getName() {
		return "localplayerdata";
	}

	@Override
	public void createTable() {
		create("CREATE TABLE IF NOT EXISTS `" + getName() + "` (`uuid` VARCHAR(100) NOT NULL, `ign` varchar(20) NOT NULL, `level` INT, `food` INT, `location` varchar(200) NOT NULL, `hp` INT, `maxhp` INT, `armor` varchar(20000) NOT NULL, `inventory` varchar(20000) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
	}
	
	public void save(Player player) {
		new LocalData().save(player);
	}
}
