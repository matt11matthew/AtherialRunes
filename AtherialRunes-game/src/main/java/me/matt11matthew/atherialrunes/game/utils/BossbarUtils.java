package me.matt11matthew.atherialrunes.game.utils;

import me.matt11matthew.atherialrunes.game.enums.Unicodes;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

public class BossbarUtils {

	public static HashMap<Player, BossBar> bossbar = new HashMap<Player, BossBar>();

	/**
	 *
	 * @param player the player
     */
	public static void sendBar(Player player) {
		if (!bossbar.containsKey(player)) {
			int hp = (int) player.getHealth();
			int maxhp = (int) player.getMaxHealth();
			double progress = (player.getHealth() / player.getMaxHealth());
			double percent = (progress * 100.0D);
			bossbar.put(player, Bukkit.createBossBar(Utils.colorCodes("&bHealth: " + getColor(percent) + "[" + hp + "&l/" + getColor(percent) + "" + maxhp + "]"), getBarColor(percent), BarStyle.SOLID, BarFlag.CREATE_FOG));
			bossbar.get(player).addPlayer(player);
			
			bossbar.get(player).setProgress(progress);
			bossbar.get(player).setVisible(true);
		} else {
			int hp = (int) player.getHealth();
			int maxhp = (int) player.getMaxHealth();
			double progress = (player.getHealth() / player.getMaxHealth());
			double percent = (progress * 100.0D);
			bossbar.get(player).setProgress(progress);
			bossbar.get(player).setColor(getBarColor(percent));
			bossbar.get(player).setTitle(Utils.colorCodes("&bHealth: " + getColor(percent) + "[" + hp + "&l/" + getColor(percent) + "" + maxhp + "]"));
		}
	}

	/**
	 *
	 * @param percent the bossbar percent
	 * @return the color
     */
	public static BarColor getBarColor(double percent) {
		if (percent > 50) {
			return BarColor.GREEN;
		} else if ((percent < 51) && (percent > 20)) {
			return BarColor.YELLOW;
		} else if ((percent < 21) && (percent > 0)) {
			return BarColor.RED;
		} else {
			return BarColor.GREEN;
		}
	}

	/**
	 *
	 * @param percent the bossbar percent
	 * @return the color
	 */
	public static String getColor(double percent) {
		if (percent > 50) {
			return "&a";
		} else if ((percent < 51) && (percent > 20)) {
			return "&e";
		} else if ((percent < 21) && (percent > 0)) {
			return "&c";
		} else {
			return "&a";
		}
	}

	/**
	 *
	 * @param player the player
     */
	public static void removeBar(Player player) {
		if (bossbar.containsKey(player)) {
			bossbar.get(player).removeAll();
			
		}
	}

	/**
	 * sets up hp above head
	 */
	public static void setHPAboveHead() {
		Scoreboard s = Bukkit.getScoreboardManager().getMainScoreboard();
		if (s.getObjective("hp") != null) {
			s.getObjective("hp").unregister();
		}
		Objective o = s.registerNewObjective("hp", "health");
		o.setDisplayName(Utils.colorCodes("&c" + Unicodes.COMMON_HEART.get()));
		o.setDisplaySlot(DisplaySlot.BELOW_NAME);
	}
}