package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob;

import me.matt11matthew.atherialrunes.game.GameConstants;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.tier.Tier;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.Monster;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Boot;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Chestplate;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Helmet;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob.monster.mobarmor.Leggings;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner.Spawner;
import me.matt11matthew.atherialrunes.game.utils.RandomUtils;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;

public class MobMechanics extends ListenerMechanic {

	public static HashMap<LivingEntity, Monster> monsters = new HashMap<>();

	@Override
	public void onEnable() {
		print("-----------------------------------------");
		print("[MobMechanics] Enabling...");
		print("-----------------------------------------");
		registerListeners();
	}

	@Override
	public void onDisable() {
		print("-----------------------------------------");
		print("[MobMechanics] Disabling...");
		print("-----------------------------------------");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.NORMAL;
	}

	public static void spawn(Location location, Mob mob, Spawner spawner) {
		LivingEntity l = (LivingEntity) Bukkit.getWorld(GameConstants.WORLD_NAME).spawnEntity(location, mob.getMobType().getEntityType());
		String helmet = Utils.getRandomFromList(mob.getHelmets());
		String chestplate = Utils.getRandomFromList(mob.getChestplates());
		String legging = Utils.getRandomFromList(mob.getLeggings());
		String boot = Utils.getRandomFromList(mob.getBoots());
		String weapon = Utils.getRandomFromList(mob.getWeapons());
		String name = Utils.getRandomFromList(mob.getDisplayNames());
		l.getEquipment().setHelmet(new ItemStack(Material.AIR));
		l.getEquipment().setChestplate(new ItemStack(Material.AIR));
		l.getEquipment().setLeggings(new ItemStack(Material.AIR));
		l.getEquipment().setBoots(new ItemStack(Material.AIR));
		l.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
		l.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
		if (helmet.startsWith("skull:")) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, (short) 4);
			SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
			skullMeta.setOwner(helmet.split("skull:")[1].trim());
			skull.setItemMeta(skullMeta);
			l.getEquipment().setHelmet(skull);
		}
		l.setCustomName(Utils.colorCodes(name));
		l.setCustomNameVisible(true);
		double health = RandomUtils.random((int) mob.getMinHealth(), (int) mob.getMaxHealth());
		Monster monster = new Monster();
		monster.setMob(mob);
		monster.setHealth(health);
		monster.setDisplayName(Utils.colorCodes(name));
		monster.setLivingEntity(l);
		monster.setSpawner(spawner);
		if (!boot.equalsIgnoreCase("air")) {
			monster.setBoots(new Boot(Integer.parseInt(boot.trim())));
			l.getEquipment().setBoots(monster.getBoots().get(Tier.getTier(monster.getBoots().getTier())));
		}
		if ((!helmet.equalsIgnoreCase("air")) && (!helmet.contains("skull:"))) {
			monster.setHelmet(new Helmet(Integer.parseInt(boot.trim())));
			l.getEquipment().setHelmet(monster.getHelmet().get(Tier.getTier(monster.getHelmet().getTier())));
		}
		if (!chestplate.equalsIgnoreCase("air")) {
			monster.setChestplate(new Chestplate(Integer.parseInt(chestplate.trim())));
			l.getEquipment().setChestplate(monster.getChestplate().get(Tier.getTier(monster.getChestplate().getTier())));
		}
		if (!legging.equalsIgnoreCase("air")) {
			monster.setLeggings(new Leggings(Integer.parseInt(legging.trim())));
			l.getEquipment().setLeggings(monster.getLeggings().get(Tier.getTier(monster.getLeggings().getTier())));
		}
		monsters.put(l, monster);
	}

	public static Mob getMob(String mob) {
		return new Mob(mob);
	}
}

