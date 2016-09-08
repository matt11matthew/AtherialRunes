package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.item;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.StatUtils;
import org.bukkit.inventory.ItemStack;

public enum ItemStat {

    DAMAGE(0, "&fDamage: &c", "Damage");

    int id;
    String lore;
    String name;

    ItemStat(int id, String lore, String name) {
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

    public int getInt(ItemStack weapon) {
        return (has(weapon)) ? StatUtils.getInt(weapon, this) : 0;
    }
}
