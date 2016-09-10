package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.Mob;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Boot;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Chestplate;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Helmet;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Leggings;

public class Monster {

    private Mob mob;
    private double health;
    private double damage;
    private String displayName;
    private Helmet helmet;
    private Chestplate chestplate;
    private Leggings leggings;
    private Boot boots;

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }
}
