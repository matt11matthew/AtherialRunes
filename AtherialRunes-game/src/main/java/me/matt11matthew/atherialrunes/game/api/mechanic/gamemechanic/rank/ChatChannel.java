package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank;

public enum ChatChannel {
	
	LOCAL(1, "&7<Local>", "Local"),
	GLOBAL(2, "&7<&bGlobal&7>", "Global"),
	TRADE(3, "&7<&aTrade&7>", "Trade"),
	GUILD(4, "&7<&3Guild&7>", "Guild");
	
	int id;
	String prefix;
	String name;
	
	ChatChannel(int id, String prefix, String name) {
		this.id = id;
		this.prefix = prefix;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getName() {
		return name;
	}
	
	public static ChatChannel getChatChannelFromId(int id) {
		for (int i = 0; i < ChatChannel.values().length; i++) {
			if (ChatChannel.values()[i].getId() == id) {
				return ChatChannel.values()[i];
			}
		}
		return ChatChannel.LOCAL;
	}
}
