package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.level;

import org.bukkit.Bukkit;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;

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
		return LoadPriority.NORMAL;
	}
	
	public void task() {
		AtherialRunnable.getInstance().runRepeatingTask(new Runnable() {
			
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(player -> {
					GamePlayer gp = Main.getGamePlayer(player.getName());
					if (!gp.isVanished()) {
						gp.setExpBarMessage("&e(" + gp.getEXP() + "/" + LevelUtils.getEXPNeeded((gp.getLevel() + 1)) + "&lEXP&e)");
						gp.msg(MessageType.ACTION, gp.getExpBarMessage());
					}
				});
			}
		}, 5L, 5L);
	}
}
