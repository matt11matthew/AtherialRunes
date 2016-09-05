package me.matt11matthew.atherialrunes.game.utils;

public class IntegerUtils {

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
