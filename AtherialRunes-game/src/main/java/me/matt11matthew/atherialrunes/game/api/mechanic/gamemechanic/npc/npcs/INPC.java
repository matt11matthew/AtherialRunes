package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.npcs;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.enums.NPCType;
import org.bukkit.Location;

import java.util.UUID;

public interface INPC {

    void spawn();

    String getName();

    Location getLocation();

    void despawn();

    void setVisible(boolean visible);

    boolean isVisible();

    NPCType getNPCType();
    
    UUID getUniqueId();

    void save();
}
