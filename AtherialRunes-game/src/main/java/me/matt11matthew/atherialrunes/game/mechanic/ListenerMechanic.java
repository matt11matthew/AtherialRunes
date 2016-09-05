package me.matt11matthew.atherialrunes.game.mechanic;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import me.matt11matthew.atherialrunes.game.Main;

public abstract class ListenerMechanic extends Mechanic implements Listener {

	public void registerListeners() {
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
	}
}