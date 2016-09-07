package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.sort;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.MarketItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortPriceHighest extends Sorter {

	/**
	 *
	 * @return sorted from highest price to lowest price
     */
	@Override
	public List<MarketItem> getSorted(int page) {
		List<MarketItem> byPriceHigh = getItems(page);
		Collections.sort(byPriceHigh, new Comparator<MarketItem>() {

			@Override
			public int compare(MarketItem i1, MarketItem i2) {
				return new Double(i2.getPrice()).compareTo(i1.getPrice());
			}
	    });
		return byPriceHigh;
	}
}
