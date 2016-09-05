package me.matt11matthew.atherialrunes.discord;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import me.matt11matthew.atherialrunes.discord.command.CommandManager;
import me.matt11matthew.atherialrunes.discord.command.commands.CommandTest;

public class Main {
	
	public static void main(String args[]) {
        DiscordAPI api = Javacord.getApi("MjIyNDA3NjY3OTA4MzQ1ODU3.Cq880Q.FHIKaK8fJQCq3BzV4NYR_6vcaf4", true);
        api.connectBlocking();
        api.registerListener(new CommandManager());
        registerCommands();
	}
	
	private static void registerCommands() {
		CommandManager cm = new CommandManager();
		cm.registerCommand("test", new CommandTest());
	}

	public static void print(String msg) {
		System.out.println(msg);
	}
}