package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.LevelUtils;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.utils.IntegerUtils;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandAddEXP extends AtherialCommand {

	/**
	 *
	 * @param command the command
	 * @param usage the usage
	 * @param description the description
	 * @param aliases the aliases
	 */
	public CommandAddEXP(String command, String usage, String description, List<String> aliases) {
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
		if (args.length == 2) {
			String pname = args[0];
			String rawEXP = args[1];
			if (!IntegerUtils.isInt(rawEXP)) {
				sender.sendMessage(Utils.colorCodes("&c/addexp <player> <exp>"));
				return true;
			}
			try {
				GamePlayer gp = Main.getGamePlayer(pname);
				LevelUtils.addEXP(gp, Integer.parseInt(rawEXP.trim()));
				sender.sendMessage(Utils.colorCodes("&cYou've given " + rawEXP.trim() + " &lEXP &cto " + pname));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
		return true;
	}
}
