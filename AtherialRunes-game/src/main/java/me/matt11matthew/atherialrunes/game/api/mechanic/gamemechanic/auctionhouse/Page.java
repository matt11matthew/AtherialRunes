package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse;

import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.MarketItem;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private int page;
    private List<MarketItem> itemList = new ArrayList<>();

    public Page(int page) {
        this.page = page;
    }

    public List<MarketItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<MarketItem> itemList) {
        this.itemList = itemList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void addItem(MarketItem item) {
        itemList.add(item);
    }

    public MarketItem getItem(int i) {
        return itemList.get(i);
    }
}
