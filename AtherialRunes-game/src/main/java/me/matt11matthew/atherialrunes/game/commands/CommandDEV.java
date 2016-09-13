package me.matt11matthew.atherialrunes.game.commands;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.utils.hologram.HologramManager;
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
		if (!(sender instanceof Player)) {
			try {
				ConnectionPool.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Constants.DEBUG) {
				try {
					test(Main.getGamePlayer(player), args[0], Integer.parseInt(args[1]));
				} catch (Exception e) {
					return true;
				}
				return true;
			}
		}
		return true;
	}
	
	public void test(GamePlayer gp, String message, int time) {
		HologramManager.getInstance().spawn(message, gp.getPlayer().getLocation(), time);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return null;
	}
}
		