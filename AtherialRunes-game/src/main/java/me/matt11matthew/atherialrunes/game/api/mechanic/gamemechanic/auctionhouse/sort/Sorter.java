package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.sort;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.AuctionHouseMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.MarketItem;

import java.util.ArrayList;
import java.util.List;

public abstract class Sorter {

	/**
	 *
	 * @return sorted
     */
	public abstract List<MarketItem> getSorted();

	/**
	 *
	 * @return all market items
     */
	public List<MarketItem> getItems() {
		List<MarketItem> items = new ArrayList<MarketItem>();
		for (MarketItem item : AuctionHouseMechanics.items.values()) {
			items.add(item);
		}
		return items;
	}
}
