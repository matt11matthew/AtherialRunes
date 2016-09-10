package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.mob;

import me.matt11matthew.atherialrunes.game.GameConstants;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;

public class MobMechanics extends ListenerMechanic {
	
	public HashMap<String, Mob> mobs = new HashMap<>();

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
		return LoadPriority.LOWEST;
	}

	public static void spawn(Location location, Mob mob) {
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


	}
}

