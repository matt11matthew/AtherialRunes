package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.NPCMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAddNPC extends AtherialCommand {
    public CommandAddNPC(String command, String usage, String description) {
        super(command, usage, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Rank.isGM(player.getName())) {
                GamePlayer gp = Main.getGamePlayer(player);
                if (args.length == 2) {
                    String id = args[0];
                    String name = args[1].replaceAll("_", " ");
                    if (NPCMechanics.npcs.containsKey(id)) {
                        gp.msg(MessageType.CHAT, "&cThe NPC " + id + " already exists!");
                        return true;
                    }
                    NPCMechanics.createPlayerNPC(player.getLocation(), name, id);
                    gp.msg(MessageType.CHAT, "&aYou've created the NPC " + id + ":" + name);
                    return true;
                } else {
                    gp.msg(MessageType.CHAT, "&c/addnpc <id> <name>");
                    return true;
                }
            }
        }
        return true;
    }
}
