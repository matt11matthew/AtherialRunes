package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import org.bukkit.Bukkit;

public class LevelingMechanics extends ListenerMechanic {

	@Override
	public void onEnable() {
		print("[LevelingMechanics] Enabling...");
		registerListeners();
		task();
	}

	@Override
	public void onDisable() {
		print("[LevelingMechanics] Disabling...");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.LOWEST;
	}
	
	public void task() {
		AtherialRunnable.getInstance().runRepeatingTask(new Runnable() {
			
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(player -> {
					GamePlayer gp = Main.getGamePlayer(player.getName());
					if (!gp.isVanished()) {
						gp.setExpBarMessage(gp.getLevel() + "&lLVL &e[" + gp.getEXP() + "/" + LevelUtils.getEXPNeeded((gp.getLevel() + 1)) + "&lEXP&e]");
						gp.msg(MessageType.ACTION, gp.getExpBarMessage());
					}
				});
			}
		}, 5L, 5L);
	}
}
