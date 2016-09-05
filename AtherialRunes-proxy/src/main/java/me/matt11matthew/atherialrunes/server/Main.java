package me.matt11matthew.atherialrunes.server;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.server.commands.CommandShards;
import me.matt11matthew.atherialrunes.server.shard.ShardManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
	
	public void onEnable() {
		print("Starting Proxy...");
		print("Version " + Constants.SERVER_VERSION);
		print("Build #" + Constants.BUILD);
		print("ProxyServer Info -> " + Constants.MASTER_SERVER_IP + ":" + Constants.MASTER_SERVER_PORT);
		ShardManager.setupShards();
		ShardManager.connectShards();
	}
	
	public void onDisable() {
		ShardManager.endShards();
	}

	public static void print(String s) {
		System.out.println(s);
	}
	
	public static String colorCodes(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	private void registerCommands() {
		getProxy().getPluginManager().registerCommand(this, new CommandShards("shard"));
	}
}
