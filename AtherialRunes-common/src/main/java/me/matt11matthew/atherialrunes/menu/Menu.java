package me.matt11matthew.atherialrunes.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.matt11matthew.atherialrunes.utils.Utils;

public abstract class Menu {
	
	public Inventory inventory;
	
	private int slots = 9;
	private String title = "title";
	
	public Menu(String title, int slots) {
		this.title = title;
		this.slots = slots;
		inventory = Bukkit.createInventory(null, slots, Utils.colorCodes(title));
	}
	
	public Menu(String title) {
		this(title, 9);
	}
	
	public Menu(int slots) {
		this("title", slots);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void addItem(ItemStack item) {
		inventory.addItem(item);
	}
	
	public void setItem(int slot, ItemStack item) {
		inventory.setItem(slot, item);
	}
	
	public void removeItem(ItemStack item) {
		inventory.removeItem(item);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}
	
	public void open(Player player) {
		player.openInventory(inventory);
	}
}
