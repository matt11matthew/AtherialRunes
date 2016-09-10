package me.matt11matthew.atherialrunes.network;


import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenThread extends Thread {

    InetAddress lAddress;

    public void run() {
        ServerSocket ss;
        int port = NetworkUtils.server_list.get(NetworkUtils.getServerNum()).contains(":") ? Integer.parseInt(NetworkUtils.server_list.get(NetworkUtils.getServerNum()).split(":")[1]) : 3306;
        try {

            lAddress = InetAddress.getByName(Bukkit.getIp());
            ss = new ServerSocket(port, 200, lAddress);
            while (true) {
                final Socket clientSocket = ss.accept();
                String ip = clientSocket.getInetAddress().getHostAddress();
                Thread process = new Thread(new ConnectProtocol(clientSocket, ip));
                process.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}