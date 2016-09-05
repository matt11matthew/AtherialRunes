package me.matt11matthew.atherialrunes.sound;

import org.bukkit.Sound;

public enum EnumSound {
	
	LEVEL_UP(Sound.ENTITY_PLAYER_LEVELUP);
	
	Sound sound;
	
	EnumSound(Sound sound) {
		this.sound = sound;
	}
	
	public Sound getSound() {
		return sound;
	}
}