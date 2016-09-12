package me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.thread;

import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.AuctionItem;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.MarketMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.MarketPage;
import me.matt11matthew.atherialrunes.item.ItemSerialization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefreshThread extends Thread {

    @Override
    public void run() {
        save();
        refresh();
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
                int cost = rs.getInt("cost");
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
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void save() {
        try {
            MarketMechanics.items.values().forEach(item -> {
                if (has(item.getUUID())) {
                    update(item);
                } else {
                    insert(item);
                }
                MarketMechanics.items.remove(item.getUUID());
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void insert(AuctionItem item) {
        PreparedStatement pst = null;
        try {
            pst = ConnectionPool.getConnection().prepareStatement("insert into shops (id, item, seller, price, time, page) values (?, ?, ?, ?, ?, ?);");
            pst.setString(1, item.getUUID());
            pst.setString(2, ItemSerialization.itemStackToBase64(item.buildRawItem()));
            pst.setString(3, item.getSeller());
            pst.setInt(4, item.getPrice());
            pst.setLong(5, item.getTime());
            pst.setInt(6, item.getPage().getPageNumber());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;

    }

    private void update(AuctionItem item) {
        PreparedStatement pst = null;
        try {
            pst = ConnectionPool.getConnection().prepareStatement("update shops set id=" + item.getUUID() + " where id=" + item.getUUID() + ";");
            pst.execute();
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
