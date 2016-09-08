package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.gear.weapon;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Weapon {

    FIST,
    WEAPON;

    public static Weapon getWeapon(ItemStack item) {
        return ((item == null) || (item.getType() == Material.AIR)) ? FIST : WEAPON;
    }
}
