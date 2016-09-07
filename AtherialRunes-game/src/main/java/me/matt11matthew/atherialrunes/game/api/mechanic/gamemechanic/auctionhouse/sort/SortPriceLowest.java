package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.sort;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.MarketItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortPriceLowest extends Sorter {

	/**
	 *
	 * @return sorted from lowest price to highest price
	 */
	@Override
	public List<MarketItem> getSorted(int page) {
		List<MarketItem> byPriceLow = getItems(page);
		Collections.sort(byPriceLow, new Comparator<MarketItem>() {

			@Override
			public int compare(MarketItem i1, MarketItem i2) {
				return new Double(i1.getPrice()).compareTo(i2.getPrice());
			}
	    });
		return byPriceLow;
	}
}
