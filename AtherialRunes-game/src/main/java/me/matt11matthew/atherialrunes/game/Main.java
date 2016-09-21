package me.matt11matthew.atherialrunes.game;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.command.AtherialCommandManager;
import me.matt11matthew.atherialrunes.database.DatabaseAPI;
import me.matt11matthew.atherialrunes.database.data.player.PlayerData;
import me.matt11matthew.atherialrunes.database.data.player.UUIDData;
import me.matt11matthew.atherialrunes.game.api.mechanic.Mechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.MechanicManager;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.HealthMechanic;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.MotdMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.AuctionHouseMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.auctionhouse.menus.FirstMenu;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.combat.CombatMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.combat.commands.CommandPvPFlag;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.damage.DamageMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.ItemMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.item.commands.CommandGiveCustomItem;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.LevelingMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.commands.CommandAddEXP;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.level.commands.CommandSetLevel;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.commands.CommandSell;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.market.commands.CommandView;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.notoriety.NotorietyMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.NPCMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.commands.CommandAddNPC;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.npc.commands.CommandRemoveNPC;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.player.PlayerMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.ChatChannel;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.RankMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.commands.CommandChannel;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.server.ServerMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.server.commands.CommandForceReboot;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.server.commands.CommandReboot;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard.ShardMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard.commands.CommandShard;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner.SpawnerMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner.commands.CommandHideMs;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.spawner.commands.CommandShowMs;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.StaffMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.CommandAdminMode;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.CommandInvsee;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.CommandKick;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.CommandVanish;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.ban.CommandBan;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.ban.CommandUnBan;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.mute.CommandMute;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.staff.commands.mute.CommandUnmute;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.stats.StatMechanics;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.zone.ZoneMechanics;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.api.player.PlayerToggle;
import me.matt11matthew.atherialrunes.game.commands.CommandDEV;
import me.matt11matthew.atherialrunes.game.commands.CommandReload;
import me.matt11matthew.atherialrunes.game.commands.CommandSetRank;
import me.matt11matthew.atherialrunes.game.commands.CommandZone;
import me.matt11matthew.atherialrunes.game.network.NetworkUtils;
import me.matt11matthew.atherialrunes.game.network.ShardInfo;
import me.matt11matthew.atherialrunes.game.network.bungeecord.BungeeUtils;
import me.matt11matthew.atherialrunes.game.utils.AtherialUtils;
import me.matt11matthew.atherialrunes.game.utils.BossbarUtils;
import me.matt11matthew.atherialrunes.game.utils.BungeeChannelListener;
import me.matt11matthew.atherialrunes.menu.MenuManager;
import me.matt11matthew.atherialrunes.player.AtherialPlayer;
import me.matt11matthew.atherialrunes.utils.Utils;
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

	private static ShardInfo shard;
	private String shardId;

	private String port;
	//** registries **//
	private ItemRegistry itemRegistry;
	private PlayerRegistry playerRegistry;

	public void onEnable() {
		instance = this;
		print("Enabling game...");
		loadShard();
		registerCommands();
		BungeeUtils.setPlugin(this);
		registerMechanics();
		registerMenus();
		AtherialUtils.load();
		DatabaseAPI.loadDatabaseAPI();
		BossbarUtils.setHPAboveHead();
		NetworkUtils.registerServer("127.0.0.1", "25666", 0);
		NetworkUtils.registerServer("127.0.0.1", "25667", 1);
		NetworkUtils.load();
		NetworkUtils.sendPacketCrossServer("[online]ID:" + shard.getPseudoName(), Integer.parseInt(shardId), true);
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
			if (s[i].contains("port: ")) {
				port = s[i].split("port: ")[1].trim();
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
		cm.registerCommand(new CommandReload("gamereload", "/gamereload", "Reloads game stuff", Arrays.asList("reloadgame")));
		cm.registerCommand(new CommandChannel("channel", "/channel", "Changes your chat channel", Arrays.asList("ch", "chatchannel")));
		cm.registerCommand(new CommandSell("selltest", "/selltest", "Test"));
		cm.registerCommand(new CommandView("ahtest", "/ahtest", "Test"));
		cm.registerCommand(new CommandReboot("reboot", "/reboot", "Views reboot time"));
		cm.registerCommand(new CommandForceReboot("forcereboot", "/forcereboot", "Force starts a reboot."));
		cm.registerCommand(new CommandShowMs("showms", "/showms", "Shows spawners", Arrays.asList("showmobs", "showspawners", "showmobspawners")));
		cm.registerCommand(new CommandHideMs("hidems", "/hidems", "Hides spawners", Arrays.asList("hidemobs", "hidespawners", "hidemobspawners")));
		cm.registerCommand(new CommandAddNPC("addnpc", "/addnpc", "Spawns in a NPC."));
		cm.registerCommand(new CommandRemoveNPC("removenpc", "/removenpc", "Removes a NPC."));
	}

	private void registerMechanics() {
		registerMechanic(new ServerMechanics());
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
		if (!Constants.LOCALHOST) {
			registerMechanic(new BungeeChannelListener());
		}
		registerMechanic(new ItemMechanics());
		registerMechanic(new NotorietyMechanics());
		registerMechanic(new StatMechanics());
		registerMechanic(new DamageMechanics());
		registerMechanic(new NPCMechanics());
		//registerMechanic(new MarketMechanics());
		MechanicManager.loadMechanics();

	}

	private void registerMenus() {
		MenuManager.registerMenu(new FirstMenu());

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
		PlayerToggle playerToggle = new PlayerToggle(gp);
		playerToggle.addToggles(p.getToggles());
		gp.setPlayerToggle(playerToggle);
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

	public static void stop() {
	}

	//get registries
	public ItemRegistry getItemRegistry() {
		return itemRegistry;
	}

	public PlayerRegistry getPlayerRegistry() {
		return playerRegistry;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
