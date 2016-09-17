package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.Mob;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Boot;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Chestplate;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Helmet;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Leggings;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner.Spawner;
import org.bukkit.entity.LivingEntity;

public class Monster {

    private Mob mob;
    private double health;
    private String displayName;
    private Helmet helmet;
    private Chestplate chestplate;
    private Leggings leggings;
    private Boot boots;
    private Spawner spawner;
    private LivingEntity livingEntity;

    public Monster(Mob mob, double health, String displayName, Helmet helmet, Chestplate chestplate, Leggings leggings, Boot boot, Spawner spawner, LivingEntity livingEntity) {
        this.mob = mob;
        this.health = health;
        this.displayName = displayName;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boot;
        this.spawner = spawner;
        this.livingEntity = livingEntity;
    }

    public Monster() {}

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public Chestplate getChestplate() {
        return chestplate;
    }

    public void setChestplate(Chestplate chestplate) {
        this.chestplate = chestplate;
    }

    public Leggings getLeggings() {
        return leggings;
    }

    public void setLeggings(Leggings leggings) {
        this.leggings = leggings;
    }

    public Boot getBoots() {
        return boots;
    }

    public void setBoots(Boot boots) {
        this.boots = boots;
    }

    public Spawner getSpawner() {
        return spawner;
    }

    public void setSpawner(Spawner spawner) {
        this.spawner = spawner;
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
    }

    public void setLivingEntity(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }
}
