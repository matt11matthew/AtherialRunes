package me.matt11matthew.atherialrunes.game.utils.hologram;

import org.bukkit.Location;

public interface IHologram {

    void teleport(Location location);

    void remove();

    void setMessage(String message);

    String getMessage();

    Location getLocation();
}
