package me.matt11matthew.atherialrunes.network;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.network.packet.BasicMessagePacket;
import me.matt11matthew.atherialrunes.network.packet.Packet;

public class GameClient extends Listener
{

    private Client client;
    private Runnable reconnected;
    private boolean isConnected = false;

    public GameClient()
    {
        this.client = new Client();
        this.client.addListener(this);
        this.client.setKeepAliveTCP(1000);
        this.client.start();

        registerClasses(client.getKryo());
    }

    public void setReconnector(Runnable reconnector)
    {
        this.reconnected = reconnector;
    }

    public void registerListener(Listener listener)
    {
        client.addListener(listener);
    }

    public void removeListener(Listener listener)
    {
        client.removeListener(listener);
    }

    public Client getClient()
    {
        return this.client;
    }

    public boolean isConnected()
    {
        return isConnected;
    }

    public void connect() throws IOException
    {
        Log.info("Connecting to " + Constants.MASTER_SERVER_IP + ":" + Constants.MASTER_SERVER_PORT);
        this.client.connect(500000, Constants.MASTER_SERVER_IP, Constants.MASTER_SERVER_PORT);
        isConnected = true;

        Log.info("Master server connection established!");
    }

    public void kill()
    {
        if (this.client != null)
        {
            this.client.stop();
        }
    }

    public void sendTCP(byte[] data)
    {
        BasicMessagePacket packet = new BasicMessagePacket();

        packet.data = data;
        sendTCP(packet);
    }

    public void sendUDP(byte[] data)
    {
        BasicMessagePacket packet = new BasicMessagePacket();

        packet.data = data;
        sendUDP(packet);
    }

    private static void registerClasses(Kryo kryo)
    {
        kryo.register(Packet.class);
        kryo.register(byte.class);
        kryo.register(byte[].class);
        kryo.register(BasicMessagePacket.class);
    }


    public void sendTCP(Packet packet)
    {
        this.client.sendTCP(packet);
    }

    public void sendUDP(Packet packet)
    {
        this.client.sendUDP(packet);
    }

    public void disconnected(Connection c)
    {
        Log.warn("Connection lost between master server. Attempting to reestablish connection...");
        Runnable run = new DefaultReconnector();
        if (reconnected != null) run = reconnected;
        new Thread(run).start();
    }

    public class DefaultReconnector
            implements Runnable
    {
        public DefaultReconnector()
        {
        }

        public void run()
        {
            while (!GameClient.this.getClient().isConnected())
            {
                try
                {
                    GameClient.this.client.reconnect();
                } catch (Exception ex)
                {
                    try
                    {
                        Thread.sleep(5000L);
                    } catch (InterruptedException ex1)
                    {
                        ex.printStackTrace();
                    }
                }
            }

            Log.info("Connection reestablished!");
        }
    }


}