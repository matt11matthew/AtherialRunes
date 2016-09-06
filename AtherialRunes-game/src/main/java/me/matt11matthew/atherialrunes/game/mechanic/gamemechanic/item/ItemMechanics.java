package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.item;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.item.customitems.CustomItem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemMechanics extends ListenerMechanic {

    public static HashMap<String, CustomItem> customItems = new HashMap<String, CustomItem>();

    @Override
    public void onEnable() {
        registerListeners();
        print("[ItemMechanics] Enabling...");
        loadCustomItems();
    }

    @Override
    public void onDisable() {
        print("[ItemMechanics] Disabling...");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.LOWEST;
    }

    public void loadCustomItems() {
        List<File> files = new ArrayList<>();
        File[] itemFiles = new File(Main.getInstance().getDataFolder() + "/custom_items/").listFiles();
        for (File file : itemFiles) {
            files.add(file);
        }
        files.forEach(itemFile -> {
            CustomItem item = new CustomItem(itemFile);
            customItems.put(itemFile.getName().split(".json")[0], item);
            Main.print(item.getFile().getName().split(".json")[0].trim());
        });
    }
}
  