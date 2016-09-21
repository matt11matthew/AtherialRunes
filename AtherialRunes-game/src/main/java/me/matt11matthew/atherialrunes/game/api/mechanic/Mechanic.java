package me.matt11matthew.atherialrunes.game.api.mechanic;

import me.matt11matthew.atherialrunes.game.Main;

public abstract class Mechanic  {
	public abstract void onEnable();
	
	public abstract void onDisable();
	
	public abstract LoadPriority getLoadPriority();
	
	public void enable() {
		onEnable();
	}
	
	public void disable() {
		onDisable();
	}
	
	public void print(String s) {
		Main.print(s);
	}
}
