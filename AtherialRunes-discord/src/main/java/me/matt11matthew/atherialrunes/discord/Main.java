package me.matt11matthew.atherialrunes.discord;

import me.matt11matthew.atherialrunes.discord.command.CommandManager;
import me.matt11matthew.atherialrunes.discord.command.commands.CommandInfo;
import me.matt11matthew.atherialrunes.discord.command.commands.CommandStats;
import org.json.simple.JSONObject;

public class Main {
	
	public static void main(String args[]) {
		JSONObject obj = new JSONObject();
		obj.put("matthew.matt", "matthew");
		System.out.print(obj);
//		DiscordAPI api = Javacord.getApi(Constants.DISCORD_BOT_TOKEN, true);
//		api.connectBlocking();
//		api.registerListener(new CommandManager());
//		registerCommands();
	}
	
	private static void registerCommands() {
		CommandManager cm = new CommandManager();
		cm.registerCommand("stats", new CommandStats());
		cm.registerCommand("serverhelp", new CommandInfo());
		cm.registerCommand("info", new CommandInfo());
	}

	public static void print(String msg) {
		System.out.println(msg);
	}
}//return Arrays.asList("Lead Developer", "Developer", "Leads", "Global Admin", "System Admin", "Lore Developer", "Builder");