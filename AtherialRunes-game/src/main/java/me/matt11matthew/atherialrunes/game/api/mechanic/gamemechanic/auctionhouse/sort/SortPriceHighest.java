package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.MarketItem;

public class SortPriceHighest extends Sorter {

	@Override
	public List<MarketItem> getSorted() {
		List<MarketItem> byPriceHigh = getItems();
		Collections.sort(byPriceHigh, new Comparator<MarketItem>() {

			@Override
			public int compare(MarketItem i1, MarketItem i2) {
				return new Double(i2.getPrice()).compareTo(i1.getPrice());
			}
	    });
		return byPriceHigh;
	}
}
