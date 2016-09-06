package me.matt11matthew.atherialrunes.discord.command;

import java.util.List;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;


public interface Command {

	public void execute(User user, String[] args, DiscordAPI api, Message msg);
	
	public List<String> getRoles();
}
