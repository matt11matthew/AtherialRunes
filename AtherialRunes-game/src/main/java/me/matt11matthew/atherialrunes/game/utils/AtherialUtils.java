package me.matt11matthew.atherialrunes.game.utils;

import me.matt11matthew.atherialrunes.game.Main;

public class AtherialUtils {

	/**
	 *
	 * @param name the player name
	 * @return if they are a player or not
     */
	public static boolean isPlayer(String name) {
		try {
			Main.getGamePlayer(name);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
