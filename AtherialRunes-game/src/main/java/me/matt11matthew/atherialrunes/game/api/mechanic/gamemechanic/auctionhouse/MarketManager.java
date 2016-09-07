package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.MarketItem;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

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

    public static void sellItem(ItemStack itemStack, int price, GamePlayer seller) {
        MarketItem item = new MarketItem(itemStack);
        item.setSeller(seller.getNick());
        item.setUUID(UUID.randomUUID());
        item.setPrice(price);
        int pg = findOpenPage();
        if (pg == -1) {
            return;
        }
        Page page = getPage(pg);
        page.addItem(item);
        return;
    }

    public static int findOpenPage() {
        int i = 1;
        while (i < pages.size()) {
            if (!pages.get(i).isFull()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void createPage(int page) {
        pages.put(page, new Page(page));
    }

    public static boolean isPage(int page) {
        return pages.containsKey(page);
    }
}
