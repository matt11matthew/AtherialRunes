package me.matt11matthew.atherialrunes.discord;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.discord.command.CommandManager;
import me.matt11matthew.atherialrunes.discord.command.commands.CommandInfo;
import me.matt11matthew.atherialrunes.discord.command.commands.CommandStats;

public class Main {
	
	public static void main(String args[]) {
		enableDiscord();
	}

	public static void enableDiscord() {
		DiscordAPI api = Javacord.getApi(Constants.DISCORD_BOT_TOKEN, true);
		api.connectBlocking();
		api.registerListener(new CommandManager());
		registerCommands();
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