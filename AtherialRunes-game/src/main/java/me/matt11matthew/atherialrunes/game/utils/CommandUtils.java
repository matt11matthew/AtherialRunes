package me.matt11matthew.atherialrunes.game.utils;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandUtils {

    /**
     *
     * @param args command args
     * @param items the item
     * @param addPlayers if you have players
     * @return the return string
     */
    public static List<String> getPossibleCompletionsForGivenArgs(String[] args, List<CustomItem> items, boolean addPlayers) {
        String arg = args[args.length-1].toLowerCase();
        ArrayList<String> possableComplete = new ArrayList<>();

        for (CustomItem s : items) {
            if (s.getFile().getName().split(".json")[0].toLowerCase().startsWith(arg)) {
                possableComplete.add(s.getFile().getName().split(".json")[0]);
            }
        }
        if (addPlayers) {

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().startsWith(arg)) {
                    possableComplete.add(p.getName());
                } else {
                    possableComplete.add(p.getName());
                }
            }
        }
        return possableComplete;
    }
}