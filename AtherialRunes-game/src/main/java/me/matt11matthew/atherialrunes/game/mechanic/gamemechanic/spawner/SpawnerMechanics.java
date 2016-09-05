package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.spawner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;

public class SpawnerMechanics extends ListenerMechanic {
	
	public static HashMap<Location, Spawner> spawners = new HashMap<Location, Spawner>();

	@Override
	public void onEnable() {
		print("[SpawnerMechanics] Enabling...");
		registerListeners();
	}

	@Override
	public void onDisable() {
		print("[SpawnerMechanics] Disabling...");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.MONITOR;
	}
	
	public void loadSpawners() {
		
	}
	
	public void saveSpawners() {
		spawners.values().forEach(spawner -> {
			File file = new File(Main.getInstance().getDataFolder() + "/Spawners/");
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(Main.getInstance().getDataFolder() + "/Spawners/mobdata.spawners");
			if (!file.exists()) {
				try {
					file.createNewFile();
					FileWriter fileWriter = new FileWriter(file);
					Location l = spawner.getLocation();
					String loc = ("world:" + l.getWorld().getName() + "," + "x:" + (int) l.getX() + ",y:" + (int) l.getY() + ",z:" + (int) l.getZ() + ",");
					String s = loc + "=" + spawner.getMob() + ",Cooldown:" + spawner.getCooldown() + ",Range:" + spawner.getRange() + ",";
					fileWriter.write(s + System.lineSeparator());
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
