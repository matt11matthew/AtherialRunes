package me.matt11matthew.atherialrunes.server;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

	public static String motd;

	public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, new MOTDListener());
	}

	public static void print(String s) {
		System.out.println(s);
	}
	
	public static String colorCodes(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
