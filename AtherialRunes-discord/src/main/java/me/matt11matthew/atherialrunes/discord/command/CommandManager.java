package me.matt11matthew.atherialrunes.discord.command;

import java.util.HashMap;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.matt11matthew.atherialrunes.discord.Main;

public class CommandManager implements MessageCreateListener {
	
	private static HashMap<String, Command> commands = new HashMap<String, Command>();

	@Override
	public void onMessageCreate(DiscordAPI api, Message msg) {
		if (msg.getContent().startsWith("!")) {
			commands.keySet().forEach(commandKey -> {
				if (msg.getContent().startsWith("!" + commandKey)) {
					Command command = commands.get(commandKey);
					User user = msg.getAuthor();
					Main.print(canRun(user, command, api) + "");
					if (canRun(user, command, api)) {
						if (msg.getContent().contains(" ")) {
							String[] args = msg.getContent().split(commandKey)[1].split(" ");
							command.execute(user, args, api);
						} else {
							String[] s = null;
							command.execute(user, s, api);
						}
						msg.delete();
					}
				}
			});
		}
	}
	
	private boolean canRun(User user, Command command, DiscordAPI api) {
		Server server = api.getServerById("216389507572957184");
		Main.print(user.getRoles(server).toString());
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
