package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.auctionhouse;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.auctionhouse.menus.MarketItem;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.auctionhouse.menus.OptionMenu;
import me.matt11matthew.atherialrunes.utils.Utils;

public class AuctionHouseMechanics extends ListenerMechanic {
	
	public static HashMap<UUID, MarketItem> items = new HashMap<UUID, MarketItem>();

	@Override
	public void onEnable() {
		print("[AuctionHouseMechanics] Enabling...");
		registerListeners();
		new AuctionHouseTable().createTable();
		loadItems();
	}

	@Override
	public void onDisable() {
		print("[AuctionHouseMechanics] Disabling...");
		saveItems();
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.NORMAL;
	}
	
	public static void loadItems() {
		new AuctionHouseTable().loadItems();
	}
	
	public static void saveItems() {
		new AuctionHouseTable().saveItems();
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getSlotType() == SlotType.OUTSIDE) {
			return;
		}
		Player player = (Player) e.getWhoClicked();
		player.sendMessage(e.getSlot() + "");
		if (e.getClickedInventory().getTitle().equals(Utils.colorCodes("&c&lMarket"))) {
			e.setCancelled(true);
			int slot = e.getSlot();
			switch (slot) {
				case 49:
					player.closeInventory();
					new OptionMenu().open(player);
					break;
			}
		}
		if (e.getClickedInventory().getTitle().equals(Utils.colorCodes("&c&lAuction House"))) {
			e.setCancelled(true);
			int slot = e.getSlot();
			switch (slot) {
				case 3:
					player.sendMessage("sell items");
					break;
				case 5:
					player.closeInventory();
					new MarketMenu(1).open(player);
					break;
			}
		}
	}

}