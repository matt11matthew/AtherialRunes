package me.matt11matthew.atherialrunes.database.data.player;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.matt11matthew.atherialrunes.database.data.Data;
import me.matt11matthew.atherialrunes.database.table.tables.player.UUIDTable;

public class UUIDData implements Data {
	
	public static HashMap<String, String> uuids = new HashMap<String, String>();

	public void save(Player player) {
		new UUIDTable().save(player);
		uuids.remove(player.getName());
	}

	public void update(Player player) {
		uuids.put(player.getName(), player.getUniqueId().toString());
	}
	
	public void update(String uuid, String name) {
		uuids.put(name, uuid);
	}
	
	public static String getUUID(String name) {
		return (uuids.containsKey(name)) ? uuids.get(name) : UUIDTable.getUUID(name);
	}



}
