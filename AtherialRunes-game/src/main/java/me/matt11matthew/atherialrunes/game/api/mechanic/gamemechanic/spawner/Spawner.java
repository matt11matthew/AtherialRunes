package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner;

import org.bukkit.Location;

public class Spawner {
	
	private Location location;
	private int cooldown;
	private int range;
	private boolean active;
	private String mob;
	
	public Spawner(Location location, int cooldown, int range) {
		this.location = location;
		this.cooldown = cooldown;
		this.range = range;
		this.active = false;
		this.mob = "T1";
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}
}
