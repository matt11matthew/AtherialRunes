package me.matt11matthew.atherialrunes.game.network;

import org.apache.commons.lang.StringUtils;

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
                    String id = inputLine.split("[online]")[1];
                    print(id + " is ONLINE");
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
