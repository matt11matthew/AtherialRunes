package me.matt11matthew.atherialrunes.network;

import java.util.Arrays;
import java.util.Optional;

public enum ShardInfo
{

    // SHARD //
    US0("us0", "US-0", new ServerAddress("localhost", 25571)),
	US1("us1", "US-1", new ServerAddress("localhost", 25572));
  
    private final String shardID;
    private final String pseudoName;
    private final ServerAddress address;

    ShardInfo(String shardID, String pseudoName, ServerAddress address)
    {
        this.shardID = shardID;
        this.pseudoName = pseudoName;
        this.address = address;
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
}
