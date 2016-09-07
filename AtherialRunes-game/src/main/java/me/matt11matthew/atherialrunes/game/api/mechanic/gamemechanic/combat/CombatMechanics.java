package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.combat;

import me.matt11matthew.atherialrunes.game.GameConstants;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.damage.events.PlayerDamagePlayerEvent;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

public class CombatMechanics extends ListenerMechanic {

	@Override
	public void onEnable() {
		print("[CombatMechanics] Enabling...");
		registerListeners();
		combatTimeTask();
	}

	@Override
	public void onDisable() {
		print("[CombatMechanics] Disabling...");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.HIGH;
	}
	
	public static void flagPvP(GamePlayer gp) {
		if (!gp.isInCombat()) {
			gp.msg(MessageType.ACTION, GameConstants.IN_COMBAT_MESSAGE);
		}
		gp.setCombatTime(GameConstants.COMBAT_TIME);
	}

	@EventHandler
	public void onPlayerDamage(PlayerDamagePlayerEvent e) {
		if (!e.isCancelled()) {
			flagPvP(e.getAttacker());
		}
	}
	
	public void combatTimeTask() {
		AtherialRunnable.getInstance().runRepeatingTask(new Runnable() {
			
			@Override
			public void run() {
				Bukkit.getServer().getOnlinePlayers().forEach(player -> {
					GamePlayer gp = Main.getGamePlayer(player.getName());
					if (gp.isInCombat()) {
						if (gp.getCombatTime() > 0) {
							gp.setCombatTime((gp.getCombatTime() - 1));
						} else {
							gp.setCombatTime(-1);
							gp.msg(MessageType.ACTION, GameConstants.OUT_COMBAT_MESSAGE);
							return;
						}
					}
				});
			}
		}, 20L, 20L);
	}
}
