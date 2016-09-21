package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market;

import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.game.GameConstants;
import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import me.matt11matthew.atherialrunes.item.ItemSerialization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarketPage {

    private int pageNumber;

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
            newPage.addItem(auctionItem);
            return newPage;
        }
        return page;
    }

    public  int getItemSize() {
        try {
            return getItems().size();
        } catch (Exception e) {
            return 0;
        }
    }

    public static void createPage() {
        int pageAmount = MarketMechanics.pages.size();
        int newPageAmount = (pageAmount + 1);
        MarketPage marketPage = new MarketPage(newPageAmount);
        MarketMechanics.pages.put(newPageAmount, marketPage);
    }

    public void addItem(AuctionItem item) {
        AtherialRunnable.getInstance().runTaskAsynchronously(Main.getInstance(), () -> {
            PreparedStatement pst = null;
            try {
                pst = ConnectionPool.getConnection().prepareStatement("insert into shops (id, item, seller, price, time, page) values ('" + item.getUUID() + "', '" + ItemSerialization.itemStackToBase64(item.buildRawItem()) + "', '" + item.getSeller() + "', '" + item.getPrice() + "', '" + item.getTime() + "', '" + item.getPage().getPageNumber() + "');");
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        });
    }

    public void removeItem(AuctionItem item) {
        AtherialRunnable.getInstance().runTaskAsynchronously(Main.getInstance(), () -> {
            PreparedStatement pst = null;
            try {
                pst = ConnectionPool.getConnection().prepareStatement("delete * from shops where id = " + item.getUUID());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        });
    }

    public List<AuctionItem> getItems() {
        List<AuctionItem> itemList = new ArrayList<>();
        AtherialRunnable.getInstance().runTaskAsynchronously(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                PreparedStatement pst = null;
                try {
                    pst = ConnectionPool.getConnection().prepareStatement("select * from shops");
                    pst.execute();
                    ResultSet rs = pst.getResultSet();
                    if (rs.next()) {
                        String id = rs.getString("id");
                        String item = rs.getString("item");
                        int cost = rs.getInt("price");
                        String seller = rs.getString("seller");
                        long time = rs.getLong("time");
                        int page = rs.getInt("page");
                        AuctionItem auctionItem = new AuctionItem(item);
                        auctionItem.setUUID(id);
                        auctionItem.setPrice(cost);
                        auctionItem.setPage(MarketPage.get(page));
                        auctionItem.setSeller(seller);
                        auctionItem.setTime(time);
                        itemList.add(auctionItem);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return itemList;
    }
}