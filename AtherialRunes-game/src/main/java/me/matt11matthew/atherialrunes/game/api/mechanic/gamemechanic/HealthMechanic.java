package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic;

import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import me.matt11matthew.atherialrunes.game.utils.BossbarUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HealthMechanic extends ListenerMechanic {

	@Override
	public void onEnable() {
		print("-----------------------------------------");
		print("[HealthMechanics] Enabling...");
		print("-----------------------------------------");
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
		print("-----------------------------------------");
		print("[HealthMechanics] Disabling...");
		print("-----------------------------------------");
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
