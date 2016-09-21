package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner;

import com.mysql.jdbc.PreparedStatement;
import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.game.GameConstants;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.MobMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.events.AtherialChatEvent;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.commands.ReloadException;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import me.matt11matthew.atherialrunes.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SpawnerMechanics extends ListenerMechanic {
	
	public static HashMap<Location, Spawner> spawners = new HashMap<Location, Spawner>();
	public static HashMap<GamePlayer, Block> placing = new HashMap<GamePlayer, Block>();

	static SpawnerMechanics instance = null;

	@Override
	public void onEnable() {
		print("-----------------------------------------");
		print("[SpawnerMechanics] Enabling...");
		print("-----------------------------------------");
		createTable();
		registerListeners();
		loadAllSpawners();
		spawnerTask();
	}

	private void createTable() {
		PreparedStatement pst = null;
		try {
			pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("create table if not exists mobspawners (`location` varchar(100) NOT NULL, `cooldown` INT, `range` INT, `mob` varchar(100) NOT NULL, `elite` varchar(100) NOT NULL, `amount` INT) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void onDisable() {
		print("-----------------------------------------");
		print("[SpawnerMechanics] Disabling...");
		print("-----------------------------------------");
		saveAllSpawners();
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.LOWEST;
	}

	public void loadAllSpawners() {
		PreparedStatement pst = null;
		try {
			pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("select * from mobspawners order by location");
			pst.execute();
			ResultSet rs = pst.getResultSet();
			while (rs.next()) {
				String s = rs.getString("location").replaceAll(".", ",");
				String[] sSplit = s.split(",");
				Location location = Bukkit.getWorld(GameConstants.WORLD_NAME).getBlockAt(Integer.parseInt(sSplit[0]), Integer.parseInt(sSplit[1]), Integer.parseInt(sSplit[2])).getLocation();
				int cooldown = rs.getInt("cooldown");
				int range = rs.getInt("range");
				String mob = rs.getString("mob");
				int tier = 0;
				boolean elite = Boolean.parseBoolean(rs.getString("elite"));
				int amount = rs.getInt("amount");
				Spawner spawner = new Spawner(location, cooldown, range, elite, mob, tier, amount);
				spawners.put(location, spawner);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
			}
		}
		Main.print("Spawners loaded");
	}

	
	public void saveAllSpawners() {
		PreparedStatement pst = null;
		try {
			for (Spawner spawner : spawners.values()) {
				pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("delete * from mobspawners");
				pst.executeUpdate();
				pst = (PreparedStatement) ConnectionPool.getConnection().prepareStatement("insert into mobspawners"
						+ "(location, cooldown, range, mob, elite, amount) "
						+ "VALUES("
						+ "'" + spawner.getLocation()
						+ "', '" + spawner.getCooldown()
						+ "', '" + spawner.getRange()
						+ "', '" + spawner.getMob()
						+ "', '" + spawner.isElite()
						+ "', '" + spawner.getAmountSpawn() + "') "
						+ "ON DUPLICATE KEY UPDATE "
						+ "location='" + spawner.getLocation()
						+ "', cooldown='" + spawner.getCooldown()
						+ "', range='" + spawner.getRange()
						+ "', mob='" + spawner.getMob()
						+ "', elite='" + spawner.isElite()
						+ "', amount='" + spawner.getAmountSpawn() + "'");
				pst.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					try {
						pst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
			}
		}
		Main.print("Spawners saved");
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
					spawner.setCurrentCooldown(0);
					spawner.setActive(true);
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

	@EventHandler
	public void onSpawnerRightClick(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		if (Rank.isGM(player.getName())) {
			if (action == Action.RIGHT_CLICK_BLOCK) {
				if ((e.getClickedBlock() != null) && (e.getClickedBlock().getType() == Material.MOB_SPAWNER)) {
					if (spawners.containsKey(e.getClickedBlock().getLocation())) {
						e.setCancelled(true);
						Spawner spawner = spawners.get(e.getClickedBlock().getLocation());
						Location location = spawner.getLocation();
						GamePlayer gp = Main.getGamePlayer(player);
						gp.msg(MessageType.CHAT, "&a================================");
						gp.msg(MessageType.CHAT, "&aLocation: &7" + (int) location.getX() + ", " + (int) location.getY() + ", " + (int) + location.getZ());
						gp.msg(MessageType.CHAT, "&aMob: &7" + ChatColor.stripColor(spawner.getMob()));
						gp.msg(MessageType.CHAT, "&aCooldown: &7" + spawner.getCooldown());
						gp.msg(MessageType.CHAT, "&aRange: &7" + spawner.getRange());
						gp.msg(MessageType.CHAT, "&aAmount: &7" + spawner.getAmountSpawn());
						gp.msg(MessageType.CHAT, "&a================================");
						return;
					}
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
			MobMechanics.spawn(spawner.getLocation(), MobMechanics.getMob(spawner.getMob()), spawner);
			i++;
		}
	}

	public static void reload() throws ReloadException {
		SpawnerMechanics.getInstance().saveAllSpawners();
		try {
			SpawnerMechanics.getInstance().loadAllSpawners();
		} catch (Exception e) {
			throw new ReloadException("SpawnerMechanics");
		}
	}

	public static SpawnerMechanics getInstance() {
		if (instance == null) {
			instance = new SpawnerMechanics();
		}
		return instance;
	}

	public Location findLocation(Spawner spawner) {
		return spawner.getLocation();
		//TODO create a method with a random location
	}



	/*
	{
  "type": "skeleton",
  "health": "100-100",
  "damage": "100-500",
  "names": [
    "&cEpic Skeleton",
    "&bT4 Skeleton"
  ],
	}
  "helmets": [
    "skull:matt11matthew",
    "4"
  ],
  "chestplates": [
    "4"
  ],
  "leggings": [
    "4"
  ],
  "boots": [
    "4"
  ],
  "weapons": [
    "axe:4",
    "sword:4"
  ]
}
	 */
}

