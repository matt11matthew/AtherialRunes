package me.matt11matthew.atherialrunes.database.table.tables.player;

import me.matt11matthew.atherialrunes.Main;
import me.matt11matthew.atherialrunes.database.table.Table;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;

public class PlayerdataBackupTable extends Table {

	@Override
	public String getName() {
		return "backup_playerdata";
	}

	@Override
	public void createTable() {
		create("CREATE TABLE IF NOT EXISTS `" + getName() + "` (`uuid` VARCHAR(50) NOT NULL, `ign` varchar(20) NOT NULL, `rank` varchar(20) NOT NULL, `shard` varchar(200) NOT NULL, `time` varchar(200) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
	}
	
	public void backup(AtherialPlayer player) {
        String time = "None";
		insert("INSERT INTO " + getName()
				+ "(uuid, ign, rank, shard) "
				+ "VALUES("
				+ "'" + player.getUUID()
				+ "', '" + player.getName()
				+ "', '"+ player.getRank()
				+ "', '"+ player.getShard()
				+ "', '" + time + "') "
				+ "ON DUPLICATE KEY UPDATE "
				+ "uuid='" + player.getUUID()
				+ "', ign='" + player.getName()
				+ "', rank='" + player.getRank()
				+ "', shard='" + player.getShard()
				+ "', time='" + time + "'");
		Main.print("Created BACKUP for " + player.getName());
	}
	
}
