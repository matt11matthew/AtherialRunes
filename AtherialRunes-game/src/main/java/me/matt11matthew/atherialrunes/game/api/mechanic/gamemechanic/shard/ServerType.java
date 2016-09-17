package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard;

public enum ServerType {

	DEVELOPER("Developer", "&6&lDevelopment Server"),
	YOUTUBE("Youtube", "&f&lYou&ctube &cServer"),
	LIVE("Live", "&a&lLive Server");
	
	String name;
	String prefix;
	
	ServerType(String name, String prefix) {
		this.name = name;
		this.prefix = prefix;
	}
	
	public String getName() {
		return name;
	}

	public String getPrefix() {
		return prefix;
	}

	public static ServerType getServerTypeFromName(String name) {
		for (ServerType serverType : ServerType.values()) {
			if (serverType.name.equals(name)) {
				return serverType;
			}
		}
		return null;
	}
}
