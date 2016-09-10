package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats;

import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;

public class StatMechanics extends ListenerMechanic {

    @Override
    public void onEnable() {
        print("-----------------------------------------");
        print("[StatMechanics] Enabling...");
        print("-----------------------------------------");
        registerListeners();
    }

    @Override
    public void onDisable() {
        print("-----------------------------------------");
        print("[StatMechanics] Disabling...");
        print("-----------------------------------------");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.NORMAL;
    }
}
