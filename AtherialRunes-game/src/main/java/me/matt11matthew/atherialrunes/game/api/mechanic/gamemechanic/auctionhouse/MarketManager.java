package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse;

import java.util.HashMap;

public class MarketManager {

    static MarketManager instance = null;
    public static HashMap<Integer, Page> pages = new HashMap<>();

    public static MarketManager getInstance() {
        if (instance == null) {
            instance = new MarketManager();
        }
        return instance;
    }

    public static Page getPage(int page) {
        if (pages.containsKey(page)) {
            return pages.get(page);
        }
        return null;
    }

    public static void createPage(int page) {
        pages.put(page, new Page(page));
    }

    public static boolean isPage(int page) {
        return pages.containsKey(page);
    }
}
