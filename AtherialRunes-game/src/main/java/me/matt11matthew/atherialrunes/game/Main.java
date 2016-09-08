package me.matt11matthew.atherialrunes.game;


import codecrafter47.bungeetablistplus.api.bukkit.BungeeTabListPlusBukkitAPI;
import com.esotericsoftware.minlog.Log;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.matt11matthew.atherialrunes.command.AtherialCommandManager;
import me.matt11matthew.atherialrunes.database.DatabaseAPI;
import me.matt11matthew.atherialrunes.database.data.player.PlayerData;
import me.matt11matthew.atherialrunes.database.data.player.UUIDData;
import me.matt11matthew.atherialrunes.game.api.exception.LoaderNotHandledException;
import me.matt11matthew.atherialrunes.game.api.mechanic.Mechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.MechanicManager;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.HealthMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.MotdMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.AuctionHouseMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.FirstMenu;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.combat.CombatMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.combat.commands.CommandPvPFlag;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.ItemMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.commands.CommandGiveCustomItem;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.LevelingMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.commands.CommandAddEXP;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.commands.CommandSetLevel;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.notoriety.NotorietyMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.player.PlayerMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.ChatChannel;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.RankMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard.ShardMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard.ShardMenu;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard.commands.CommandShard;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner.SpawnerMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.StaffMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.CommandAdminMode;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.CommandInvsee;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.CommandKick;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.CommandVanish;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.ban.CommandBan;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.ban.CommandUnBan;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.mute.CommandMute;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.mute.CommandUnmute;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.zone.ZoneMechanics;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.api.registry.RegistryLoader;
import me.matt11matthew.atherialrunes.game.api.tab.variables.NotorietyVariable;
import me.matt11matthew.atherialrunes.game.api.tab.variables.ShardNameVariable;
import me.matt11matthew.atherialrunes.game.commands.CommandDEV;
import me.matt11matthew.atherialrunes.game.commands.CommandSetRank;
import me.matt11matthew.atherialrunes.game.commands.CommandZone;
import me.matt11matthew.atherialrunes.game.utils.AtherialUtils;
import me.matt11matthew.atherialrunes.game.utils.BossbarUtils;
import me.matt11matthew.atherialrunes.game.utils.BungeeChannelListener;
import me.matt11matthew.atherialrunes.game.utils.NetworkClientListener;
import me.matt11matthew.atherialrunes.menu.MenuManager;
import me.matt11matthew.atherialrunes.network.GameClient;
import me.matt11matthew.atherialrunes.network.ShardInfo;
import me.matt11matthew.atherialrunes.network.bungeecord.BungeeUtils;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	public static List<String> staff = new ArrayList<String>();
	
	private static GameClient client;
	private static ShardInfo shard;
	private String shardId;

	//** registries **//
	private ItemRegistry itemRegistry;
	private PlayerRegistry playerRegistry;

	public void onEnable() {
		instance = this;
		print("Enabling game...");
		loadShard();
		registerCommands();
		registerMechanics();
		registerMenus();
		BungeeUtils.setPlugin(this);
		AtherialUtils.load();
		BungeeTabListPlusBukkitAPI.registerVariable(this, new NotorietyVariable());
		BungeeTabListPlusBukkitAPI.registerVariable(this, new ShardNameVariable());
		client = new GameClient();

        try {
            client.connect();
            Log.set(Log.LEVEL_INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }

		//Registries must be loaded after connection establishment.
        //After it has been loaded into the server, it can be used.
		try
		{
			RegistryLoader.load().register(playerRegistry = new PlayerRegistry()).register(itemRegistry = new ItemRegistry()).manageLoad();
		} catch (LoaderNotHandledException e)
		{
			e.printStackTrace();
		}
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		DatabaseAPI.loadDatabaseAPI();
		BossbarUtils.setHPAboveHead();
        BungeeUtils.setPlugin(this);
	}
	
	public String getShardFile() {
		File file = new File("shardconfig.shard");
		String text = null;
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			text = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return text;
	}
	
	private void loadShard() {
		String ss = getShardFile();
		String[] s = ss.split("\n");
		for (int i = 0; i < s.length; i++) {
			if (s[i].contains("id: ")) {
				shardId = s[i].split("id: ")[1].trim();
			}
		}
		shard = ShardInfo.getByShardID(shardId);
	}
	
	public void onDisable() {
		MechanicManager.disableMechanics();
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static void print(String s) {
		System.out.println(s);
	}
	
	private void registerCommands() {
		AtherialCommandManager cm = new AtherialCommandManager();
		cm.registerCommand(new CommandSetRank("setrank", "/setrank <player> <rank>", "Sets the rank of a player.", Arrays.asList("setgroup")));
		cm.registerCommand(new CommandZone("zone", "/zone", "The zone you are in.", Arrays.asList("z")));
		cm.registerCommand(new CommandDEV("dev", "/dev", "Dev command."));
		cm.registerCommand(new CommandKick("kick", "/kick <player> <reason>", "Kicks a player."));
		cm.registerCommand(new CommandBan("ban", "/ban <player> <time> <reason>", "Bans a player."));
		cm.registerCommand(new CommandUnBan("unban", "/unban <player> <reason>", "Unbans a player."));
		cm.registerCommand(new CommandInvsee("invsee", "/invsee <player>", "Opens a players inventory.", Arrays.asList("viewinventory", "viewinv", "showinv", "showinventory", "inventorysee")));
		cm.registerCommand(new CommandPvPFlag("tag", "/tag <player>", "Tags a player in pvp.", Arrays.asList("pvpflag", "pvptag")));
		cm.registerCommand(new CommandShard("shard", "/shard", "Opens the shard menu."));
		cm.registerCommand(new CommandMute("mute", "/mute <player> <time> <reason>", "Mutes a player."));
		cm.registerCommand(new CommandUnmute("unmute", "/unmute <player> <reason>", "Unmutes a player."));
		cm.registerCommand(new CommandVanish("vanish", "/vanish", "Vanish.", Arrays.asList("atherialvanish", "hide")));
		cm.registerCommand(new CommandSetLevel("setlevel", "/setlevel <player> <level>", "Set a players level.", Arrays.asList("setlvl")));
		cm.registerCommand(new CommandGiveCustomItem("givecustomitem", "/givecustomitem <item>", "Gives yourself a custom item.", Arrays.asList("addcustomitem")));
		cm.registerCommand(new CommandAdminMode("adminmode", "/adminmode <nick>", "Go into adminmode", Arrays.asList("globaladminmode")));
		cm.registerCommand(new CommandAddEXP("addexp", "/addexp <player> <exp>", "Gives yourself exp", Arrays.asList("giveexp")));
	}
	
	private void registerMechanics() {
		registerMechanic(new LevelingMechanics());
		registerMechanic(new HealthMechanic());
		registerMechanic(new RankMechanics());
		registerMechanic(new MotdMechanics());
		registerMechanic(new PlayerMechanics());
		registerMechanic(new ZoneMechanics());
		registerMechanic(new AuctionHouseMechanics());
		registerMechanic(new CombatMechanics());
		registerMechanic(new ShardMechanics());
		registerMechanic(new StaffMechanics());
		registerMechanic(new SpawnerMechanics());
		registerMechanic(new NetworkClientListener());
		registerMechanic(new BungeeChannelListener());
		registerMechanic(new ItemMechanics());
		registerMechanic(new NotorietyMechanics());
		MechanicManager.loadMechanics();
		
	}
	
	 public static void sendNetworkMessage(String task, String message, String... contents) {
	        ByteArrayDataOutput out = ByteStreams.newDataOutput();
	        out.writeUTF(task);
	        out.writeUTF(message);

	        for (String s : contents)
	            out.writeUTF(s);

	        getClient().sendTCP(out.toByteArray());
	    }
	
	private void registerMenus() {
		MenuManager.registerMenu(new FirstMenu());
		MenuManager.registerMenu(new ShardMenu());
	}
	
	public static void registerMechanic(Mechanic mechanic) {
		MechanicManager.mechanics.put(mechanic.getClass().getSimpleName(), mechanic);
	}

	public static GamePlayer getGamePlayer(String pname) {
		if (GamePlayer.players.containsKey(UUIDData.getUUID(pname))) {
			return GamePlayer.players.get(UUIDData.getUUID(pname));
		}
		DatabaseAPI.getInstance().getPlayerData(pname).update();
		AtherialPlayer p = PlayerData.getAtherialPlayer(pname);
		GamePlayer gp = new GamePlayer(p.getName());
		gp.setRank(Rank.valueOf(p.getRank().toUpperCase()));
		gp.setChatChannel(ChatChannel.getChatChannelFromId(p.getChatChannel()));
		gp.setCombatTime(p.getCombatTime());
		gp.setEXP(p.getEXP());
		gp.setLevel(p.getLevel());
		gp.setSkillPoints(p.getSkillPoints());
		gp.setVanished(p.isVanished());
		gp.setNick(p.getNick());
		gp.setNotoriety(p.getNotoriety());
		gp.setAdminMode(p.isInAdminMode());
		GamePlayer.players.put(UUIDData.getUUID(pname), gp);
		return gp;
	}

	public static GamePlayer getGamePlayer(Player player) {
		return getGamePlayer(player.getName());
	}
	
	public static void sendMessageToStaff(String msg) {
		for (String staffName : staff) {
			BungeeUtils.sendPlayerMessage(staffName, Utils.colorCodes(msg));
		}
	}

	public static ShardInfo getShard() {
		return shard;
	}

	public static void setShard(ShardInfo shard) {
		Main.shard = shard;
	}

	public static GameClient getClient() {
		return client;
	}

	public static void setClient(GameClient client) {
		Main.client = client;
	}

	public static void stop() {
		Bukkit.getServer().broadcastMessage(Utils.colorCodes("&cStopping"));
		Bukkit.getServer().getOnlinePlayers().forEach(player -> {
			player.kickPlayer(Utils.colorCodes("&c&lServer rebooting"));
		});
		Bukkit.getServer().spigot().restart();
	}

	//get registries
	public ItemRegistry getItemRegistry()
	{
		return itemRegistry;
	}

	public PlayerRegistry getPlayerRegistry()
	{
		return playerRegistry;
	}
}
