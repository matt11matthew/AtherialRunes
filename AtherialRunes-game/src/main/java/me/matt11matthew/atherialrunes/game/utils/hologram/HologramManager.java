package me.matt11matthew.atherialrunes.game.utils.hologram;

import org.bukkit.Location;

import java.util.HashMap;

public class HologramManager {

    public static HashMap<Location, Hologram> hologram_map = new HashMap<>();

    static HologramManager instance = null;

    public static HologramManager getInstance() {
        if (instance == null) {
            instance = new HologramManager();
        }
        return instance;
    }

    public void removeAll() {
        hologram_map.values().forEach(hologram -> {
            hologram.remove();
            hologram_map.remove(hologram.getLocation());
        });
    }

    public void task() {
        hologram_map.values().forEach(hologram -> {
            int time = hologram.getTime();
            if (time > 0) {
                hologram.setTime((hologram.getTime() - 1));
                return;
            } else if (time == 0) {
                hologram.remove();
                hologram_map.remove(hologram.getLocation());
                return;
            }
        });
    }

    public void spawn(String message, Location location, int time) {
        Hologram hologram = new Hologram(message, location, time);
        hologram_map.put(location, hologram);
    }
}
