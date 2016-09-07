package me.matt11matthew.atherialrunes.player;

import me.matt11matthew.atherialrunes.item.ItemSerialization;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LocalPlayer {

	private String name;
	private String uuid;
	private int level;
	private int foodLevel;
	private String inventory;
	private String location;
	private int hp;
	private int maxHP;
	private List<String> armor;
	
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
		String playerInv = getInventory();
		if (playerInv != null && playerInv.length() > 0 && !playerInv.equalsIgnoreCase("null")) {
			ItemStack[] items = ItemSerialization.fromString(playerInv, 36).getContents();
			player.getInventory().setContents(items);
			player.updateInventory();
		}
		List<String> playerArmor = (ArrayList<String>) getArmor();
		int i = -1;
		ItemStack[] armorContents = new ItemStack[4];
		ItemStack offHand = new ItemStack(Material.AIR);
		for (String armor : playerArmor) {
			i++;
			if (i <= 3) { //Normal armor piece
				if (armor.equals("null") || armor.equals("")) {
					armorContents[i] = new ItemStack(Material.AIR);
				} else {
					armorContents[i] = ItemSerialization.itemStackFromBase64(armor);
				}
			} else {
				if (armor.equals("null") || armor.equals("")) {
					offHand = new ItemStack(Material.AIR);
				} else {
					offHand = ItemSerialization.itemStackFromBase64(armor);
				}
			}
		}
		player.getEquipment().setArmorContents(armorContents);
		player.getEquipment().setItemInOffHand(offHand);

		player.updateInventory();
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

	public List<String> getArmor() {
		return armor;
	}

	public void setArmor(List<String> armor) {
		this.armor = armor;
	}
}
