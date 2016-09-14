package me.matt11matthew.atherialrunes.discord.command;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.matt11matthew.atherialrunes.Constants;

import java.util.HashMap;

public class CommandManager implements MessageCreateListener {
	
	private static HashMap<String, Command> commands = new HashMap<String, Command>();

	@Override
	public void onMessageCreate(DiscordAPI api, Message msg) {
		if (msg.getContent().startsWith("!")) {
			commands.keySet().forEach(commandKey -> {
				if (msg.getContent().startsWith("!" + commandKey)) {
					Command command = commands.get(commandKey);
					User user = msg.getAuthor();
					if (canRun(user, command, api)) {
						if (msg.getContent().contains(" ")) {
							String[] args = msg.getContent().split(commandKey)[1].split(" ");
							msg.delete();
							command.execute(user, args, api, msg);
						} else {
							String[] s = null;
							msg.delete();
							command.execute(user, s, api, msg);
						}
						
					}
				}
			});
		}
	}
	
	private boolean canRun(User user, Command command, DiscordAPI api) {
		Server server = api.getServerById(Constants.DISCORD_SERVER_ID);
		if (command.getRoles() == null) {
			return true;
		}
		for (Role userRoles : user.getRoles(server)) {
			if (command.getRoles().contains(userRoles.getName())) {
				return true;
			}
		}
		return false;
	}

	public void registerCommand(String cmd, Command command) {
		commands.put(cmd, command);
	}
}
