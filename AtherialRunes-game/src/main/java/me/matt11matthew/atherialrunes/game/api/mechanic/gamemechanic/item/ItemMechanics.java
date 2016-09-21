package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item;


import me.matt11matthew.atherialrunes.exceptions.NotJsonFileException;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.commands.ReloadException;
import me.matt11matthew.atherialrunes.game.utils.json.JSONUtils;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ItemMechanics extends ListenerMechanic {

    public static HashMap<String, CustomItem> customItems = new HashMap<String, CustomItem>();

    @Override
    public void onEnable() {
        registerListeners();
        print("-----------------------------------------");
        print("[ItemMechanics] Enabling...");
        print("-----------------------------------------");
        loadCustomItems();
    }

    @Override
    public void onDisable() {
        print("-----------------------------------------");
        print("[ItemMechanics] Disabling...");
        print("-----------------------------------------");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.MONITOR;
    }

    /**
     * loads custom items
     */
    private static void loadCustomItems() {
        File itemFile = new File(Main.getInstance().getDataFolder() + "/custom_items/", "items.json");

        JSONObject obj = null;
        try {
            obj = JSONUtils.convertStringToJSONObject(itemFile);
        } catch (NotJsonFileException e) {
            e.printStackTrace();
        }
        List<String> items = (JSONArray) obj.get("customitems");
        for (String item : items) {
            Main.print(item);
            CustomItem customItem = new CustomItem(item, obj);
            customItems.put(item, customItem);
        }
    }

    public static void reload() throws ReloadException {
        customItems.clear();
        try {
            loadCustomItems();
        } catch (Exception e) {
            throw new ReloadException("ItemMechanics");
        }
    }

    public static ItemStack getCustomItem(String template_name) {
        File template = new File(Main.getInstance().getDataFolder() + "/custom_items/" + template_name + ".item");
        if (!(template.exists())) {
            return null; // No such custom template!
        }

        int item_id = -1;
        String item_name = "";
        List<String> item_lore = new ArrayList<String>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(template));

            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("item_name=")) {
                    line = line.replaceAll("&0", ChatColor.BLACK.toString());
                    line = line.replaceAll("&1", ChatColor.DARK_BLUE.toString());
                    line = line.replaceAll("&2", ChatColor.DARK_GREEN.toString());
                    line = line.replaceAll("&3", ChatColor.DARK_AQUA.toString());
                    line = line.replaceAll("&4", ChatColor.DARK_RED.toString());
                    line = line.replaceAll("&5", ChatColor.DARK_PURPLE.toString());
                    line = line.replaceAll("&6", ChatColor.GOLD.toString());
                    line = line.replaceAll("&7", ChatColor.GRAY.toString());
                    line = line.replaceAll("&8", ChatColor.DARK_GRAY.toString());
                    line = line.replaceAll("&9", ChatColor.BLUE.toString());
                    line = line.replaceAll("&a", ChatColor.GREEN.toString());
                    line = line.replaceAll("&b", ChatColor.AQUA.toString());
                    line = line.replaceAll("&c", ChatColor.RED.toString());
                    line = line.replaceAll("&d", ChatColor.LIGHT_PURPLE.toString());
                    line = line.replaceAll("&e", ChatColor.YELLOW.toString());
                    line = line.replaceAll("&f", ChatColor.WHITE.toString());

                    line = line.replaceAll("&u", ChatColor.UNDERLINE.toString());
                    line = line.replaceAll("&s", ChatColor.BOLD.toString());
                    line = line.replaceAll("&i", ChatColor.ITALIC.toString());
                    line = line.replaceAll("&m", ChatColor.MAGIC.toString());

                    item_name = line.substring(line.indexOf("=") + 1, line.length());
                } else if (line.startsWith("item_id=")) {
                    item_id = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.length()));
                } else {
                    // It's lore!
                    line = line.replaceAll("&0", ChatColor.BLACK.toString());
                    line = line.replaceAll("&1", ChatColor.DARK_BLUE.toString());
                    line = line.replaceAll("&2", ChatColor.DARK_GREEN.toString());
                    line = line.replaceAll("&3", ChatColor.DARK_AQUA.toString());
                    line = line.replaceAll("&4", ChatColor.DARK_RED.toString());
                    line = line.replaceAll("&5", ChatColor.DARK_PURPLE.toString());
                    line = line.replaceAll("&6", ChatColor.GOLD.toString());
                    line = line.replaceAll("&7", ChatColor.GRAY.toString());
                    line = line.replaceAll("&8", ChatColor.DARK_GRAY.toString());
                    line = line.replaceAll("&9", ChatColor.BLUE.toString());
                    line = line.replaceAll("&a", ChatColor.GREEN.toString());
                    line = line.replaceAll("&b", ChatColor.AQUA.toString());
                    line = line.replaceAll("&c", ChatColor.RED.toString());
                    line = line.replaceAll("&d", ChatColor.LIGHT_PURPLE.toString());
                    line = line.replaceAll("&e", ChatColor.YELLOW.toString());
                    line = line.replaceAll("&f", ChatColor.WHITE.toString());

                    line = line.replaceAll("&u", ChatColor.UNDERLINE.toString());
                    line = line.replaceAll("&s", ChatColor.BOLD.toString());
                    line = line.replaceAll("&i", ChatColor.ITALIC.toString());
                    line = line.replaceAll("&m", ChatColor.MAGIC.toString());

                    if (line.contains("(")) {
                        // Number range!
                        String line_copy = line;
                        for (String s : line_copy.split("\\(")) {
                            if (!(s.contains("~"))) {
                                continue;
                            }
                            int lower = Integer.parseInt(s.substring(0, s.indexOf("~")));
                            int upper = Integer.parseInt(s.substring(s.indexOf("~") + 1, s.indexOf(")")));

                            int val = new Random().nextInt((upper - lower)) + lower;
                            line = line.replace("(" + lower + "~" + upper + ")", String.valueOf(val));
                        }
                    }

                    item_lore.add(line);
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemStack is = new ItemStack(Material.getMaterial(item_id), 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(item_name);
        im.setLore(item_lore);
        is.setItemMeta(im);

//        String rarity = ItemMechanics.generateItemRarity(is);
//        if (rarity != null) {
//            // Add rarity if needed.
//            item_lore.add(rarity);
//            im.setLore(item_lore);
//            is.setItemMeta(im);
//        }

        return is;
    }

    public static ItemStack createItem(Material m, short meta_data, String name, String desc) {
        ItemStack is = new ItemStack(m, 1, meta_data);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Utils.colorCodes(name));
        List<String> new_lore = new ArrayList<>();
        if (desc.contains(",")) {
            for (String s : desc.split(",")) {
                new_lore.add(Utils.colorCodes(s));
            }
        } else {
            new_lore.add(Utils.colorCodes(desc));
        }
        im.setLore(new_lore);
        is.setItemMeta(im);

        return is;
    }
}
  