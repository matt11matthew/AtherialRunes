package me.matt11matthew.atherialrunes.server;

import me.matt11matthew.atherialrunes.Constants;
import me.matt11matthew.atherialrunes.utils.Utils;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MOTDListener implements Listener {

    @EventHandler
    public void onPing(ProxyPingEvent e){
        ServerPing serverPing = e.getResponse();
        String motd = "";
        if (Constants.MAINTENANCE) {
            motd = Utils.asCentered(Utils.colorCodes(Constants.MAINTENANCE_MOTD));
        } else if (Constants.DEBUG) {
            motd = Utils.asCentered(Utils.colorCodes(Constants.DEV_MOTD));
        } else {
            motd = Utils.asCentered(Utils.colorCodes(Constants.MOTD));
        }
        serverPing.setDescription(motd);
        e.setResponse(serverPing);
    }
}
