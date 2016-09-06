package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.auctionhouse.menus;

import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MarketItem {

	private String seller = null;
	private double price = 0.0D;
	private UUID uuid = null;
	private List<String> item_lore = new ArrayList<String>();
	private String name = null;
	private int amount = 1;
	private short durability = 0;
	private Material type = Material.DIRT;
	
	public MarketItem(ItemStack item) {
		this.type = item.getType();
		this.durability = item.getDurability();
		this.amount = item.getAmount();
		if (item.hasItemMeta()) {
			ItemMeta im = item.getItemMeta();
			if (im.hasLore()) {
				setLore(im.getLore());
			}
			if (im.hasDisplayName()) {
				setName(im.getDisplayName());
			}
		}
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public double getPrice() {
		return price;
	}
	
	public UUID getUUID() {
		return uuid;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	public short getDurability() {
		return durability;
	}

	public void setDurability(short durability) {
		this.durability = durability;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLore() {
		return item_lore;
	}
	
	public void addLore(String lore) {
		item_lore.add(Utils.colorCodes(lore));
	}

	public void setLore(List<String> item_lore) {
		this.item_lore = item_lore;
	}

	public Material getType() {
		return type;
	}

	public void setType(Material type) {
		this.type = type;
	}
	
	public ItemStack buildRawItem() {
		ItemStack item = new ItemStack(type);
		ItemMeta im = item.getItemMeta();
		if (!item_lore.isEmpty()) {
			im.setLore(item_lore);
		}
		if (name != null) {
			im.setDisplayName(Utils.colorCodes(name));
		}
		item.setItemMeta(im);
		item.setAmount(amount);
		item.setDurability(durability);
		return item;
	}
	
	public ItemStack buildItem() {
		ItemStack item = new ItemStack(type);
		ItemMeta im = item.getItemMeta();
		if (price > 0.0D) {
			addLore("&bPrice: &f" + (int) price);
		}
		if (seller != null) {
			addLore("&bSeller: &f" + getSeller());
		}
		if (uuid != null) {
			addLore("&7" + uuid);
		}
		if (!item_lore.isEmpty()) {
			im.setLore(item_lore);
		}
		if (name != null) {
			im.setDisplayName(Utils.colorCodes(name));
		}
		item.setItemMeta(im);
		item.setAmount(amount);
		item.setDurability(durability);
		return item;
	}
}