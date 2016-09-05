package me.matt11matthew.atherialrunes.sound;

import org.bukkit.entity.Player;

public class AtherialSound {
	
	private EnumSound sound;
	private float volume;
	private float pitch;
	
	public AtherialSound(EnumSound sound, float volume, float pitch) {
		this.volume = volume;
		this.pitch = pitch;
		this.sound = sound;
	}

	public void play(Player player) {
		player.playSound(player.getLocation(), sound.getSound(), volume, pitch);
	}

	public EnumSound getSound() {
		return sound;
	}

	public void setSound(EnumSound sound) {
		this.sound = sound;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}
}
