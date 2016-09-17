package me.matt11matthew.atherialrunes.game.network;

import me.matt11matthew.atherialrunes.game.Main;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.ChatChannel;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.rank.Rank;
import me.matt11matthew.atherialrunes.game.api.mechanic.gamemechanic.server.ServerMechanics;
import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectProtocol implements Runnable {

    private Socket clientSocket;
    private String sendingIP;

    public ConnectProtocol(Socket clientSocket, String sendingIP) {
        this.clientSocket = clientSocket;
        this.sendingIP = sendingIP;
    }

    public static void sendResultCrossServer(String server_ip, String message, int server_num) {
        Socket kkSocket = null;
        PrintWriter out = null;

        try {
            try {
                kkSocket = NetworkUtils.getSocket(server_num);
                out = new PrintWriter(kkSocket.getOutputStream(), true);
            } catch (Exception err) {
                String ipAndPort = server_ip;
                String ipNoPort = ipAndPort.contains(":") ? server_ip.split(":")[0] : ipAndPort;
                int port = (ipAndPort.contains(":") && StringUtils.isNumeric(ipAndPort.split(":")[1])) ? Integer.parseInt(ipAndPort.split(":")[1]) : 3306;
                kkSocket = new Socket();
                kkSocket.connect(new InetSocketAddress(ipNoPort, port), 150);
                out = new PrintWriter(kkSocket.getOutputStream(), true);
            }

            out.println(message);
            out.close();
        } catch (IOException e) {

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("[online]")) {
                    String id = inputLine.split("ID:")[1];
                    print(Utils.colorCodes("&c" + id + " is &lONLINE"));
                }
                if (inputLine.startsWith("[sayall]")) {
                    String text = inputLine.split("MSG:")[1];
                    Bukkit.getServer().broadcastMessage(Utils.colorCodes(text));
                }
                if (inputLine.startsWith("[sc]")) {
                    String from_tag = inputLine.split("fromplayerrandomcool:")[1].split(",")[0];
                    String from_shard = inputLine.split("shardplayerrandomcool:")[1];
                    Bukkit.getConsoleSender().sendMessage(Utils.colorCodes("&7<&6StaffChat&7> &8&l(" + from_shard + ") " + from_tag));
                    Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                        GamePlayer gp = Main.getGamePlayer(player);
                        if (Rank.isStaff(player.getName())) {
                            if (gp.getShard().equals(from_shard)) {
                                gp.msg(MessageType.CHAT, ChatChannel.STAFF.getPrefix() + " " + from_tag);
                            } else {
                                gp.msg(MessageType.CHAT, ChatChannel.STAFF.getPrefix() + " &8&l(" + from_shard + "&8) " + from_tag);
                            }
                        }
                    });
                }
                if (inputLine.startsWith("[reboot]")) {
                    String shard = inputLine.split("shard:")[1].split(",")[0].trim();
                    if (NetworkUtils.getServerNum() == Integer.parseInt(shard.split("-")[1].trim())) {
                        ServerMechanics.getInstance().setRebootTime(0, 0, 30);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void print(String msg) {
        System.out.println(msg);
    }
}
