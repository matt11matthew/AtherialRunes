package me.matt11matthew.atherialrunes.discord;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;

public class Main {
	
	public static void main(String args[]) {
        DiscordAPI api = Javacord.getApi(args[0], args[1]);
        api.connect(new FutureCallback<DiscordAPI>() {
	    public void onSuccess(DiscordAPI api) {
            api.registerListener(new MessageHandler());
				
			}

			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}
}