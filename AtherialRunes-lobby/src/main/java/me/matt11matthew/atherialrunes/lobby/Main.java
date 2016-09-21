package me.matt11matthew.atherialrunes.lobby;

import me.matt11matthew.atherialrunes.Constants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main instance;
    
	public void onEnable() {
		instance = this;
		print("Starting Lobby...");
		registerListeners();
		registerCommands();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	

	public static void print(String s) {
		System.out.println(s);
	}	
	
	private void registerCommands() {

	}
	
	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
	}

	public static Location getLobbySpawn() {
		return Constants.LOBBY_SPAWN;
	}

	public static Main getInstance() {
		return instance;
	}
}