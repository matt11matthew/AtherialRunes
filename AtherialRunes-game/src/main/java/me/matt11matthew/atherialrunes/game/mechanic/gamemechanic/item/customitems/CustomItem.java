package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.item.customitems;

import me.matt11matthew.atherialrunes.exceptions.NotJsonFileException;
import me.matt11matthew.atherialrunes.utils.Utils;
import me.matt11matthew.atherialrunes.utils.json.JSONUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomItem {

    private List<String> item_lore = new ArrayList<String>();
    private String name = null;
    private int amount = 1;
    private short durability = 0;
    private Material type = Material.DIRT;
    private File file;

    public CustomItem(ItemStack item) {
        this.file = null;
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

    public CustomItem(File file) {
        JSONObject obj = null;
        try {
            obj = JSONUtils.convertStringToJSONObject(file);
        } catch (NotJsonFileException e) {
            e.printStackTrace();
        }
        this.file = file;
        String type = (String) obj.get("material");
        String name = (String) obj.get("name");
        short durability = (short) obj.get("durability");
        List<String> lore = new ArrayList<>();
        String rawLore = obj.get("lore").toString();
        for (String l : rawLore.split("/")) {
            lore.add(Utils.colorCodes(l));
        }
        this.type = Material.valueOf(type.toUpperCase());
        setName(name);
        setLore(lore);
        this.durability = durability;
        this.amount = 1;
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

    public ItemStack buildItem() {
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

    public File getFile() {
        return file;
    }
}