package me.matt11matthew.atherialrunes.discord.command.commands;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.discord.command.Command;

import java.util.List;

public class CommandInfo implements Command {
    @Override
    public void execute(User user, String[] args, DiscordAPI api, Message msg) {
        String help = "------------------------------------," + "AtherialRunes Runes Help" + "," + "Website: " + Constants.WEBSITE_LINK + "," + "Developers: " + getDevelopers() + "," + "Leads: " + getLeads() + ",------------------------------------,";
        help = help.replaceAll(",", System.lineSeparator());
        help = help.replaceAll("/", ",");
        user.sendMessage(help);
    }

    @Override
    public List<String> getRoles() {
        return null;
    }

    private String getDevelopers() {
        return "[matt11matthew]";
    }

    private String getLeads() {
        return "[Jacky/ Hunter]";
    }
}
//add inventory saving