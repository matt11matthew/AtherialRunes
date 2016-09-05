package me.matt11matthew.atherialrunes.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocalPlayer {

	private String name;
	private String uuid;
	private int level;
	private int foodLevel;
	private String inventory;
	private String location;
	private int hp;
	private int maxHP;
	
	public LocalPlayer(Player player) {
		this.name = player.getName();
		this.uuid = player.getUniqueId().toString();
	}
	
	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setLocation(String loc) {
		this.location = loc;
	}
	
	public void setInventory(String inv) {
		this.inventory = inv;
	}
	
	public String getLocation() {
		return location;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	public String getInventory() {
		return inventory;
	}

	public String getUUID() {
		return uuid;
	}
	
	public void load(Player player) {
		player.setLevel(getLevel());
		player.setMaxHealth(getMaxHP());
		player.setHealth(getHP());
		player.setFoodLevel(getFoodLevel());
		player.teleport(parseLocation(getLocation()));
		//TODO load inventory
	}

	private Location parseLocation(String l) {
		String world = l.split("world:")[1].split(",")[0];
		double x = Double.parseDouble(l.split("x:")[1].split(",")[0]);
		double y = Double.parseDouble(l.split("y:")[1].split(",")[0]);
		double z = Double.parseDouble(l.split("z:")[1].split(",")[0]);
		float pitch = Float.parseFloat(l.split("pitch:")[1].split(",")[0]);
		float yaw = Float.parseFloat(l.split("yaw:")[1].split(",")[0]);
		return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getHP() {
		return hp;
	}

	public void setHP(int hp) {
		this.hp = hp;
	}
}
