package me.matt11matthew.atherialrunes.network.ping;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import me.matt11matthew.atherialrunes.network.ServerAddress;

public class ServerPinger {

    public static String fetchData(final ServerAddress serverAddress, int timeout) throws IOException {

        Socket socket = null;
        DataOutputStream dataOut = null;
        DataInputStream dataIn = null;
        final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        final DataOutputStream handshake = new DataOutputStream(byteOut);

        try {
            socket = new Socket(serverAddress.getAddress(), serverAddress.getPort());
            socket.setSoTimeout(timeout);
            dataOut = new DataOutputStream(socket.getOutputStream());
            dataIn = new DataInputStream(socket.getInputStream());
            handshake.write(0);
            PacketUtils.writeVarInt(handshake, 4);
            PacketUtils.writeString(handshake, serverAddress.getAddress(), PacketUtils.UTF8);
            handshake.writeShort(serverAddress.getPort());
            PacketUtils.writeVarInt(handshake, 1);
            byte[] bytes = byteOut.toByteArray();
            PacketUtils.writeVarInt(dataOut, bytes.length);
            dataOut.write(bytes);
            bytes = new byte[]{0};
            PacketUtils.writeVarInt(dataOut, bytes.length);
            dataOut.write(bytes);
            PacketUtils.readVarInt(dataIn);
            PacketUtils.readVarInt(dataIn);
            final byte[] responseData = new byte[PacketUtils.readVarInt(dataIn)];
            dataIn.readFully(responseData);
            final String jsonString = new String(responseData, PacketUtils.UTF8);
            return jsonString;
        } finally {
            PacketUtils.closeQuietly(dataOut);
            PacketUtils.closeQuietly(dataIn);
            PacketUtils.closeQuietly(socket);
            PacketUtils.closeQuietly(byteOut);
            PacketUtils.closeQuietly(handshake);
        }
    }

}
