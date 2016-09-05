package me.matt11matthew.atherialrunes.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.lobby.commands.CommandShard;
import me.matt11matthew.atherialrunes.lobby.shard.ShardLoader;

public class Main extends JavaPlugin {
	
	private static Main instance;
    
	public void onEnable() {
		instance = this;
		print("Starting Lobby...");
		print("Version " + Constants.SERVER_VERSION);
		print("Build #" + Constants.BUILD);
		print("Loading shards...");
		ShardLoader.loadShards();
		print("");
		print("");
		print("Registering commands...");
		print("");
		registerCommands();
		print("");
		print("");
		print("Registering Listeners...");
		print("");
		registerListeners();
		
	
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	

	public static void print(String s) {
		System.out.println(s);
	}	
	
	private void registerCommands() {
		getCommand("shard").setExecutor(new CommandShard());
		print("Registered command -> /shard");
		print("Commands registered!");
	}
	
	private void registerListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListeners(), this);
		print("Registered listener -> PlayerListeners");
		print("Listeners registered!");
	}

	public static Location getLobbySpawn() {
		return Constants.LOBBY_SPAWN;
	}

	public static Main getInstance() {
		return instance;
	}
}