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
        MarketPage page = MarketMechanics.pages.get(pageAmount);
        int newPageAmount = (pageAmount + 1);
        if (page.items.size() >= GameConstants.MAX_MARKET_ITEMS_PER_PAGE) {
            MarketPage.createPage();
            MarketPage newPage = MarketPage.get(newPageAmount);
            newPage.items.add(auctionItem);
            return newPage;
        }
        return page;
    }

    public static void createPage() {
        int pageAmount = MarketMechanics.pages.size();
        int newPageAmount = (pageAmount + 1);
        MarketPage marketPage = new MarketPage(newPageAmount);
        MarketMechanics.pages.put(newPageAmount, marketPage);
    }
}
