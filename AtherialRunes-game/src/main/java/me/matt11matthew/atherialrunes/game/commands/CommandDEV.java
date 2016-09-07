package me.matt11matthew.atherialrunes.game.commands;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.MarketManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandDEV extends AtherialCommand {

	/**
	 *
	 * @param command the command
	 * @param usage the usage
	 * @param description the description
	 */
	public CommandDEV(String command, String usage, String description) {
		super(command, usage, description);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Constants.DEBUG) {
				test(player, Integer.parseInt(args[0]));
				return true;
			}
		}
		return true;
	}
	
	public void test(Player player, int price) {
		MarketManager.sellItem(player.getItemInHand(), price, Main.getGamePlayer(player));
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return null;
	}
}
		