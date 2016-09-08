package me.matt11matthew.atherialrunes.game.api.tab.variables;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import org.bukkit.entity.Player;

public class NotorietyVariable extends Variable {

    public NotorietyVariable() {
        super("notoriety");
    }

    @Override
    public String getReplacement(Player player) {
        GamePlayer gp = Main.getGamePlayer(player);
        return gp.getNotoriety() + "";
    }
}