package me.matt11matthew.atherialrunes.lobby;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import me.matt11matthew.atherialrunes.lobby.shard.ShardLoader;
import me.matt11matthew.atherialrunes.server.shard.Shard;
import me.matt11matthew.atherialrunes.server.shard.ShardManager;
import me.matt11matthew.atherialrunes.utils.Utils;

public class PlayerListeners implements Listener {
	
	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		e.setCancelled(true);
		if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			if ((player.getEquipment().getItemInMainHand() != null) && (player.getEquipment().getItemInMainHand().getType() == Material.COMPASS)) {
				player.openInventory(ShardLoader.SHARD_MENU);
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.VOID) { 
			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				player.teleport(Main.getLobbySpawn());
			}
		}
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		player.getInventory().clear();
		player.getInventory().setItem(0, ShardLoader.COMPASS);
	}
    
    @EventHandler
    public void onClick(InventoryClickEvent e) {
    	if (e.getSlotType() == SlotType.OUTSIDE) {
    		return;
    	}
    	Player player = (Player) e.getWhoClicked();
    	e.setCancelled(true);
    	if (e.getClickedInventory().getTitle().equals(ShardLoader.SHARD_MENU_TITLE)) {
    		ItemStack cur = e.getCurrentItem();
    		if (cur.getType() == Material.WOOL) {
    			String name = ChatColor.stripColor(cur.getItemMeta().getDisplayName());
    			String shardId = name.split("-")[1].split(" ")[0].replaceAll(" ", "").trim();
    			Shard shard = ShardManager.shards.get(shardId);		
    			switch (cur.getDurability()) {
    				case ShardLoader.RED:
    					player.sendMessage(Utils.colorCodes("&c" + shard.getPrefix() + shard.getId() + " is &lOFFLINE"));
    					player.closeInventory();
    					break;
    				case ShardLoader.WHITE:
    					player.sendMessage(Utils.colorCodes("&cJoining &f&l" + shard.getPrefix() + shard.getId()));
    					player.closeInventory();
    					String server = (shard.getPrefix() + shard.getId()).replace(" ", "").toLowerCase().trim();
    					ShardLoader.sendToServer(player.getName(), server);
    					break;
    			}
    			if (cur.getDurability() == ShardLoader.RED) { 
    				player.sendMessage(Utils.colorCodes("&c" + shard.getPrefix() + shard.getId() + " is &lOFFLINE"));
    				player.closeInventory();
    				return;
    			}
    			
    		}
    	}
    }
}