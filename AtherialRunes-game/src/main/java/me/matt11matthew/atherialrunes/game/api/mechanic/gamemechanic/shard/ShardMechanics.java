package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.network.ShardInfo;
import me.matt11matthew.atherialrunes.game.network.bungeecord.BungeeUtils;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ShardMechanics extends ListenerMechanic {
	
	public static HashMap<String, ShardInfo> shards = new HashMap<String, ShardInfo>();
	public static HashMap<Player, String> sharding = new HashMap<Player, String>();
	
	@Override
	public void onEnable() {
		print("-----------------------------------------");
		print("[ShardMechanics] Enabling...");
		print("-----------------------------------------");
		registerListeners();
		loadShards();
		enableShards();
	}

	@Override
	public void onDisable() {
		print("-----------------------------------------");
		print("[ShardMechanics] Disabling...");
		print("-----------------------------------------");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.MONITOR;
	}
	
	public void loadShards() {
		for (ShardInfo shard : ShardInfo.values()) {
			shards.put(shard.getShardID(), shard);
		}
	}
	
	public void enableShards() {
		shards.values().forEach(shard -> {
			Main.print("Shards loaded!");
			Main.print(shard.getPseudoName());
		});
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (e.getSlotType() == SlotType.OUTSIDE) {
			return;
		}
		if (e.getInventory().getTitle().equals(ShardMenu.NAME)) {
			e.setCancelled(true);
			ItemStack cur = e.getCurrentItem();
			if (cur.getType() != Material.AIR) {
				String shard = ChatColor.stripColor(cur.getItemMeta().getLore().get(0));
				short dura = cur.getDurability();
				switch (dura) {
					case ShardMenu.RED:
						player.sendMessage(Utils.colorCodes("&c" + shard + " is &lOFFLINE"));
						player.closeInventory();
						break;
					case 0:
						player.closeInventory();
						shard(player, shard);
						break;
					default:
						break;
				}
				return;
			}
			
		}
	}

	public void shard(Player player, String shard) {
		GamePlayer gp = Main.getGamePlayer(player.getName());
		gp.msg(MessageType.CHAT, "&aLoading...SUB:&7Please wait! (Don't move)");
		sharding.put(player, shard);
		BungeeUtils.sendToServer(player.getName(), shard);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (sharding.containsKey(player)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if (sharding.containsKey(player)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		Player player = e.getPlayer();
		if (sharding.containsKey(player)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if (sharding.containsKey(player)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (sharding.containsKey(player)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		if (sharding.containsKey(player)) {
			e.setCancelled(true);
			player.sendMessage(Utils.colorCodes("&cYou cannot run commands while sharding..."));
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (sharding.containsKey(player)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		sharding.remove(e.getPlayer());
	}
}
