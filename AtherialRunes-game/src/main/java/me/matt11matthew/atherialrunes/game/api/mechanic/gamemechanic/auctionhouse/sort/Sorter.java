package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.sort;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.MarketManager;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.Page;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.MarketItem;

import java.util.List;

public abstract class Sorter {

	/**
	 *
	 * @return sorted
     */
	public abstract List<MarketItem> getSorted(int page);

	/**
	 *
	 * @return all market items
     */
	public List<MarketItem> getItems(int pg) {
		Page page = MarketManager.getPage(pg);
		return page.getItemList();
	}
}
