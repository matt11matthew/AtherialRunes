package me.matt11matthew.atherialrunes.game.api.tab.variables;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import org.bukkit.entity.Player;

public class ShardNameVariable extends Variable {

    public ShardNameVariable() {
        super("dr");
    }

    @Override
    public String getReplacement(Player player) {
        return "dr";
    }
}
