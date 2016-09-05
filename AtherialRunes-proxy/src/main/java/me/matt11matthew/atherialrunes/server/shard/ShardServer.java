package me.matt11matthew.atherialrunes.server.shard;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.server.Main;

public class ShardServer {
	
	private Shard shard = null;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;
    private String serverIP = null;
    private boolean connected = false;
    private Socket connection = null;
    
	public ShardServer(Shard shard) {
		this.shard = shard;
		this.serverIP = Constants.MASTER_SERVER_IP;
		
	}
	
	public Shard getShard() {
		return shard;
	}
	
	public void disconnect() {
		sendPacket("DISCONNECT-" + shard.getId());
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connected = false;
	}
	
	public void connect() {
		Main.print("Trying to connect " + shard.getPrefix() + shard.getId() + " to masterserver...");
		try {
			try {
				connection = new Socket(Constants.MASTER_SERVER_IP, Constants.MASTER_SERVER_PORT);
				Main.print(connection.getRemoteSocketAddress() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Main.print("Connected " + shard.getIp() + ":" + shard.getPort() + " To " + Constants.MASTER_SERVER_IP + ":" + Constants.MASTER_SERVER_PORT);
			Main.print(shard.getPrefix() + shard.getId());
			//input = new ObjectInputStream(connection.getInputStream());
			sendPacket("CONNECTED-" + shard.getId());
			connected = true;
			loop();
		} catch (Exception e) {
			e.printStackTrace();
			disconnect();
			return;
		}
	}
	
	public void loop() {
		String packet = null;
		try {
			input = new ObjectInputStream(connection.getInputStream());
			if (input != null) {
				packet = input.readUTF();
			}
		} catch (EOFException e) {
			e.printStackTrace();
			disconnect();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			disconnect();
			return;
		}
		try {
			while ((packet != null) && (!packet.equals("END"))) {
				if (packet.startsWith("MSG-")) {
					String msg = packet.split("MSG-")[1].trim();
					Main.print(msg);
					return;
    			} else if (packet.startsWith("START-")) {
					Main.print("WIP");
					return;
    			} else {
    				return;
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
			disconnect();
			return;
		}
	}
	
	public void sendPacket(String packet) {
		try {
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			output.writeUTF(packet);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public ObjectInputStream getInput() {
		return input;
	}

	public void setInput(ObjectInputStream input) {
		this.input = input;
	}

	public ObjectOutputStream getOutput() {
		return output;
	}

	public void setOutput(ObjectOutputStream output) {
		this.output = output;
	}

	public boolean isConnected() {
		return connected;
	}

	public boolean isOnline() {
		try {
			connection.close();
			connection = new Socket(Constants.MASTER_SERVER_IP, Constants.MASTER_SERVER_PORT);
			if (!connection.isConnected()) return false;
			if (connection.isClosed()) return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
