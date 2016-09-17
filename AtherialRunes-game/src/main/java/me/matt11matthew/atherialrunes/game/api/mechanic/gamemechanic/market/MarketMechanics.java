package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market;

import me.matt11matthew.atherialrunes.game.api.mechanic.ListenerMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.LoadPriority;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.thread.Refresh;
import org.apache.logging.log4j.core.helpers.UUIDUtil;
import org.bukkit.inventory.ItemStack;
import java.util.concurrent.ConcurrentHashMap;

public class MarketMechanics extends ListenerMechanic {

    static MarketMechanics instance;

    public static ConcurrentHashMap<String, AuctionItem> items = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Integer, MarketPage> pages = new ConcurrentHashMap<>();

    public static MarketMechanics getInstance() {
        if (instance == null) {
            instance = new MarketMechanics();
        }
        return instance;
    }

    @Override
    public void onEnable() {
        print("-----------------------------------------");
        print("[MarketMechanics] Enabling...");
        print("-----------------------------------------");
        registerListeners();
        new Refresh().load();
    }

    @Override
    public void onDisable() {
        print("-----------------------------------------");
        print("[MarketMechanics] Disabling...");
        print("-----------------------------------------");
    }

    @Override
    public LoadPriority getLoadPriority() {
        return LoadPriority.NORMAL;
    }

    public void addItem(AuctionItem item) {
        sellItem(item.buildRawItem(), item.getPrice(), item.getSeller());
    }

    public void sellItem(ItemStack item, int price, String seller) {
        AuctionItem auctionItem = new AuctionItem(item);
        auctionItem.setSeller(seller);
        auctionItem.setPrice(price);
        auctionItem.setUUID(UUIDUtil.getTimeBasedUUID().toString());
        auctionItem.setPage(MarketPage.newPage(auctionItem));
        items.put(auctionItem.getUUID(), auctionItem);
        auctionItem.getPage().addItem(auctionItem);
    }
}
