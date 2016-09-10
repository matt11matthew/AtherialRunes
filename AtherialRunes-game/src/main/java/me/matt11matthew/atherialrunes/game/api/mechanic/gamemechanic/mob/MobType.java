package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob;

import org.bukkit.entity.EntityType;

public enum MobType {

    SKELETON(EntityType.SKELETON);

    private EntityType entityType;

    MobType(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
