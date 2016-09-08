package me.matt11matthew.atherialrunes.game.enums;

import me.matt11matthew.atherialrunes.game.utils.AtherialUtils;
import me.matt11matthew.atherialrunes.utils.Utils;
import org.bukkit.entity.Player;

public enum MessageType {

	ACTION(),
	TITLE(),
	SUB_TITLE(),
	TITLE_SUB_TITLE(),
	CHAT();
	
	MessageType() {}
	
	public void send(String msg, Player player) {
		msg = msg.replaceAll("%TAB%", "    ");
		switch (this) {
			case ACTION:
				AtherialUtils.sendActionBar(player, Utils.colorCodes(msg));
				//TTA_Methods.sendActionBar(player, Utils.colorCodes(msg));
				break;
			case CHAT:
				player.sendMessage(Utils.colorCodes(msg));
				break;
			case SUB_TITLE:
				//TTA_Methods.sendTitle(player, "", 20, 20, 20, Utils.colorCodes(msg), 20, 20, 20);
				break;
			case TITLE:
				//TTA_Methods.sendTitle(player, Utils.colorCodes(msg), 20, 20, 20, "", 20, 20, 20);
				break;
			case TITLE_SUB_TITLE:
				//TTA_Methods.sendTitle(player, Utils.colorCodes(msg.split("SUB:")[0].trim()), 20, 20, 20, Utils.colorCodes(msg.split("SUB:")[1].trim()), 20, 20, 20);
				break;
			default:
				break;
		}
	}
}
