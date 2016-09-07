package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.search;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;

public class SearchByRarityMenu extends Menu {

	public SearchByRarityMenu() {
		super("&c&lSearch Rarity", (3 * 9));
		
		AtherialItem divider = new AtherialItem(Material.STAINED_GLASS_PANE, (short) 15, 1);
		divider.setName(" ");
		
		AtherialItem normal = new AtherialItem(Material.QUARTZ_STAIRS, 1);
		normal.setName("&bRarity: &fNormal");
		normal.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem unusual = new AtherialItem(Material.EMERALD, 1);
		unusual.setName("&bRarity: &eUnusual");
		unusual.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem epic = new AtherialItem(Material.GOLD_NUGGET, 1);
		epic.setName("&bRarity: &aEpic");
		epic.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem legendary = new AtherialItem(Material.NETHER_STAR, 1);
		legendary.setName("&bRarity: &3Legendary");
		legendary.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		setItem(1, divider.build());
		setItem(2, divider.build());
		setItem(3, divider.build());
		setItem(4, divider.build());
		setItem(5, divider.build());
		setItem(6, divider.build());
		setItem(7, divider.build());
		setItem(8, divider.build());
		setItem(9, divider.build());
		setItem(10, divider.build());
		setItem(11, normal.build());
		setItem(12, divider.build());
		setItem(13, unusual.build());
		setItem(14, divider.build());
		setItem(15, epic.build());
		setItem(16, divider.build());
		setItem(17, legendary.build());
		setItem(18, divider.build());
		setItem(19, divider.build());
		setItem(20, divider.build());
		setItem(21, divider.build());
		setItem(22, divider.build());
		setItem(23, divider.build());
		setItem(24, divider.build());
		setItem(25, divider.build());
		setItem(26, divider.build());
	}
}
