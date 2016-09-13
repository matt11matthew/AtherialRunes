package me.matt11matthew.atherialrunes.discord.command.commands;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import me.matt11matthew.atherialrunes.discord.command.Command;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.LevelUtils;

import java.util.List;


public class CommandStats implements Command {

	@Override
	public void execute(User user, String[] args, DiscordAPI api, Message msg) {
		if (args.length == 2) {
			String name = args[1].replaceAll(" ", "").trim();
			String rank = "Default";
			String chatChannel = "Local";
			String stats = "";
			if (name.equals("matt11matthew")) {
				stats =
						"------------------------------------," +
								"IGN: " + name + "," +
								"Rank: " + rank + "," +
								"Chat Channel: " + chatChannel + "," +
								"Gold: " + gold + "," +
								"Silver: " + silver + "," +
								"Copper: " + copper + "," +
								"Level: " + 43 + "," +
								"Experience: [" + 543 + "/" + LevelUtils.getEXPNeeded(44) + "]" + "," +
								"Skill Points: " + 60 + "," +
								"------------------------------------" + ",";
			} else if (name.equals("Jackychen1999")) {
				stats =
						"------------------------------------," +
								"IGN: " + name + "," +
								"Rank: " + rank + "," +
								"Chat Channel: " + chatChannel + "," +
								"Gold: " + 0 + "," +
								"Silver: " + 2 + "," +
								"Copper: " + 0 + "," +
								"Level: " + 0 + "," +
								"Experience: [" + 0 + "/" + LevelUtils.getEXPNeeded(2) + "]" + "," +
								"Skill Points: " + 0 + "," +
								"------------------------------------" + ",";

			} else {
				stats =
						"------------------------------------," +
								"IGN: " + name + "," +
								"Rank: " + rank + "," +
								"Chat Channel: " + chatChannel + "," +
								"Gold: " + 0 + "," +
								"Silver: " + 0 + "," +
								"Copper: " + 0 + "," +
								"Level: " + 0 + "," +
								"Experience: [" + 0 + "/" + LevelUtils.getEXPNeeded(2) + "]" + "," +
								"Skill Points: " + 0 + "," +
								"------------------------------------" + ",";
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
