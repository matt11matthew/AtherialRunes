package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.search;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;

public class SearchByTierMenu extends Menu {

	public SearchByTierMenu() {
		super("&c&lSearch Tier", (3 * 9));
		
		AtherialItem divider = new AtherialItem(Material.STAINED_GLASS_PANE, (short) 15, 1);
		divider.setName(" ");
		
		AtherialItem t1 = new AtherialItem(Material.LEATHER_CHESTPLATE, 1);
		t1.setName("&fTier 1");
		t1.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem t2 = new AtherialItem(Material.CHAINMAIL_CHESTPLATE, 1);
		t2.setName("&7Tier 2");
		t2.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem t3 = new AtherialItem(Material.IRON_CHESTPLATE, 1);
		t3.setName("&3Tier 3");
		t3.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem t4 = new AtherialItem(Material.DIAMOND_CHESTPLATE, 1);
		t4.setName("&cTier 4");
		t4.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		setItem(0, divider.build());
		setItem(1, divider.build());
		setItem(2, divider.build());
		setItem(3, divider.build());
		setItem(4, divider.build());
		setItem(5, divider.build());
		setItem(6, divider.build());
		setItem(7, divider.build());
		setItem(8, divider.build());
		setItem(9, divider.build());
		setItem(10, t1.build());
		setItem(11, divider.build());
		setItem(12, t2.build());
		setItem(13, divider.build());
		setItem(14, t3.build());
		setItem(15, divider.build());
		setItem(16, t4.build());
		setItem(17, divider.build());
		setItem(18, divider.build());
		setItem(19, divider.build());
		setItem(20, divider.build());
		setItem(21, divider.build());
		setItem(22, divider.build());
		setItem(23, divider.build());
		setItem(24, divider.build());
		setItem(25, divider.build());
	}
}
