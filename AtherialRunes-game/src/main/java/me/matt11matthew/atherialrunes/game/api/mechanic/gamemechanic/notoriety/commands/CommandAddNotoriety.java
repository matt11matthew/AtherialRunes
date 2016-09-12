package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.notoriety.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.utils.IntegerUtils;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandAddNotoriety extends AtherialCommand {

	/**
	 *
	 * @param command the command
	 * @param usage the usage
	 * @param description the description
	 * @param aliases the aliases
	 */
	public CommandAddNotoriety(String command, String usage, String description, List<String> aliases) {
		super(command, usage, description, aliases);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!Rank.isGM(player.getName())) {
				return true;
			}
		}
		if (args.length == 3) {
			String pname = args[0];
			String rawEXP = args[1];
			String type = args[2];
			if (!IntegerUtils.isInt(rawEXP)) {
				sender.sendMessage(Utils.colorCodes("&c/addnotoriety <player> <notoriety> <+/->"));
				return true;
			}
			try {
				GamePlayer gp = Main.getGamePlayer(pname);
				if (type.equals("-")) {
					gp.setNotoriety((gp.getNotoriety() - Integer.parseInt(rawEXP)));
					sender.sendMessage(Utils.colorCodes("&cYou've taken " + rawEXP.trim() + " &lnotoriety &cfrom " + pname));
				} else if (type.equals("+")) {
					gp.setNotoriety((gp.getNotoriety() + Integer.parseInt(rawEXP)));
					sender.sendMessage(Utils.colorCodes("&cYou've given " + rawEXP.trim() + " &lnotoriety &cto " + pname));
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
		return true;
	}
}
