package me.matt11matthew.atherialrunes.discord.command.commands;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import me.matt11matthew.atherialrunes.discord.command.Command;
import me.matt11matthew.atherialrunes.discord.enums.Role;

import java.util.Arrays;
import java.util.List;

public class CommandSpam implements Command {
    @Override
    public void execute(User user, String[] args, DiscordAPI api, Message msg) {
        msg.reply("SPAM");
    }

    @Override
    public List<String> getRoles() {
        return Arrays.asList(Role.LEAD_DEVELOPER.getName());
    }

}
//add inventory saving