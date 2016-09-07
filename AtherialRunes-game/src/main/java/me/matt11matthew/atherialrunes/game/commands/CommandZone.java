package me.matt11matthew.atherialrunes.game.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandZone extends AtherialCommand {

	/**
	 *
	 * @param command the command
	 * @param usage the usage
	 * @param description the description
	 * @param aliases the aliases
	 */
	public CommandZone(String command, String usage, String description, List<String> aliases) {
		super(command, usage, description, aliases);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			return true;
		}
		return true;
	}

	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return null;
	}
}
		