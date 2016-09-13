package me.matt11matthew.atherialrunes.game.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.ItemMechanics;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandReload extends AtherialCommand {
    public CommandReload(String command, String usage, String description, List<String> aliases) {
        super(command, usage, description, aliases);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.getName().equals("matt11matthew")) {
                return true;
            }
        }
        String itemInfo = "";
        String itemColor = "";
        try {
            try {
                ItemMechanics.reload();
                itemInfo = "Success";
                itemColor = "&a";
            } catch (ReloadException e) {
                itemInfo = "Failed";
                itemColor = "&c";
            }
        } catch (Exception e) {
        } finally {
            sender.sendMessage(Utils.colorCodes("&cReload completed!"));
            String info =
                    "&a-------------------------------," +
                    "&bItemMechanics: &n" + itemColor + itemInfo + "," +
                    "&a-------------------------------";
            info = info.replaceAll(",", System.lineSeparator());
            sender.sendMessage(Utils.colorCodes(info));
            return true;
        }
    }
}