package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandKick extends AtherialCommand {

	/**
	 *
	 * @param command the command
	 * @param usage the usage
	 * @param description the description
	 */
	public CommandKick(String command, String usage, String description) {
		super(command, usage, description);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length >= 1) {
				String pname = args[0];
				GamePlayer gp = Main.getGamePlayer(pname);
				String reasonString = null;
				if (args.length > 1) {
					StringBuilder reason = new StringBuilder(args[1]);
		            for (int arg = 1; arg < args.length; arg++) reason.append(" ").append(args[arg]);
		            reasonString = reason.toString();
				}
				if (reasonString == null) {
					reasonString = "You have been kicked";
				}
				gp.kick(sender.getName(), reasonString);
				return true;
			} else {
				sender.sendMessage(Utils.colorCodes("&c/kick <player> <time>"));
				return true;
			}
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!Rank.isStaff(player.getName())) {
				return true;
			}
			if (args.length >= 1) {
				String pname = args[0];
				GamePlayer gp = Main.getGamePlayer(pname);
				String reasonString = null;
				if (args.length > 1) {
					StringBuilder reason = new StringBuilder(args[1]);
		            for (int arg = 1; arg < args.length; arg++) reason.append(" ").append(args[arg]);
		            reasonString = reason.toString();
				}
				if (reasonString == null) {
					reasonString = "You have been kicked";
				}
				gp.kick(player.getName(), reasonString);
				return true;
			} else {
				sender.sendMessage(Utils.colorCodes("&c/kick <player> <time>"));
				return true;
			}
		}
		return true;
	}

	
	public List<String> getOnlinePlayers() {
		List<String> players = new ArrayList<String>();
		for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
			players.add(pl.getName());
		}
		return players;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.isStaff(player.getName())) {
				if (args.length == 1) {
					return Utils.getOnlinePlayerNames(args);
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		return null;
	}
}
		