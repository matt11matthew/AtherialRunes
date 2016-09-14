package me.matt11matthew.atherialrunes.discord.command.commands;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import me.matt11matthew.atherialrunes.discord.command.Command;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.LevelUtils;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.utils.AtherialUtils;

import java.util.List;


public class CommandStats implements Command {

	@Override
	public void execute(User user, String[] args, DiscordAPI api, Message msg) {
		if (args.length == 2) {
			String name = args[1].replaceAll(" ", "").trim();
			String stats = "";
			if (AtherialUtils.isPlayer(name)) {
				GamePlayer gp = Main.getGamePlayer(name);
				stats =
						"------------------------------------," +
								"IGN: " + name + "," +
								"Rank: " + gp.getRank() + "," +
								"Chat Channel: " + gp.getChatChannel().getName() + "," +
								"Gold: " + gp.getGold() + "," +
								"Silver: " + gp.getSilver() + "," +
								"Copper: " + gp.getCopper() + "," +
								"Level: " + gp.getLevel() + "," +
								"Experience: [" + (long) gp.getEXP() + "/" + LevelUtils.getEXPNeeded((gp.getLevel() + 1)) + "]" + "," +
								"Skill Points: " + gp.getSkillPoints() + "," +
								"Notoriety: " + gp.getNotoriety() + "," +
						"------------------------------------" + ",";

			} else {
				stats = name + " doesn't exist in the database.";
			}
			stats = stats.replaceAll(",", System.lineSeparator());
			msg.reply(stats);
		}
	}

	@Override
	public List<String> getRoles() {
		return null;
	}

}
