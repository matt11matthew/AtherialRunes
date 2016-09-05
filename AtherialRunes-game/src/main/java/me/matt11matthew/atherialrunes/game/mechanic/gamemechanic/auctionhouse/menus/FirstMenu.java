package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.auctionhouse.menus;

import org.bukkit.Material;

import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;

public class FirstMenu extends Menu {

	public FirstMenu() {
		super("&c&lAuction House", 9);
		
		AtherialItem divider = new AtherialItem(Material.STAINED_GLASS_PANE, (short) 15, 1);
		divider.setName(" ");
		
		AtherialItem sell = new AtherialItem(Material.INK_SACK, (short) 13, 1);
		sell.setName("&aSell Your Items");
		sell.addLore("&7Click this and choose what you want to sell.");
		
		AtherialItem view = new AtherialItem(Material.INK_SACK, (short) 5, 1);
		view.setName("&aView the Auction House");
		view.addLore("&7Click this to view what others are selling.");
		
		setItem(0, divider.build());
		setItem(1, divider.build());
		setItem(2, divider.build());
		setItem(3, sell.build());
		setItem(4, divider.build());
		setItem(5, view.build());
		setItem(6, divider.build());
		setItem(7, divider.build());
		setItem(8, divider.build());
		
	}

}
