package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.staff.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.player.GamePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandAdminMode extends AtherialCommand {

    public CommandAdminMode(String command, String usage, String description, List<String> aliases) {
        super(command, usage, description, aliases);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Rank.isGM(player.getName())) {
                if (args.length == 1) {
                    String nick = args[0];
                    GamePlayer gp = Main.getGamePlayer(player.getName());
                    if (gp.isInAdminMode()) {
                        gp.setAdminMode(false);
                        gp.setNick(nick);
                        gp.kick("Console", "&cYour now playing legit\n" + "Nick: " + gp.getNick());
                        return true;
                    } else {
                        gp.setAdminMode(true);
                        gp.setNick(gp.getName());
                        gp.kick("Console", "&cYour now in adminmode");
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
