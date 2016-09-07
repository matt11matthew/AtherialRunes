package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff;

import me.matt11matthew.atherialrunes.database.data.player.Mute;
import me.matt11matthew.atherialrunes.game.GameConstants;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaffMechanics extends ListenerMechanic {

	@Override
	public void onEnable() {
		print("[StaffMechanics] Enabling...");
		registerListeners();
		task();
	}

	@Override
	public void onDisable() {
		print("[StaffMechanics] Disabling...");
	}

	@Override
	public LoadPriority getLoadPriority() {
		return LoadPriority.MONITOR;
	}
	
	public List<Player> getNonStaffPlayers() {
		List<Player> players = new ArrayList<Player>();
		Bukkit.getServer().getOnlinePlayers().forEach(player -> {
			if (!Main.staff.contains(player)) {
				players.add(player);
			}
		});
		return players;
	}
	
	public void task() {
		AtherialRunnable.getInstance().runRepeatingTask(new Runnable() {
			
			@Override
			public void run() {
				Bukkit.getServer().getOnlinePlayers().forEach(player -> {
					GamePlayer gp = Main.getGamePlayer(player.getName());
					//NickManager manager = NickNamerAPI.getNickManager();
					//manager.setNick(UUID.fromString(gp.getUUID()), gp.getNick());
					if (Rank.isGM(player.getName())) {
						if (gp.isVanished()) {
							gp.getPlayer().setPlayerListName(Utils.colorCodes(ChatColor.RED + gp.getNick()));
							gp.msg(MessageType.ACTION, GameConstants.CURRENTLY_HIDDEN_MESSAGE);
						} else {
							gp.getPlayer().setPlayerListName(gp.getRank().getTabListName() + gp.getNick());
						}
					} else {
						gp.getPlayer().setPlayerListName(gp.getRank().getTabListName() + gp.getNick());
						Mute mute = Mute.getMute(gp.getName());
						if (mute.isMuted()) {
							if (mute.canUnmute()) {
								mute.setUnmuteReason("Time");
								mute.setTime(0);
								Mute.save(mute);
							}
						}
					}
				});
			}
		}, 5L, 5L);
	}
}
