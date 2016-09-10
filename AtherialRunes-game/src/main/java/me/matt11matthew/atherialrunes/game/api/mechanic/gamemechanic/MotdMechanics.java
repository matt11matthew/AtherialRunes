package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdMechanics extends ListenerMechanic {

	@Override
	public void onEnable() {
		print("-----------------------------------------");
		print("[MOTDMechanics] Enabling...");
		print("-----------------------------------------");
		registerListeners();
	}

	@Override
	public void onDisable() {
		print("-----------------------------------------");
		print("[MOTDMechanics] Disabling...");
		print("-----------------------------------------");
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