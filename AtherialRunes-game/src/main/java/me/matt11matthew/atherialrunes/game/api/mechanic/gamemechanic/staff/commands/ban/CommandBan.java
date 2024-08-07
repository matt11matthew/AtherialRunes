package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.ban;

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

public class CommandBan extends AtherialCommand {

	/**
	 *
	 * @param command the command
	 * @param usage the usage
	 * @param description the description
	 */
	public CommandBan(String command, String usage, String description) {
		super(command, usage, description);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			//ban player <time> <reason>
			if (args.length >= 2) {
				String pname = args[0];
				String time = args[1];
				GamePlayer gp = Main.getGamePlayer(pname);
				String reasonString = null;
				if (args.length > 2) {
					StringBuilder reason = new StringBuilder(args[2]);
		            for (int arg = 2; arg < args.length; arg++) reason.append(" ").append(args[arg]);
		            reasonString = reason.toString();
				}
				if (!isTime(time)) {
					sender.sendMessage(Utils.colorCodes("&c/ban <player> <time> <reason>"));
					return true;
				}
				if (reasonString == null) {
					reasonString = "You have been banned";
				}
				sender.sendMessage(Utils.colorCodes("&c&lBANNED &c" + pname + ""));
				sender.sendMessage(Utils.colorCodes("&f&lReason: &c" + reasonString));
				sender.sendMessage(Utils.colorCodes("&f&lTime: &c" + time));
				Main.sendMessageToStaff("&c" + sender.getName() + " has &lBANNED &c" + pname);
				gp.ban(sender.getName(), reasonString, time);
				return true;
			}
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!Rank.isStaff(player.getName())) {
				return true;
			}
			if (args.length >= 2) {
				String pname = args[0];
				String time = args[1];
				GamePlayer gp = Main.getGamePlayer(pname);
				String reasonString = null;
				if (args.length > 2) {
					StringBuilder reason = new StringBuilder(args[2]);
		            for (int arg = 2; arg < args.length; arg++) reason.append(" ").append(args[arg]);
		            reasonString = reason.toString();
				}
				if (reasonString == null) {
					reasonString = "You have been banned";
				}
				if (!isTime(time)) {
					player.sendMessage(Utils.colorCodes("&c/ban <player> <time> <reason>"));
					return true;
				}
				player.sendMessage(Utils.colorCodes("&c&lBANNED &c" + pname + ""));
				player.sendMessage(Utils.colorCodes("&f&lReason: &c" + reasonString));
				player.sendMessage(Utils.colorCodes("&f&lTime: &c" + time));
				Main.sendMessageToStaff("&c" + player.getName() + " has &lBANNED &c" + pname);
				gp.ban(player.getName(), reasonString, time);
				return true;
			}
		}
		return true;
	}

	
	private boolean isTime(String time) {
		return Utils.canParseTime(time);
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
		