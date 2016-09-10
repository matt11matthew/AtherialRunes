package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.enums.Rarity;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.tier.Tier;
import me.matt11matthew.atherialrunes.game.utils.RandomUtils;
import org.bukkit.inventory.ItemStack;

public interface MobArmor {

    ItemStack get(Tier tier);

    public static Rarity getRandomRarity() {
        int i = RandomUtils.random(1, 100);
        if (i < 5) {
            return Rarity.LEGENDARY;
        } else if ((i < 25) && (i > 5)) {
            return Rarity.EPIC;
        } else if ((i < 40) && (i > 25)) {
            return Rarity.UNUSUAL;
        } else if (i > 40) {
            return Rarity.NORMAL;
        } else {
            return Rarity.NORMAL;
        }
    }
}
