package me.matt11matthew.atherialrunes.utils;

import me.matt11matthew.atherialrunes.database.data.player.Mute;

public class MuteUtils {
	
	public static void mute(String name, String reason, String by, long time) {
		Mute mute = Mute.getMute(name);
		mute.setReason(reason);
		mute.setBy(by);
		mute.setTime(time);
		Mute.save(mute);
	}
	
	public static void unmute(String name, String reason) {
		Mute mute = Mute.getMute(name);
		mute.setUnmuteReason(reason);
		mute.setTime(0);
		Mute.save(mute);
	}
}
