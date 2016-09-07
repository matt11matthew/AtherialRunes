package me.matt11matthew.atherialrunes.game.commands;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.FirstMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandDEV extends AtherialCommand {

	public CommandDEV(String command, String usage, String description) {
		super(command, usage, description);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Constants.DEBUG) {
				test(player);
				return true;
			}
		}
		return true;
	}
	
	public void test(Player player) {
		new FirstMenu().open(player);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return null;
	}
}
		