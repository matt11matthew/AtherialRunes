package me.matt11matthew.atherialrunes.discord.command;

import java.util.List;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;

public interface Command {

	public void execute(User user, String[] args, DiscordAPI api);
	
	public List<String> getRoles();
}
