package me.matt11matthew.atherialrunes.game.mechanic.servermechanic.patch.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.mechanic.servermechanic.patch.PatchMechanics;

public class CommandPatchNotes extends AtherialCommand {

	public CommandPatchNotes(String command, String usage, String description, List<String> aliases) {
		super(command, usage, description, aliases);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			new PatchMechanics().open(player);
			return true;
		}
		return true;
	}
}
