package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.shard;

public enum ServerType {

	DEVELOPER("Developer"),
	LIVE("Live");
	
	String name;
	
	ServerType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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
