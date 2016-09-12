package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level;

import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.sound.AtherialSound;
import me.matt11matthew.atherialrunes.sound.EnumSound;

public class LevelUtils {

	public static void addEXP(GamePlayer gp, int exp) {
		long needed = getEXPNeeded((gp.getLevel() + 1));
		gp.setEXP((gp.getEXP() + exp));
		gp.msg(MessageType.CHAT, "%TAB%%TAB%&e&l+&e " + exp + " &lEXP &7[" + gp.getEXP() + "/" + needed + "]");
		if (gp.getEXP() >= needed) {
			gp.setLevel((gp.getLevel() + 1));
			gp.setEXP(0);
			gp.msg(MessageType.CHAT, "&c--------------------------");
			gp.msg(MessageType.CHAT, "        &c&lLevel UP        ");
			gp.msg(MessageType.CHAT, "&c&lGained: &a1 Skill Points");
			gp.msg(MessageType.CHAT, "&c--------------------------");
			gp.fw();
			gp.sound(new AtherialSound(EnumSound.LEVEL_UP, 1.0F, 1.0F));
		}
	}
	
	public static long getEXPNeeded(int lvl) {
		if (lvl == 2) {
			return 1000;
		}
		return (long) (1000 * (lvl * lvl));
	}
}
