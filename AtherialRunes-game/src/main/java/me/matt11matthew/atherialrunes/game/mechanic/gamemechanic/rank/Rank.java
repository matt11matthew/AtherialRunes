package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import me.matt11matthew.atherialrunes.database.data.player.UUIDData;
import me.matt11matthew.atherialrunes.game.player.GamePlayer;
import me.matt11matthew.atherialrunes.utils.Utils;

public enum Rank {
	
	LEAD_DEVELOPER(1, "&6&lLEAD DEV &7", ":&f "),
	LEAD(2, "&a&lLEAD &7", ":&f "),
	DEVELOPER(3, "&c&lDEV &7", ":&f "),
	GA(4, "&e&lGA &7", ":&f "),
	DEFAULT(5, "&7", ": "),
	APPRENTICE(6, "&a&lA &7", ":&f "),
	KNIGHT(7, "&3&lK &7", ":&f "),
	NOBEL(8, "&d&lN &7", ":&f "),
	FOUNDER(9, "&b&lF &7", ":&f "),
	MOD(10, "&6&lMOD &7", ":&f "),
	BUILDER(11, "&3&lBUILDER &7", ":&f "),
	YOUTUBER(12, "&c&lYT &7", ":&f ");
	
	int id;
	String tag;
	String chatColor;

	Rank(int id, String tag, String chatColor) {
		this.id = id;
		this.tag = tag;
		this.chatColor = chatColor;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getChatColor() {
		return this.chatColor;
	}
	
	public String getTag() {
		return tag;
	}
	
	public String getChatMessage(String name, String msg) {
		return Utils.colorCodes(getTag() + name + chatColor + ChatColor.stripColor(msg));
	}
	
	public String getTabListName(String name) {
		return Utils.colorCodes(tag + name);
	}
	
	public static boolean isDeveloper(String name) {
		Rank rank = GamePlayer.players.get(UUIDData.getUUID(name)).getRank();
		return ((rank == DEVELOPER) || (rank == LEAD_DEVELOPER));
	}
	
	public static boolean isGM(String name) {
		Rank rank = GamePlayer.players.get(UUIDData.getUUID(name)).getRank();
		return ((rank == DEVELOPER) || (rank == GA) || (rank == LEAD_DEVELOPER) || (rank == LEAD));
	}
	
	public static boolean isStaff(String name) {
		Rank rank = GamePlayer.players.get(UUIDData.getUUID(name)).getRank();
		return ((rank == DEVELOPER) || (rank == GA) || (rank == MOD) || (rank == BUILDER) || (rank == Rank.LEAD_DEVELOPER) || (rank == LEAD));
	}
	
	public static List<String> getRanks() {
		List<String> ranks = new ArrayList<String>();
		for (Rank rank : values()) {
			ranks.add(rank.toString());
		}
		return ranks;
	}
}