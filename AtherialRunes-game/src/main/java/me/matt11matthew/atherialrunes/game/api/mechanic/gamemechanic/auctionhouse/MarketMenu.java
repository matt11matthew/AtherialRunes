package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import me.matt11matthew.atherialrunes.item.AtherialItem;
import me.matt11matthew.atherialrunes.menu.Menu;

public class MarketMenu extends Menu {

	/**
	 *
	 * @param pg the page
     */
	public MarketMenu(int pg) {
		super("&c&lMarket (" + pg + ")", (6 * 9));
		
		AtherialItem divider = new AtherialItem(Material.STAINED_GLASS_PANE, (short) 15, 1);
		divider.setName(" ");
		
		AtherialItem settings = new AtherialItem(Material.EMPTY_MAP);
		settings.setName("&cSettings");
		settings.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		AtherialItem previous = new AtherialItem(Material.TIPPED_ARROW);
		previous.setName("&aPrevious Page");
		previous.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);

		AtherialItem next = new AtherialItem(Material.TIPPED_ARROW);
		next.setName("&aNext Page");
		next.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		setItem(0, divider.build());
		setItem(1, divider.build());
		setItem(2, divider.build());
		setItem(3, divider.build());
		setItem(4, divider.build());
		setItem(5, divider.build());
		setItem(6, divider.build());
		setItem(7, divider.build());
		setItem(8, divider.build());
		setItem(45, divider.build());
		setItem(46, divider.build());
		setItem(47, divider.build());
		setItem(48, previous.build());
		setItem(49, settings.build());
		setItem(50, next.build());
		setItem(51, divider.build());
		setItem(52, divider.build());
		setItem(53, divider.build());
		Page page = MarketManager.getPage(pg);
		int i = 10;
		try {
			while ((i < page.getItemList().size())) {
				if (i > 39) {
					break;
				}
				setItem(i, page.getItem(i).buildItem());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();;
			return;
		}
	}
}
