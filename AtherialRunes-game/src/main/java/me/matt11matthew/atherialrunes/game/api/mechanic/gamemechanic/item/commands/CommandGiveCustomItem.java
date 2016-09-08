package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.CustomItem;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.ItemMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.utils.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandGiveCustomItem extends AtherialCommand {

    public CommandGiveCustomItem(String command, String usage, String description, List<String> aliases) {
        super(command, usage, description, aliases);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!Rank.isGM(player.getName())) {
                return true;
            }
            if (args.length == 1) {
                String item = args[0];
                try {
                    GamePlayer gp = Main.getGamePlayer(player.getName());

                    if (ItemMechanics.customItems.containsKey(item)) {
                        player.getInventory().addItem(ItemMechanics.customItems.get(item).buildItem());
                        return true;
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Rank.isGM(player.getName())) {
                try {
                    GamePlayer gp = Main.getGamePlayer(player.getName());

                    return CommandUtils.getPossibleCompletionsForGivenArgs(args, (List<CustomItem>) ItemMechanics.customItems.values(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }
}