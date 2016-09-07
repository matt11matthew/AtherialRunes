package me.matt11matthew.atherialrunes.sound;

import org.bukkit.entity.Player;

public class AtherialSound {
	
	private EnumSound sound;
	private float volume;
	private float pitch;

	/**
	 *
	 * @param sound the sound
	 * @param volume the volume
	 * @param pitch the pitch
     */
	public AtherialSound(EnumSound sound, float volume, float pitch) {
		this.volume = volume;
		this.pitch = pitch;
		this.sound = sound;
	}

	/**
	 *
	 * @param player play sound to
     */
	public void play(Player player) {
		player.playSound(player.getLocation(), sound.getSound(), volume, pitch);
	}

	/**
	 *
	 * @return the sound
     */
	public EnumSound getSound() {
		return sound;
	}

	/**
	 *
	 * @param sound sets the sound
     */
	public void setSound(EnumSound sound) {
		this.sound = sound;
	}

	/**
	 *
	 * @return the pitch
     */
	public float getPitch() {
		return pitch;
	}

	/**
	 *
	 * @param pitch sets the pitch
     */
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	/**
	 *
	 * @return volume
     */
	public float getVolume() {
		return volume;
	}

	/**
	 *
	 * @param volume sets the volume
     */
	public void setVolume(float volume) {
		this.volume = volume;
	}
}
