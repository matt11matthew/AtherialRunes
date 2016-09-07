package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.events.AtherialLocalChatEvent;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.*;
import java.util.HashMap;

public class SpawnerMechanics extends ListenerMechanic {
	
	public static HashMap<Location, Spawner> spawners = new HashMap<Location, Spawner>();
	public static HashMap<GamePlayer, Block> placing = new HashMap<GamePlayer, Block>();

	@Override
	public void onEnable() {
		print("[SpawnerMechanics] Enabling...");
		registerListeners();
		loadSpawners();
	}

	@Override
	public void onDisable() {
		print("[SpawnerMechanics] Disabling...");
		saveSpawners();
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.MONITOR;
	}
	
	public void loadSpawners() {
		File file = new File(Main.getInstance().getDataFolder() + "/Spawners/mobdata.spawners");
		if (!file.exists()) return;
		String spawnerFileText = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			spawnerFileText = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (String spawner : spawnerFileText.split("\n")) {
			if (spawner != null) {
				String l = spawner.split("=")[0];
				String worldName = l.split("world:")[1].split(",")[0].trim();
				double x = Integer.parseInt(l.split("x:")[1].split(",")[0].trim());
				double y = Integer.parseInt(l.split("y:")[1].split(",")[0].trim());
				double z = Integer.parseInt(l.split("z:")[1].split(",")[0].trim());
				Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
				String m = spawner.split("=")[1];
				String mob = m.split("Mob:")[1].split(",")[0].trim();
				int cooldown = Integer.parseInt(m.split("Cooldown:")[1].split(",")[0].trim());
				int range = Integer.parseInt(m.split("Range:")[1].split(",")[0].trim());
				int tier = Integer.parseInt(m.split("Tier:")[1].split(",")[0].trim());
				boolean elite = Boolean.parseBoolean(m.split("Elite:")[1].split(",")[0].trim());
				Spawner spawn = new Spawner(location, cooldown, range, elite, mob, tier);
				spawners.put(location, spawn);
			}
		}
		return;
	}
	
	public void saveSpawners() {
		spawners.values().forEach(spawner -> {
			File file = new File(Main.getInstance().getDataFolder() + "/Spawners/");
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(Main.getInstance().getDataFolder() + "/Spawners/mobdata.spawners");
			file.delete();
			if (!file.exists()) {
				try {
					file.createNewFile();
					FileWriter fileWriter = new FileWriter(file);
					Location l = spawner.getLocation();
					String loc = ("world:" + l.getWorld().getName() + "," + "x:" + (int) l.getX() + ",y:" + (int) l.getY() + ",z:" + (int) l.getZ() + ",");
					String s = loc + "=Mob:" + spawner.getMob() + ",Cooldown:" + spawner.getCooldown() + ",Range:" + spawner.getRange() + ",Tier:" + spawner.getTier() + ",Elite:" + spawner.isElite() + ",";
					fileWriter.write(s + System.lineSeparator());
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@EventHandler
	public void onChat(AtherialLocalChatEvent e) {
		GamePlayer gp = e.getGamePlayer();
		if (gp.isInAdminMode()) {
			if (placing.containsKey(gp)) {
				e.setCancelled(true);
				String info = e.getMessage().trim();
				try {
					String msg = e.getMessage();
					String mob = msg.split("Mob:")[1].split(",")[0].trim();
					int tier = Integer.parseInt(msg.split("Tier:")[1].split(",")[0].trim());
					boolean elite = Boolean.parseBoolean(msg.split("Elite:")[1].split(",")[0].trim());
					int cooldown = Integer.parseInt(msg.split("Cooldown:")[1].split(",")[0].trim());
					int range = Integer.parseInt(msg.split("Range:")[1].split("=")[0].trim());
					Spawner spawner = new Spawner(placing.get(gp).getLocation(), cooldown, range, elite, mob, tier);
				} catch (Exception ee) {
					gp.msg(MessageType.CHAT, "&cError");
					return;
				}
			}
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (Rank.isGM(player.getName())) {
			GamePlayer gp = Main.getGamePlayer(player);
			if (gp.isInAdminMode()) {
				if (e.getBlock().getType() == Material.MOB_SPAWNER) {
					e.setCancelled(true);
					gp.msg(MessageType.CHAT, "Please type mob info");
					gp.msg(MessageType.CHAT, "Type =Mob:troll,Tier:1,Elite:false,Cooldown:60,Range:16=");
					placing.put(gp, e.getBlock());
					return;
				}
			}
		}
	}
}

