package me.matt11matthew.atherialrunes.server;

import me.matt11matthew.atherialrunes.Constants;

public class Main {

    
	public static void main(String[] args) {
		print("Starting MasterServer...");
		print("Version " + Constants.SERVER_VERSION);
		print("Build #" + Constants.BUILD);
		print("MasterServer Info -> " + Constants.MASTER_SERVER_IP + ":" + Constants.MASTER_SERVER_PORT);

	}


	public static void print(String s) {
		System.out.println(s);
	}	
}