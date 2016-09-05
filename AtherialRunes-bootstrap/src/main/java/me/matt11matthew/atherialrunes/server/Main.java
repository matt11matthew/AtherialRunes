package me.matt11matthew.atherialrunes.server;

import java.io.IOException;

import com.avaje.ebeaninternal.server.cluster.Packet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.minlog.Log;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.network.MasterServer;
import me.matt11matthew.atherialrunes.network.packet.BasicMessagePacket;

public class Main {
	
	private static Kryo kryo;
    
	public static void main(String[] args) {
		print("Starting MasterServer...");
		print("Version " + Constants.SERVER_VERSION);
		print("Build #" + Constants.BUILD);
		print("MasterServer Info -> " + Constants.MASTER_SERVER_IP + ":" + Constants.MASTER_SERVER_PORT);
		try {
            MasterServer server = new MasterServer();
            Log.info("Listening on " + Constants.MASTER_SERVER_IP + ":" + Constants.MASTER_SERVER_PORT);
            kryo = server.getKryo();

            Log.set(Log.LEVEL_INFO);
            registerClasses();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private static void registerClasses() {
		kryo.register(Packet.class);
        kryo.register(byte.class);
        kryo.register(byte[].class);
        kryo.register(BasicMessagePacket.class);
		
	}

	public static void print(String s) {
		System.out.println(s);
	}	
}