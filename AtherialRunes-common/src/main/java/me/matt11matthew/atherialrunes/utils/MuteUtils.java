package me.matt11matthew.atherialrunes.utils;

import me.matt11matthew.atherialrunes.database.data.player.Mute;

public class MuteUtils {

	/**
	 *
	 * @param name the name of player getting muted
	 * @param reason the mute reason
	 * @param by who muted
     * @param time mute time
     */
	public static void mute(String name, String reason, String by, long time) {
		Mute mute = Mute.getMute(name);
		mute.setReason(reason);
		mute.setBy(by);
		mute.setTime(time);
		Mute.save(mute);
	}

	/**
	 *
	 * @param name the player name getting unmuted
	 * @param reason the unmute reason
     */
	public static void unmute(String name, String reason) {
		Mute mute = Mute.getMute(name);
		mute.setUnmuteReason(reason);
		mute.setTime(0);
		Mute.save(mute);
	}
}
