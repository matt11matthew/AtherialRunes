package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.MobMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.events.AtherialChatEvent;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.*;
import java.util.HashMap;

public class SpawnerMechanics extends ListenerMechanic {
	
	public static HashMap<Location, Spawner> spawners = new HashMap<Location, Spawner>();
	public static HashMap<GamePlayer, Block> placing = new HashMap<GamePlayer, Block>();

	@Override
	public void onEnable() {
		print("-----------------------------------------");
		print("[SpawnerMechanics] Enabling...");
		print("-----------------------------------------");
		registerListeners();
		loadSpawners();
		spawnerTask();
	}

	@Override
	public void onDisable() {
		print("-----------------------------------------");
		print("[SpawnerMechanics] Disabling...");
		print("-----------------------------------------");
		saveSpawners();
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.LOWEST;
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
				int amount = Integer.parseInt(m.split("Amount:")[1].split(",")[0].trim());
				boolean elite = Boolean.parseBoolean(m.split("Elite:")[1].split(",")[0].trim());
				Spawner spawn = new Spawner(location, cooldown, range, elite, mob, tier, amount);
				spawners.put(location, spawn);
			}
		}
		return;
	}
	
	public void saveSpawners() {
		File file = new File(Main.getInstance().getDataFolder() + "/Spawners/");
		if (!file.exists()) {
			file.mkdirs();
		}
		spawners.values().forEach(spawner -> {

			File spawnerFile = new File(Main.getInstance().getDataFolder() + "/Spawners/mobdata.spawners");
			spawnerFile.delete();
			if (!spawnerFile.exists()) {
				try {
					spawnerFile.createNewFile();
					FileWriter fileWriter = new FileWriter(spawnerFile);
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
	public void onChat(AtherialChatEvent e) {
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
					int amount = Integer.parseInt(msg.split("Amount:")[1].split(",")[0].trim());
					int range = Integer.parseInt(msg.split("Range:")[1].split("=")[0].trim());
					Spawner spawner = new Spawner(placing.get(gp).getLocation(), cooldown, range, elite, mob, tier, amount);
					spawners.put(spawner.getLocation(), spawner);
					gp.msg(MessageType.CHAT, "&aYou've placed a spawner!");
				} catch (Exception ee) {
					gp.msg(MessageType.CHAT, "&cError");
				} finally {
					placing.remove(gp);
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
					gp.msg(MessageType.CHAT, "Type =Mob:t4custom,Tier:1,Elite:false,Cooldown:60,Amount:1,Range:16=");
					placing.put(gp, e.getBlock());
					return;
				}
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getPlayer().isOp()) {
			if (e.getBlock().getType() == Material.MOB_SPAWNER) {
				if (spawners.containsKey(e.getBlock().getLocation())) {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					spawners.remove(e.getBlock().getLocation());
					e.getPlayer().sendMessage(Utils.colorCodes("&aYou've broken a spawner!"));
				}
			}
		}
	}

	public void spawnerTask() {
		AtherialRunnable.getInstance().runRepeatingTask(() -> {
			spawners.values().forEach(spawner -> {
				int timeTask = 0;
				if (!spawner.isActive()) {
					if (spawner.getCurrentCooldown() > 0) {
						timeTask = AtherialRunnable.getInstance().runRepeatingTask( () -> {
							spawner.setCurrentCooldown((spawner.getCurrentCooldown() - 1));
						}, (spawner.getCurrentCooldown() * 20L), (spawner.getCurrentCooldown() * 20L));
					}
				}
				if (spawner.getCurrentCooldown() == 0) {
					spawner.setActive(true);
				}
				if (spawner.isActive()) {
					spawner.setCurrentCooldown(spawner.getCooldown());
					spawner.setActive(false);
					spawnMob(spawner, spawner.getAmountSpawn());
					return;
				}
			});
        }, 1L, 1L);
	}

	public void spawnMob(Spawner spawner, int amount) {
		int i = 0;
		while (i < amount) {
			MobMechanics.spawn(spawner.getLocation(), MobMechanics.mobs.get(spawner.getMob()), spawner);
			i++;
		}
	}

	public Location findLocation(Spawner spawner) {
		return spawner.getLocation();
		//TODO create a method with a random location
	}
}

