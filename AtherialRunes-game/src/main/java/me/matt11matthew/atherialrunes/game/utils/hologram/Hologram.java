package me.matt11matthew.atherialrunes.game.utils.hologram;

import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Hologram implements IHologram {

    private String message;
    private Location location;
    private ArmorStand stand;
    private int time;

    public Hologram(String message, Location location, int time) {
        this.message = message;
        this.location = location;
        stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        stand.setCustomName(Utils.colorCodes(message));
        stand.setCustomNameVisible(true);
        stand.setInvulnerable(true);
        stand.setAI(false);
        stand.setGliding(false);
        stand.setVisible(false);
    }

    @Override
    public void teleport(Location location) {
        stand.teleport(location);
    }

    @Override
    public void remove() {
        stand.remove();
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
