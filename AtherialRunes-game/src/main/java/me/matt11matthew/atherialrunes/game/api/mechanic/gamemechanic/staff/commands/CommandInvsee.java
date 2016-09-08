package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class CommandInvsee extends AtherialCommand {

	/**
	 *
	 * @param command the command
	 * @param usage the usage
	 * @param description the description
	 * @param args the aliases
	 */
	public CommandInvsee(String command, String usage, String description, List<String> args) {
		super(command, usage, description, args);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!Rank.isGM(player.getName())) {
				return true;
			}
			if (args.length == 1) {
				String pname = args[0];
				Inventory inventory = Bukkit.createInventory(null, 54, pname + "'s Inventory");
				if ((Bukkit.getPlayerExact(pname) != null) && (Bukkit.getPlayerExact(pname).isOnline())) {
					Player t = Bukkit.getPlayerExact(pname);
					player.openInventory(t.getInventory());
					return true;
				}
				player.openInventory(inventory);
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
			if (Rank.isGM(player.getName())) {
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
		