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

public class CommandRemoveNPC extends AtherialCommand {
    public CommandRemoveNPC(String command, String usage, String description) {
        super(command, usage, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Rank.isGM(player.getName())) {
                GamePlayer gp = Main.getGamePlayer(player);
                if (args.length == 1) {
                    String id = args[0];
                    if (!NPCMechanics.npcs.containsKey(id)) {
                        gp.msg(MessageType.CHAT, "&cThe NPC " + id + " doesn't exist!");
                        return true;
                    }
                    NPCMechanics.removePlayerNPC(id);
                    gp.msg(MessageType.CHAT, "&aYou've deleted the NPC " + id + "!");
                    return true;
                } else {
                    gp.msg(MessageType.CHAT, "&c/removenpc <id>");
                    return true;
                }
            }
        }
        return true;
    }
}
