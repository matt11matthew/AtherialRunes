package me.matt11matthew.atherialrunes.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.matt11matthew.atherialrunes.lobby.shard.ShardLoader;

public class CommandShard implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.openInventory(ShardLoader.SHARD_MENU);
			return true;
		}
		return true;
	}
}
