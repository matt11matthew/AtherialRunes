package me.matt11matthew.atherialrunes;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.logging.Logger;

public class Constants {
	
	public static final String SERVER_VERSION = "1.0D";
	public static final String BUILD = "1";
	
	public static final String[] DEVELOPERS = {"matt11matthew"};
	public static final String[] GLOBAL_ADMINS = {"Jackychen1999", "Incinerator64"};

	public static final String DEFAULT_TOGGLES_STRING = "debug,pvp,";

	/**
	 *
	 * @param name the players name
	 * @return if they are or not
	 */
	public static boolean isGlobalAdmin(String name) {
		return Arrays.asList(GLOBAL_ADMINS).contains(name);
	}

	/**
	 *
	 * @param name the players name
	 * @return if they are or not
     */
	public static boolean isDeveloper(String name) {
		return Arrays.asList(DEVELOPERS).contains(name);
	}

	public static final boolean DEBUG = true;
	public static final boolean MAINTENANCE = false;
	public static final boolean DEV_SERVER = false;
	public static final boolean DISABLE = false;

	public static final int MASTER_SERVER_PORT = 45678;
	public static final String MASTER_SERVER_IP = "localhost";

	public static final String DATE = "9/12/2016";
	
	public static final String WEBSITE_LINK = "www.atherialrunes.net";
	
	public static final String MOTD = "&aAtherial Runes  &7Patch " + SERVER_VERSION + " \n&cBETA - &f&l" + WEBSITE_LINK;
    public static final String MAINTENANCE_MOTD = "&aAtherial Runes &8- &cUndergoing Maintenance     \n                &8- &f&n" + WEBSITE_LINK + " &8-";
	public static final String DEV_MOTD = "&f&lAtherial Runes &8- &6Undergoing Development     \n                &8- &f&n" + WEBSITE_LINK + " &8-";
	
	public static final Location LOBBY_SPAWN = Bukkit.getWorld("hub").getSpawnLocation();

	public static final String DISCORD_SERVER_ID = "216389507572957184";
	public static final String DISCORD_BOT_TOKEN = "MjIyNDA3NjY3OTA4MzQ1ODU3.Cq880Q.FHIKaK8fJQCq3BzV4NYR_6vcaf4";
	
	public static Logger log = Logger.getLogger(Constants.class.getSimpleName());
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static final String FTP_HOST_NAME = "192.99.37.67";
	public static final String FTP_USER_NAME = "root";
	public static final String FTP_PASSWORD = "ggfx1";
	public static final int FTP_PORT = 22;

	public static final String SQL_URL = "jdbc:mysql://192.99.37.67:3306/atherialrunes";//"jdbc:mysql://localhost:3306/atherialrunes";
	public static final String SQL_USER = "discord";//"matt11matthew";
	public static final String SQL_PASSWORD = "discord";

	public static final String REMOTE_SQL_URL = "jdbc:mysql://192.99.37.67:3306/atherialrunes";
	public static final String REMOTE_SQL_USER = "mattremote";
	public static final String REMOTE_SQL_PASSWORD = "pass";
}