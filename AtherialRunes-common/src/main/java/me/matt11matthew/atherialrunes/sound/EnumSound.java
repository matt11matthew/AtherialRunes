package me.matt11matthew.atherialrunes.sound;

import org.bukkit.Sound;

public enum EnumSound {
	
	LEVEL_UP(Sound.ENTITY_PLAYER_LEVELUP),
	HURT(Sound.ENTITY_GENERIC_HURT);
	
	Sound sound;

	/**
	 *
	 * @param sound the sound
     */
	EnumSound(Sound sound) {
		this.sound = sound;
	}

	/**
	 *
	 * @return returns the sound
     */
	public Sound getSound() {
		return sound;
	}
}