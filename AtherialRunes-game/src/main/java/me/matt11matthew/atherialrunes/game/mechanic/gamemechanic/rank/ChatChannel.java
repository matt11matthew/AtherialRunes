package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank;

public enum ChatChannel {
	
	LOCAL(1, "&7&l<LOCAL>"),
	GLOBAL(2, "&e&l<G>"),
	TRADE(3, "&a&l<T>");
	
	int id;
	String prefix;
	
	ChatChannel(int id, String prefix) {
		this.id = id;
		this.prefix = prefix;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPrefix() {
		return prefix;
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
