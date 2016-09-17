package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.thread;

import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.AuctionItem;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.MarketMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.MarketPage;
import me.matt11matthew.atherialrunes.game.utils.AtherialRunnable;
import me.matt11matthew.atherialrunes.item.ItemSerialization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Refresh {

    public void load() {
        AtherialRunnable.getInstance().runAsyncRepeatingTask(() -> {
            save();
            refresh();
        }, 1L, 1L);
    }
    private void refresh() {
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
                MarketMechanics.getInstance().addItem(auctionItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean has(String id) {
        PreparedStatement pst = null;
        try {
            pst = ConnectionPool.getConnection().prepareStatement("select * from shops where id = " + id + ";");
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.getString("seller").isEmpty()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void save() {
        MarketMechanics.pages.values().forEach(page -> {
            page.items.forEach(item -> {
                if (has(item.getUUID())) {
                    update(item);
                } else {
                    insert(item);
                }
                page.items.remove(item);
                MarketMechanics.items.remove(item.getUUID());
            });
        });
    }

    private void insert(AuctionItem item) {
        PreparedStatement pst = null;
        try {
            pst = ConnectionPool.getConnection().prepareStatement("insert into shops (id, item, seller, price, time, page) values ('" + item.getUUID() + "', '" + ItemSerialization.itemStackToBase64(item.buildRawItem()) + "', '" + item.getSeller() + "', '" + item.getPrice() + "', '" + item.getTime() + "', '" + item.getPage().getPageNumber() + "');");
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    private void update(AuctionItem item) {
        PreparedStatement pst = null;
        try {
            pst = ConnectionPool.getConnection().prepareStatement("update shops set item=" + ItemSerialization.itemStackToBase64(item.buildRawItem()) + " where id=" + item.getUUID() + ";");
            pst.execute();
            pst = ConnectionPool.getConnection().prepareStatement("update shops set seller=" + item.getSeller() + " where id=" + item.getUUID() + ";");
            pst.execute();
            pst = ConnectionPool.getConnection().prepareStatement("update shops set price=" + item.getPrice() + " where id=" + item.getUUID() + ";");
            pst.execute();
            pst = ConnectionPool.getConnection().prepareStatement("update shops set time=" + item.getTime() + " where id=" + item.getUUID() + ";");
            pst.execute();
            pst = ConnectionPool.getConnection().prepareStatement("update shops set page=" + item.getPage().getPageNumber() + " where id=" + item.getUUID() + ";");
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
