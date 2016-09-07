package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item;

import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;

public class ItemMechanics extends ListenerMechanic {

    @Override
    public void onEnable() {
        registerListeners();
        print("[ItemMechanics] Enabling...");
    }

    @Override
    public void onDisable() {
        print("[ItemMechanics] Disabling...");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.LOWEST;
    }
}
  