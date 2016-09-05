package me.matt11matthew.atherialrunes.network;


public interface PingResponse {

    /**
     * @return If the server is online
     */
    boolean isOnline();

    /**
     * @return MOTD of pinged server
     */
    String getMotd();

    /**
     * @return Amount of online players displayed
     */
    int getOnlinePlayers();


    /**
     * @return Amount of max players displayed
     */
    int getMaxPlayers();

}
