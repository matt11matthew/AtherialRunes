package me.matt11matthew.atherialrunes.discord;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class MessageHandler implements MessageCreateListener {

	@Override
	public void onMessageCreate(DiscordAPI api, Message msg) {
		if (StringUtils.isCurse(msg.getContent())) {
			msg.delete();
			msg.getAuthor().sendMessage("Message Blocked");
		
		
		}
		
	}

}
