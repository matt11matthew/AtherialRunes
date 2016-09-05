package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.combat.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.combat.CombatMechanics;
import me.matt11matthew.atherialrunes.game.player.GamePlayer;
import me.matt11matthew.atherialrunes.utils.Utils;

public class CommandPvPFlag extends AtherialCommand {
	
	public CommandPvPFlag(String command, String usage, String description, List<String> aliases) {
		super(command, usage, description, aliases);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.isOp()) {
			if (args.length == 1) {
				String pname = args[0];
				GamePlayer gp = Main.getGamePlayer(pname);
				CombatMechanics.flagPvP(gp);
				sender.sendMessage(Utils.colorCodes("&cYou have flagged " + pname + " in &lCOMBAT"));
				return true;
			}
		}
		return true;
	}
}
