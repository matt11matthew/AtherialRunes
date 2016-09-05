package me.matt11matthew.atherialrunes.server.shard.enums;

public enum ShardType {
	
	LIVE(1, "Live"),
	BETA(2, "Beta"),
	DEV(3, "Dev"),
	SUB(4, "Sub");
	
	int id;
	String name;
	
	ShardType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

}
