package me.matt11matthew.atherialrunes.game.utils.message;

import me.matt11matthew.atherialrunes.game.api.player.GamePlayer;
import me.matt11matthew.atherialrunes.game.enums.MessageType;

public class ChatMessage extends Message {
    @Override
    public void send(GamePlayer gamePlayer) {
        gamePlayer.msg(MessageType.CHAT, getMessage());
    }
}