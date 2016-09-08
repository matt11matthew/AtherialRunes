package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.armor.ArmorStat;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.item.ItemStat;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StatUtils {

    public static boolean hasStat(ItemStack item, ItemStat stat) {
        List<String> itemLore = (item.getItemMeta().hasLore()) ? item.getItemMeta().getLore() : new ArrayList<>();
        for (String lore : itemLore) {
            return (itemLore.contains(Utils.colorCodes(stat.getLore())));
        }
        return false;
    }

    public static boolean hasStat(ItemStack item, ArmorStat stat) {
        List<String> itemLore = (item.getItemMeta().hasLore()) ? item.getItemMeta().getLore() : new ArrayList<>();
        for (String lore : itemLore) {
            return (itemLore.contains(Utils.colorCodes(stat.getLore())));
        }
        return false;
    }

    private static String getStringFromLore(ItemStack item, String value, int line) {
        String returnVal = "";
        line = (line - 1);
        ItemMeta meta = item.getItemMeta();
        value = ChatColor.stripColor(value);
        try {
            List<String> lore = meta.getLore();
            if (lore != null) {

                for (int i = 0; i < lore.size(); i++) {
                    if (((String)lore.get(line)).contains(value)) {

                        String vals = ((String)lore.get(line)).split(value)[1];
                        vals = ChatColor.stripColor(vals).trim();
                        vals = vals.replaceAll(" ", "");
                        returnVal = vals;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVal;
    }

    public static int getInt(ItemStack weapon, ItemStat stat) {
        int return_int = 0;
        switch (stat) {
            case DAMAGE:
                String lore = getStringFromLore(weapon, stat.getLore() + ":", 1);
                return_int = Integer.parseInt(lore.trim().replaceAll(" ", ""));
                break;
        }
        return return_int;
    }
}
