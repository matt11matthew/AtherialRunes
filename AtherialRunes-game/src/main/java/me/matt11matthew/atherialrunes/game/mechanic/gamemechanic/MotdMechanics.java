package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic;

import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.utils.Utils;

public class MotdMechanics extends ListenerMechanic {

	@Override
	public void onEnable() {
		print("[MOTDMechanics] Enabling...");
		registerListeners();
	}

	@Override
	public void onDisable() {
		print("[MOTDMechanics] Disabling...");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.NORMAL;
	}
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent e) {
		if (Constants.MAINTENANCE) {
			e.setMotd(Utils.asCentered(Utils.colorCodes(Constants.MAINTENANCE_MOTD)));
		} else if (Constants.DEBUG) {
			e.setMotd(Utils.asCentered(Utils.colorCodes(Constants.DEV_MOTD)));
		} else {
			e.setMotd(Utils.asCentered(Utils.colorCodes(Constants.MOTD)));
		}
	}
}