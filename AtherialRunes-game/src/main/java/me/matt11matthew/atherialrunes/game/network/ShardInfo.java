package me.matt11matthew.atherialrunes.game.network;


import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard.ServerType;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.shard.Shard;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.item.AtherialItem;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Optional;

public enum ShardInfo
{

    // SHARD //
    US0("0", "US-0", new ServerAddress("localhost", 25571), ServerType.DEVELOPER, 200, "us0"),
	US1("1", "US-1", new ServerAddress("localhost", 25572), ServerType.LIVE, 200, "us1");
  
    private final String shardID;
    private final String pseudoName;
    private final ServerAddress address;
    private final ServerType type;
    private final int maxplayercount;
    private final String bungeeName;

    ShardInfo(String shardID, String pseudoName, ServerAddress address, ServerType type, int maxplayercount, String bungeeName)
    {
        this.shardID = shardID;
        this.pseudoName = pseudoName;
        this.address = address;
        this.type = type;
        this.maxplayercount = maxplayercount;
        this.bungeeName = bungeeName;
    }


    public static ShardInfo getByPseudoName(String pseudoName)
    {
        Optional<ShardInfo> query = Arrays.stream(ShardInfo.values()).
                filter(info -> info.getPseudoName().equals(pseudoName)).findFirst();

        return query.isPresent() ? query.get() : null;
    }

    public static ShardInfo getByShardID(String shardID)
    {
        Optional<ShardInfo> query = Arrays.stream(ShardInfo.values()).
                filter(info -> info.getShardID().equals(shardID)).findFirst();

        return query.isPresent() ? query.get() : null;
    }

    public String getShardID() {
		return shardID;
	}


	public static ShardInfo getByAddress(ServerAddress address)
    {
        Optional<ShardInfo> query = Arrays.stream(ShardInfo.values()).
                filter(info -> info.getAddress().toString().equals(address.toString())).findFirst();

        return query.isPresent() ? query.get() : null;
    }

	public String getPseudoName() {
		return pseudoName;
	}

	public static ShardInfo[] getValues() {
		return values();
	}

	public ServerAddress getAddress() {
		return address;
	}

    public int getMaxPlayers() {
        return maxplayercount;
    }

    public ServerType getType() {
        return type;
    }

    public Shard getShard() {
        return new Shard(getPseudoName(), getAddress().getAddress(), getAddress().getPort(), getType(), getMaxPlayers(), getBungeeName());
    }

    public static final int GREEN = 5;
    public static final int RED = 14;

    public AtherialItem getShardItem(GamePlayer gp) {
        Shard info = getShard();

        short dura = (short) ((info.isOnline()) ? 0 : RED);
        if (gp.getShard().equals(getPseudoName())) {
            dura = GREEN;
        }
        AtherialItem item = new AtherialItem(Material.WOOL, dura);
        item.setName("&a" + getPseudoName() + " &7(" + info.getOnlinePlayers() + "/" + getMaxPlayers() + ")");
        item.addLore(getType().getPrefix());
        item.addLore("&7" + getShardID());
        return item;
    }

    public String getBungeeName() {
        return bungeeName;
    }
}
