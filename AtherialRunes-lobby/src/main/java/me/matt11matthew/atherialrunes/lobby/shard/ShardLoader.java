package me.matt11matthew.atherialrunes.lobby.shard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.matt11matthew.atherialrunes.lobby.Main;
import me.matt11matthew.atherialrunes.server.shard.Shard;
import me.matt11matthew.atherialrunes.server.shard.ShardManager;
import me.matt11matthew.atherialrunes.utils.Utils;

public class ShardLoader {
	
	public static Inventory SHARD_MENU;
	public static ItemStack COMPASS = null;
	public static String SHARD_MENU_TITLE = "Shard Menu";
	
	public static final short WHITE = 0;
	public static final short RED = 11;
	
	public void onEnable() {
		COMPASS = getCompassItem();
		SHARD_MENU = Bukkit.createInventory(null, (9 * 1), SHARD_MENU_TITLE);
		ShardManager.shards.values().forEach(shard -> {
			SHARD_MENU.addItem(getShardItem(shard));
		});
	}
	
	public ItemStack getShardItem(Shard shard) {
		ItemStack item = new ItemStack(Material.WOOL, ((shard.getShardServer().isConnected()) ? WHITE : RED));
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(Utils.colorCodes("&f&l" + shard.getPrefix() + shard.getId() + " &7" + shard.getOnlinePlayers() + "/" + shard.getMaxplayers()));
		List<String> lore = new ArrayList<String>();
		lore.add(Utils.colorCodes("&7Click to connect"));
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(im);
		item.setAmount(1);
		return item;
	}
	
	public ItemStack getCompassItem() {
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta im = compass.getItemMeta();
		im.setDisplayName(Utils.colorCodes("&aServer Selector &7(Right-Click)"));
		List<String> lore = new ArrayList<String>();
		lore.add(Utils.colorCodes("&7Right Click"));
		im.setLore(lore);
		compass.setItemMeta(im);
		compass.setAmount(1);
		return compass;
	}
	
	public static void loadShards() {
		new ShardLoader().onEnable();
		Main.print("Loaded shards!");
	}
	
    public static void sendToServer(String playerName, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(playerName);
        out.writeUTF(serverName);
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        if (player != null)
            player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
    }
    
    
}