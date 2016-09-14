package me.matt11matthew.atherialrunes.game.utils;

import me.matt11matthew.atherialrunes.database.ConnectionPool;
import me.matt11matthew.atherialrunes.database.table.tables.player.PlayerdataTable;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtherialUtils {

	/**
	 * @param name the player name
	 * @return if they are a player or not
	 */
	public static boolean isPlayer(String name) {
		PreparedStatement pst = null;
		try {
			AtherialPlayer player = new AtherialPlayer(name);
			pst =  ConnectionPool.getConnection().prepareStatement("SELECT * FROM `" + new PlayerdataTable().getName() + "` WHERE `uuid` ='" + player.getUUID() + "'");
			pst.execute();
			ResultSet rs = pst.getResultSet();
			return rs.next();
		} catch (SQLException e) {
			return false;
		}
	}

	public static String nmsver;

	public static void load() {
		nmsver = Bukkit.getServer().getClass().getPackage().getName();
		nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
	}

	public static void sendActionBar(Player player, String message) {
		// Call the event, if cancelled don't send Action Bar

		try {
			Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
			Object p = c1.cast(player);
			Object ppoc;
			Class<?> c4 = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
			Class<?> c5 = Class.forName("net.minecraft.server." + nmsver + ".Packet");
			Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
			Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
			Object o = c2.getConstructor(new Class<?>[]{String.class}).newInstance(message);
			ppoc = c4.getConstructor(new Class<?>[]{c3, byte.class}).newInstance(o, (byte) 2);
			Method m1 = c1.getDeclaredMethod("getHandle");
			Object h = m1.invoke(p);
			Field f1 = h.getClass().getDeclaredField("playerConnection");
			Object pc = f1.get(h);
			Method m5 = pc.getClass().getDeclaredMethod("sendPacket", c5);
			m5.invoke(pc, ppoc);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}