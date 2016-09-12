package me.matt11matthew.atherialrunes.game.utils.message;

import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.utils.Utils;

public abstract class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }

    public Message() {
        this(null);
    }

    public Message append(String text) {
        if (message == null) {
            message = text;
        }
        message += text + ",";
        return this;
    }

    public void send(GamePlayer gamePlayer){}

    public String getMessage() {
        String msg = message;
        msg = Utils.colorCodes(msg);
        msg = msg.replaceAll(",", System.lineSeparator());
        msg = msg.replaceAll("%comma%", ",");
        return msg;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRawMessage() {
        return Utils.colorCodes(message);
    }
}
