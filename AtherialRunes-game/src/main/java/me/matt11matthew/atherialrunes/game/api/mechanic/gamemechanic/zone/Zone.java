package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.zone;

import me.matt11matthew.atherialrunes.utils.Utils;

public enum Zone {

	SAFE("&a&l                *** SAFE ZONE (DMG-OFF) ***"),
	WILDERNESS("&e&l           *** WILDERNESS (MOBS-ON, PVP-OFF) ***"),
	WAR("&c&l                *** WAR ZONE (PVP-ON) ***");
	
	String message;
	
	Zone(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return Utils.colorCodes(message);
	}
}