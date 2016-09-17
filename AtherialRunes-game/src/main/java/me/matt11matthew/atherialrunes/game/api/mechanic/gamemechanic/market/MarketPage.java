package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market;

import me.matt11matthew.atherialrunes.game.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class MarketPage {

    private int pageNumber;
    public List<AuctionItem> items = new ArrayList<>();

    public MarketPage(int newPageAmount) {
        this.pageNumber = newPageAmount;
    }

    public static MarketPage get(int page) {
        return MarketMechanics.pages.get(page);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public static MarketPage newPage(AuctionItem auctionItem) {
        int pageAmount = MarketMechanics.pages.size();
        if (pageAmount == 0) {
            MarketPage.createPage();
            return MarketMechanics.pages.get(1);
        }
        MarketPage page = MarketMechanics.pages.get(pageAmount);
        int newPageAmount = (pageAmount + 1);
        if (page.getItemSize() >= GameConstants.MAX_MARKET_ITEMS_PER_PAGE) {
            MarketPage.createPage();
            MarketPage newPage = MarketPage.get(newPageAmount);
            newPage.items.add(auctionItem);
            return newPage;
        }
        return page;
    }

    public  int getItemSize() {
        int size = 0;
        try {
            size = items.size();
        } catch (Exception e) {
            size = 0;
        }
        return size;
    }

    public static void createPage() {
        int pageAmount = MarketMechanics.pages.size();
        int newPageAmount = (pageAmount + 1);
        MarketPage marketPage = new MarketPage(newPageAmount);
        MarketMechanics.pages.put(newPageAmount, marketPage);
    }

    public void addItem(AuctionItem auctionItem) {
        if (items.contains(auctionItem)) {
            items.remove(auctionItem);
            items.add(auctionItem);
            return;
        }
        items.add(auctionItem);
    }
}
