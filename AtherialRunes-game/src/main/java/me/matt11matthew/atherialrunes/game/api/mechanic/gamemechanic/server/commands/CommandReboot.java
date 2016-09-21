package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.server.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.server.ServerMechanics;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandReboot extends AtherialCommand {
    public CommandReboot(String command, String usage, String description) {
        super(command, usage, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(Utils.colorCodes("&cNext reboot in " + ServerMechanics.parseRebootTime()));
        return true;
    }
}
