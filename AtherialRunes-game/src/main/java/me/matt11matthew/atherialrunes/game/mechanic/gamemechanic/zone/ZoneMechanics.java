package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.zone;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.utils.RegionUtils;

public class ZoneMechanics extends ListenerMechanic {

	@Override
	public void onEnable() {
		print("[ZoneMechanics] Enabling...");
		registerListeners();
	}

	@Override
	public void onDisable() {
		print("[ZoneMechanics] Disabing...");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.NORMAL;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location from = e.getFrom();
		Location to = e.getTo();
		Zone from_zone = RegionUtils.getZone(from);
		Zone to_zone = RegionUtils.getZone(to);
		if ((from.getX() != to.getX()) || (from.getY() != to.getY()) || (from.getZ() != to.getZ())) {
			if (from_zone != to_zone) {
				p.sendMessage(to_zone.getMessage());
				p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.25F, 0.3F);
				if (!canEnterZone(p, to_zone)) {
					e.setCancelled(true);
					p.teleport(from);
				}
			}
		}
	}

	public boolean canEnterZone(Player p, Zone to_zone) {
		return true;
	}
}