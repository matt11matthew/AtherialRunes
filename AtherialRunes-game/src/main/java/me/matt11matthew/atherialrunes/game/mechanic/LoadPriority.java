package me.matt11matthew.atherialrunes.game.mechanic;

public enum LoadPriority {

	LOWEST(6),
	LOW(5),
	NORMAL(4),
	HIGH(3),
	HIGHEST(2),
	MONITOR(1);
	
	int priority;
	
	LoadPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return this.priority;
	}
}
