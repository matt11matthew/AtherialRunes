package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor;

import me.matt11matthew.atherialrunes.exceptions.NotJsonFileException;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.enums.Rarity;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.tier.Tier;
import me.matt11matthew.atherialrunes.game.utils.RandomUtils;
import me.matt11matthew.atherialrunes.game.utils.json.JSONUtils;
import me.matt11matthew.atherialrunes.item.AtherialItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

import java.io.File;

public class Helmet implements MobArmor {

    private int tier;

    public Helmet(int tier) {
        this.tier = tier;
    }

    @Override
    public ItemStack get(Tier tier) {
        Material type = null;
        String name = null;
        double minHP = 0.0D;
        double maxHP = 0.0D;
        String hp = null;
        String armorType = "helmet";
        Rarity rarity = MobArmor.getRandomRarity();
        File json = new File(Main.getInstance().getDataFolder() + "/values/", "helmet.json");
        int t = 1;
        JSONObject obj = null;
        try {
            obj = JSONUtils.convertStringToJSONObject(json);
        } catch (NotJsonFileException e) {
            e.printStackTrace();
        }
        switch (tier) {
            case TIER_1:
                type = Material.LEATHER_HELMET;
                name = "&fLeather Helmet";
                t = 1;
                break;
            case TIER_2:
                type = Material.CHAINMAIL_HELMET;
                name = "&7Chainmail Helmet";
                t = 2;
                break;
            case TIER_3:
                type = Material.IRON_HELMET;
                name = "&3Iron Helmet";
                t = 3;
                break;
            case TIER_4:
                type = Material.DIAMOND_HELMET;
                name = "&cDiamond Helmet";
                t = 4;
                break;
        }
        hp = (String) obj.get(tier.getPrefix() + "." + armorType + "." + rarity.getName() + ".hp");
        int health = RandomUtils.random(Integer.parseInt(hp.split("-")[0]), Integer.parseInt(hp.split("-")[1]));
        AtherialItem item = new AtherialItem(type);
        item.setName(name);
        item.addLore("&fHealth: &c" + health);
        item.addLore(rarity.getLore());
        return item.build();
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
