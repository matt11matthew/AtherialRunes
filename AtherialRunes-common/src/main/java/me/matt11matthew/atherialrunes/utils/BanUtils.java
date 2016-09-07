package me.matt11matthew.atherialrunes.utils;

import me.matt11matthew.atherialrunes.database.data.player.Ban;

public class BanUtils {

	/**
	 *
	 * @param ign the ign getting banned
	 * @param reason the ban reason
	 * @param by who banned
     * @param time ban time
     */
	public static void ban(String ign, String reason, String by, long time) {
		Ban ban = Ban.getBan(ign);
		ban.setReason(reason);
		ban.setBy(by);
		ban.setTime(time);
		Ban.save(ban);
	}

	/**
	 *
	 * @param ign the ign getting unbanned
	 * @param reason unban reason
     */
	public static void unban(String ign, String reason) {
		Ban ban = Ban.getBan(ign);
		ban.setUnbanReason(reason);
		ban.setTime(0);
		Ban.save(ban);
	}
}
