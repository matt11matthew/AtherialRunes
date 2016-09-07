package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus;

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

	/**
	 *
	 * @param item the item
     */
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

	/**
	 *
	 * @return seller
     */
	public String getSeller() {
		return seller;
	}

	/**
	 *
	 * @param seller sets the seller
     */
	public void setSeller(String seller) {
		this.seller = seller;
	}

	/**
	 *
	 * @return the price
     */
	public double getPrice() {
		return price;
	}

	/**
	 *
	 * @return the item uuid
     */
	public UUID getUUID() {
		return uuid;
	}

	/**
	 *
	 * @param price sets the price
     */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 *
	 * @param uuid sets the uuid
     */
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 *
	 * @return returns durability
     */
	public short getDurability() {
		return durability;
	}

	/**
	 *
	 * @param durability what durability is set to
     */
	public void setDurability(short durability) {
		this.durability = durability;
	}

	/**
	 *
	 * @return item amount
     */
	public int getAmount() {
		return amount;
	}

	/**
	 *
	 * @param amount amount set to
     */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 *
	 * @return item name
     */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name sets item name
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return items lore
     */
	public List<String> getLore() {
		return item_lore;
	}

	/**
	 *
	 * @param lore adds lore to item
     */
	public void addLore(String lore) {
		item_lore.add(Utils.colorCodes(lore));
	}

	/**
	 *
	 * @param item_lore sets itemlore to itemlore
     */
	public void setLore(List<String> item_lore) {
		this.item_lore = item_lore;
	}

	/**
	 *
	 * @return item type
     */
	public Material getType() {
		return type;
	}

	/**
	 *
	 * @param type sets item type
     */
	public void setType(Material type) {
		this.type = type;
	}

	/**
	 *
	 * @return the raw itemstack
     */
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

	/**
	 *
	 * @return the market itemstack
     */
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