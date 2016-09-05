package me.matt11matthew.atherialrunes.item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.matt11matthew.atherialrunes.utils.Utils;

public class AtherialItem {

	private ItemStack itemStack = null;
	
	public AtherialItem(Material type, short dura, int amount) {
		itemStack = new ItemStack(type, amount, dura);
	}
	
	public AtherialItem(Material type) {
		this(type, (short) 0, 1);
	}
	
	public AtherialItem(Material type, short dura) {
		this(type, dura, 1);
	}
	
	public AtherialItem(Material type, int amount) {
		this(type, (short) 0, amount);
	}
	
	public int getAmount() {
		return itemStack.getAmount();
	}
	
	public void setAmount(int amount) {
		itemStack.setAmount(amount);
	}
	
	public void setLore(List<String> lore) {
		ItemMeta im = itemStack.getItemMeta();
		im.setLore(lore);
		itemStack.setItemMeta(im);
	}
	
	public void addLore(String lore) {
		ItemMeta im = itemStack.getItemMeta();
		List<String> itemLore = null;
		if (!im.hasLore()) {
			itemLore = new ArrayList<String>();
		} else {
			itemLore = im.getLore();
		}
		itemLore.add(Utils.colorCodes(lore));
		im.setLore(itemLore);
		itemStack.setItemMeta(im);
	}
	
	public void addItemFlag(ItemFlag itemFlag) {
		ItemMeta im = itemStack.getItemMeta();
		im.addItemFlags(itemFlag);
		itemStack.setItemMeta(im);
	}
	
	public void setLoreline(int line, String lore) {
		ItemMeta im = itemStack.getItemMeta();
		List<String> itemLore = null;
		if (!im.hasLore()) {
			itemLore = new ArrayList<String>();
		} else {
			itemLore = im.getLore();
		}
		itemLore.set(line, Utils.colorCodes(lore));
		im.setLore(itemLore);
		itemStack.setItemMeta(im);
	}
	
	public void setName(String name) {
		ItemMeta im = itemStack.getItemMeta();
		im.setDisplayName(Utils.colorCodes(name));
		itemStack.setItemMeta(im);
	}
	
	public ItemStack build() {
		return itemStack;
	}
}
