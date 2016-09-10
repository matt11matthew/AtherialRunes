package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item;


import me.matt11matthew.atherialrunes.exceptions.NotJsonFileException;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.commands.ReloadException;
import me.matt11matthew.atherialrunes.game.utils.json.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;

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
}
  