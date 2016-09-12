package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.commands;

import me.matt11matthew.atherialrunes.command.AtherialCommand;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.MarketMenu;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.MarketPage;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandView extends AtherialCommand {

    public CommandView(String command, String usage, String description) {
        super(command, usage, description);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            GamePlayer gp = Main.getGamePlayer(player);
            try {
                MarketPage page = MarketPage.get(Integer.parseInt(args[0]));
                gp.msg(MessageType.CHAT, "&aViewing");
                new MarketMenu(page.getPageNumber()).open(player);
                return true;
            } catch (NumberFormatException e) {
                gp.msg(MessageType.CHAT, "&c/viewpage <page>");
                return true;
            }
        }
        return true;
    }
}
