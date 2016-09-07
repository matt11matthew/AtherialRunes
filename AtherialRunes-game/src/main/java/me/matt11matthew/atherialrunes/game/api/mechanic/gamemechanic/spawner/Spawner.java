package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner;

import org.bukkit.Location;

public class Spawner {
	
	private Location location;
	private int cooldown;
	private int range;
	private boolean active;
	private String mob;
	private boolean elite;
	private int tier;
	
	public Spawner(Location location, int cooldown, int range, boolean elite, String mob, int tier) {
		this.location = location;
		this.cooldown = cooldown;
		this.range = range;
		this.active = false;
		this.mob = mob;
		this.tier = tier;
		this.elite = elite;
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

	public boolean isElite() {
		return elite;
	}

	public void setElite(boolean elite) {
		this.elite = elite;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}
}
