package me.matt11matthew.atherialrunes.discord.command.commands;

import java.util.Arrays;
import java.util.List;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import me.matt11matthew.atherialrunes.discord.command.Command;

public class CommandTest implements Command {

	@Override
	public void execute(User user, String[] args, DiscordAPI api) {
		
	}

	@Override
	public List<String> getRoles() {
		return Arrays.asList("Lead Developer");
	}

}
