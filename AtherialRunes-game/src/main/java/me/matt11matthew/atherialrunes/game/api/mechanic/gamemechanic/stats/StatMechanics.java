package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats;

import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;

public class StatMechanics extends ListenerMechanic {

    @Override
    public void onEnable() {
        print("[StatMechanics] Enabling...");
        registerListeners();
    }

    @Override
    public void onDisable() {
        print("[StatMechanics] Disabling...");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.NORMAL;
    }
}
