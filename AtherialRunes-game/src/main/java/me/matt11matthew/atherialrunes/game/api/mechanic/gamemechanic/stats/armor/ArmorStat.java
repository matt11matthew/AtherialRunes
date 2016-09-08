package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.armor;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.StatUtils;
import org.bukkit.inventory.ItemStack;

public enum ArmorStat {

    DPS(0, "&fDamage Per Second: &c", "DPS");

    int id;
    String lore;
    String name;

    ArmorStat(int id, String lore, String name) {
        this.id = id;
        this.lore = lore;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public int getId() {
        return id;
    }

    public boolean has(ItemStack item) {
        return StatUtils.hasStat(item, this);
    }
}
