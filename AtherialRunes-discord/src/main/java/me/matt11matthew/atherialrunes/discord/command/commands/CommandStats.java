package me.matt11matthew.atherialrunes.discord.command.commands;

import java.util.List;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import me.matt11matthew.atherialrunes.database.table.tables.player.UUIDTable;
import me.matt11matthew.atherialrunes.discord.Main;
import me.matt11matthew.atherialrunes.discord.command.Command;


public class CommandStats implements Command {

	@Override
	public void execute(User user, String[] args, DiscordAPI api, Message msg) {
		if (args.length == 2) {
			String name = args[1].replaceAll(" ", "").trim();
			UUIDTable uuidTable = new UUIDTable();
			String uuid = uuidTable.getString("uuid", name);
			Main.print(uuid);
		}
	}
//			try {
//				
//				System.out.println(gp.getName());
//				String stats = 
//				"------------------------------------," +
//				"IGN: " + gp.getName() + "," + 
//				"Rank: " + gp.getRank().getName() + "," + 
//				"Chat Channel: " + gp.getChatChannel().getName() + "," + 
//				"Gold: " + gp.getGold() + "," + 
//				"Silver: " + gp.getSilver() + "," + 
//				"Copper: " + gp.getCopper() + "," + 
//				"Level: " + gp.getLevel() + "," + 
//				"Experience: [" + gp.getEXP() + "/" + LevelUtils.getEXPNeeded((gp.getLevel()) + 1) + "]" + "," + 
// 				"Skill Points: " + gp.getSkillPoints() + "," + 
//				"------------------------------------" + ",";
//				stats = stats.replaceAll(",", System.lineSeparator());
//				System.out.println(stats);
//			} catch (Exception e) {
//				
//			}
//		}
	

	@Override
	public List<String> getRoles() {
		return null;
	}

}
/*
GamePlayer gp = Main.getGamePlayer(name);
				System.out.println(gp.getName());
				String stats = 
				"------------------------------------," +
				"IGN: " + gp.getName() + "," + 
				"Rank: " + gp.getRank().getName() + "," + 
				"Chat Channel: " + gp.getChatChannel().getName() + "," + 
				"Gold: " + gp.getGold() + "," + 
				"Silver: " + gp.getSilver() + "," + 
				"Copper: " + gp.getCopper() + "," + 
				"Level: " + gp.getLevel() + "," + 
				"Experience: [" + gp.getEXP() + "/" + LevelUtils.getEXPNeeded((gp.getLevel()) + 1) + "]" + "," + 
 				"Skill Points: " + gp.getSkillPoints() + "," + 
				"------------------------------------" + ",";
				stats = stats.replaceAll(",", System.lineSeparator());
				System.out.println(stats);
*/