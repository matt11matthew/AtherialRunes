package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item;

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

    /**
     *
     * @param item the custom itemstack
     */
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

    /**
     *
     * @param file the file for the custom item
     */
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

    /**
     *
     * @return the item dura
     */
    public short getDurability() {
        return durability;
    }

    /**
     *
     * @param durability the dura the item dura is getting set to
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
     * @param amount amount getting set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     *
     * @return custom items name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name the name item will be set to
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
     * @param lore a line of lore that is added
     */
    public void addLore(String lore) {
        item_lore.add(Utils.colorCodes(lore));
    }

    /**
     *
     * @param item_lore the lore of the item is set
     */
    public void setLore(List<String> item_lore) {
        this.item_lore = item_lore;
    }

    /**
     *
     * @return type
     */
    public Material getType() {
        return type;
    }

    /**
     *
     * @param type sets type
     */
    public void setType(Material type) {
        this.type = type;
    }

    /**
     *
     * @return the item built
     */
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

    /**
     *
     * @return the item file
     */
    public File getFile() {
        return file;
    }
}