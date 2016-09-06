package me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.auctionhouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import com.mysql.jdbc.PreparedStatement;

import me.matt11matthew.atherialrunes.database.table.Table;
import me.matt11matthew.atherialrunes.game.mechanic.gamemechanic.auctionhouse.menus.MarketItem;
import me.matt11matthew.atherialrunes.item.ItemSerialization;

public class AuctionHouseTable extends Table {

	@Override
	public String getName() {
		return "market";
	}

	@Override
	public void createTable() {
		create("CREATE TABLE IF NOT EXISTS `" + getName() + "` (`id` VARCHAR(100) NOT NULL, `item` VARCHAR(100) NOT NULL, `seller` varchar(50) NOT NULL, `price` INT) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
	}
	
	public void saveItems() {
		try {
			for (MarketItem item : AuctionHouseMechanics.items.values()) {
				UUID uuid = item.getUUID();
				String parse = ItemSerialization.itemStackToBase64(item.buildRawItem());
				String seller = item.getSeller();
				int price = (int) item.getPrice();
				this.insert("INSERT INTO " + getName()
				+ "(id, item, seller, price) "
				+ "VALUES("
				+ "'" + uuid
				+ "'" + parse
				+ "', '" + seller
				+ "', '" + price + "') "
				+ "ON DUPLICATE KEY UPDATE "
				+ "id='" + uuid
				+ "', item='" + parse
				+ "', seller='" + seller
				+ "', price='" + price + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadItems() {
		PreparedStatement pst;

		try {
			pst = (PreparedStatement) getConnection().prepareStatement("SELECT * FROM " + getName() + " order by id");

			pst.execute();
			ResultSet rs = pst.getResultSet();

			while (rs.next()) {			
				UUID id = UUID.fromString(rs.getString("id"));
				String item = rs.getString("item");
				String seller = rs.getString("seller");
				int price = rs.getInt("price");
				ItemStack itemStack = ItemSerialization.itemStackFromBase64(item);
				MarketItem marketItem = new MarketItem(itemStack);
				marketItem.setUUID(id);
				marketItem.setPrice(price);
				marketItem.setSeller(seller);
				AuctionHouseMechanics.items.put(id, marketItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
		


