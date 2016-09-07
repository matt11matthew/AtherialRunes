package me.matt11matthew.atherialrunes.game.utils;

public class IntegerUtils {

	/**
	 *
	 * @param s the string
	 * @return if its a int or not
     */
	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
