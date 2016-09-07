package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import me.matt11matthew.atherialrunes.game.utils.BossbarUtils;

public class HealthMechanic extends ListenerMechanic {

	@Override
	public void onEnable() {
		print("[HealthMechanics] Enabling...");
		registerListeners();
		updateTask();
	}

	public void updateTask() {
		AtherialRunnable.getInstance().runRepeatingTask(new Runnable() {
			
			@Override
			public void run() {
				for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
					update(pl);
				}
			}
		}, 5L, 5L);
	}

	@Override
	public void onDisable() {
		print("[HealthMechanics] Disabling...");
		for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
			BossbarUtils.removeBar(pl);
		}
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.NORMAL;
	}
	
	public void update(Player player) {
		BossbarUtils.sendBar(player);
		player.setHealthScale(20.0D);
		player.setHealthScaled(true);
	}
}
