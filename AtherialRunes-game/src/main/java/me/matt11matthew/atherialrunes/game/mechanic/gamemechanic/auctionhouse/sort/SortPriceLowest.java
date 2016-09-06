package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.auctionhouse.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.auctionhouse.menus.MarketItem;

public class SortPriceLowest extends Sorter {

	@Override
	public List<MarketItem> getSorted() {
		List<MarketItem> byPriceLow = getItems();
		Collections.sort(byPriceLow, new Comparator<MarketItem>() {

			@Override
			public int compare(MarketItem i1, MarketItem i2) {
				return new Double(i1.getPrice()).compareTo(i2.getPrice());
			}
	    });
		return byPriceLow;
	}
}
