package me.matt11matthew.atherialrunes.game;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.utils.Utils;

public class GameConstants {

	public static final int COMBAT_TIME = 10;
	public static final String IN_COMBAT_MESSAGE = "&cNow in &lCOMBAT";
	public static final String OUT_COMBAT_MESSAGE = "&aLeaving &lCOMBAT";

	public static final String CURRENTLY_HIDDEN_MESSAGE = "&cYou're currently &lHIDDEN";

	public static final int MAX_MARKET_ITEMS_PER_PAGE = 35;

	public static final int NOTORIETY_LOSS_IN_MORNING = 3;
	public static final long MORNING_AT_TIME = 0L;

	public static final String WORLD_NAME = "AtherialRunes";

	public static final String SERVER_REBOOTING_KICK_MESSAGE = Utils.colorCodes("&aThe Server is Rebooting \n&7&n" + Constants.WEBSITE_LINK);
}
