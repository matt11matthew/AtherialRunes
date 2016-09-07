package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.sort;

import java.util.ArrayList;
import java.util.List;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.AuctionHouseMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.MarketItem;

public abstract class Sorter {
	
	public abstract List<MarketItem> getSorted();
	
	public List<MarketItem> getItems() {
		List<MarketItem> items = new ArrayList<MarketItem>();
		for (MarketItem item : AuctionHouseMechanics.items.values()) {
			items.add(item);
		}
		return items;
	}
}
