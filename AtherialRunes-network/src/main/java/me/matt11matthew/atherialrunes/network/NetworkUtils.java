package me.matt11matthew.atherialrunes.network;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class NetworkUtils {

    public static HashMap<Integer, String> server_list = new HashMap<Integer, String>();
    public static HashMap<Integer, Socket> sock_list = new HashMap<Integer, Socket>();
    public static volatile CopyOnWriteArrayList<String> socket_list = new CopyOnWriteArrayList<String>();
    public static Thread message_listener;

    public static Socket getSocket(int server_num) {
        Socket return_socket = sock_list.get(server_num);
        int delay = 200;
        if (return_socket == null || return_socket.isClosed()) {
            try {
                String ipAndPort = server_list.get(server_num);
                String ipNoPort = ipAndPort.contains(":") ? server_list.get(server_num).split(":")[0] : ipAndPort;
                int port = ipAndPort.contains(":") && StringUtils.isNumeric(ipAndPort.split(":")[1]) ? Integer.parseInt(ipAndPort.split(":")[1]) : 3306;
                Socket s = new Socket();
                s.connect(new InetSocketAddress(ipNoPort, port), delay);
                return s;
            } catch (IOException e) {
            }
        }
        return return_socket;
    }

    public static void registerServer(String ip, String port, int id) {
        server_list.put(id, ip + ":" + port);
    }

    public static void load() {
        message_listener = new ListenThread();
        message_listener.start();
    }

    public static void sendPacketCrossServer(String packet_data, int server_num, boolean all_servers) {
        Socket kkSocket = null;
        PrintWriter out = null;

        if (all_servers) {
            for (int sn : server_list.keySet()) {
                String ipAndPort = server_list.get(sn);
                String ipNoPort = ipAndPort.contains(":") ? server_list.get(sn).split(":")[0]
                        : ipAndPort;
                int port = ipAndPort.contains(":") && StringUtils.isNumeric(ipAndPort.split(":")[1]) ? Integer.parseInt(ipAndPort.split(":")[1]) : 3306;
                if (sn == getServerNum()) {
                    continue;
                }
                try {
                    kkSocket = new Socket();
                    kkSocket.connect(new InetSocketAddress(ipNoPort, port), 100);
                    out = new PrintWriter(kkSocket.getOutputStream(), true);
                    out.println(packet_data);

                } catch (IOException e) {
                    if (out != null) {
                        out.close();
                    }
                    continue;
                }

                if (out != null) {
                    out.close();
                }
            }
        } else if (!all_servers) {
            try {
                String ipAndPort = server_list.get(server_num);
                String ipNoPort = ipAndPort.contains(":") ? server_list.get(server_num).split(":")[0] : ipAndPort;
                int port = ipAndPort.contains(":") && StringUtils.isNumeric(ipAndPort.split(":")[1]) ? Integer.parseInt(ipAndPort.split(":")[1]) : 3306;
                kkSocket = new Socket();
                kkSocket.connect(new InetSocketAddress(ipNoPort, port), 100);
                out = new PrintWriter(kkSocket.getOutputStream(), true);

                out.println(packet_data);

            } catch (IOException e) {

            } finally {
                if (out != null) {
                    out.close();
                }
            }

            if (out != null) {
                out.close();
            }
        }
    }

    public static String getShardFile() {
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

    public static int getServerNum() {
        int shardId = 0;
        String ss = getShardFile();
        String[] s = ss.split("\n");
        for (int i = 0; i < s.length; i++) {
            if (s[i].contains("id: ")) {
                shardId = Integer.parseInt(s[i].split("id: ")[1].trim());
            }
        }
        return shardId;
    }

    public static void sendPacketCrossServer(String packet_data, String ip) {
        Socket kkSocket = null;
        PrintWriter out = null;

        try {
            kkSocket = new Socket();
            String ipAndPort = ip;
            String ipNoPort = ipAndPort.contains(":") ? ip.split(":")[0] : ipAndPort;
            int port = ipAndPort.contains(":") && StringUtils.isNumeric(ipAndPort.split(":")[1]) ? Integer.parseInt(ipAndPort.split(":")[1]) : 3306;
            kkSocket.connect(new InetSocketAddress(ipNoPort, port), 100);
            out = new PrintWriter(kkSocket.getOutputStream(), true);

            out.println(packet_data);
            kkSocket.close();
        } catch (IOException e) {

        } finally {
            if (out != null) {
                out.close();
            }
        }

        if (out != null) {
            out.close();
        }
    }
}
