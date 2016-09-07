package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;

public class OptionMenu extends Menu {

	public OptionMenu() {
		super("&c&lOptions", (3 * 9));
		
		AtherialItem divider = new AtherialItem(Material.STAINED_GLASS_PANE, (short) 15, 1);
		divider.setName(" ");
		
		AtherialItem seller = new AtherialItem(Material.FIREWORK_CHARGE, 1);
		seller.setName("&aSearch by Seller");
		seller.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem tier = new AtherialItem(Material.FIREWORK_CHARGE, 1);
		tier.setName("&aSearch by Tier");
		tier.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem rarity = new AtherialItem(Material.FIREWORK_CHARGE, 1);
		rarity.setName("&aSearch by Rarity");
		rarity.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem type = new AtherialItem(Material.FIREWORK_CHARGE, 1);
		type.setName("&aSearch by Item Type");
		type.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
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
		setItem(10, tier.build());
		setItem(11, divider.build());
		setItem(12, seller.build());
		setItem(13, divider.build());
		setItem(14, rarity.build());
		setItem(15, divider.build());
		setItem(16, type.build());
		setItem(17, divider.build());
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
