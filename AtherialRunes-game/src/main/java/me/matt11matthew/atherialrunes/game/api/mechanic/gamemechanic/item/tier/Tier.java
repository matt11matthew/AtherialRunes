package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.tier;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.MarketItem;

public enum Tier {

	TIER_1(1, "T1", "&f"),
	TIER_2(2, "T2", "&7"),
	TIER_3(3, "T3", "&3"),
	TIER_4(4, "T4", "&c");
	
	int tier;
	String prefix;
	String color;
	
	Tier(int tier, String prefix, String color) {
		this.tier = tier;
		this.prefix = prefix;
		this.color = color;
	}
	
	public int getTier() {
		return tier;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getColor() {
		return color;
	}
	
	public static Tier getTier(MarketItem item) {
		if (item.getName().contains("&f")) {
			return Tier.TIER_1;
		} else if (item.getName().contains("&7")) {
			return Tier.TIER_2;
		} else if (item.getName().contains("&3")) {
			return Tier.TIER_3;
		} else if (item.getName().contains("&c")) {
			return Tier.TIER_4;
		} 
		return null;
	}
}
