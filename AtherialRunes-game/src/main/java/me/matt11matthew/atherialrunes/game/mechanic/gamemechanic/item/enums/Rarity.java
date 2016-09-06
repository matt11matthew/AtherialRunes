package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.item.enums;

import me.matt11matthew.atherialrunes.utils.Utils;

public enum Rarity {
	
	NORMAL(1, "&fNormal", "Normal"),
	UNUSUAL(1, "&eUnusual", "Unusual"),
	EPIC(1, "&aEpic", "Epic"),
	LEGENDARY(1, "&3Legendary", "Legendary");
	
	int id;
	String lore;
	String name;
	
	Rarity(int id, String lore, String name) {
		this.id = id;
		this.lore = lore;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public String getLore() {
		return Utils.colorCodes(lore);
	}
	
	public String getName() {
		return name;
	}
}
