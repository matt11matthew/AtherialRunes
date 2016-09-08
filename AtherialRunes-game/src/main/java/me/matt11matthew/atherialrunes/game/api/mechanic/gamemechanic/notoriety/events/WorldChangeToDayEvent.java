package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.notoriety.events;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WorldChangeToDayEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private World world;

    public WorldChangeToDayEvent(World world) {
        this.world = world;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public World getWorld() {
        return world;
    }
}
