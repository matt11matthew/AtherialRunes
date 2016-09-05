package me.matt11matthew.atherialrunes.discord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

	public static boolean isCurse(String s) {
		if (s.contains(" ")) {
			for (String ss : s.split(" ")) {
				for (String sss : bannedwords) {
					if (ss.toLowerCase().contains(sss.toLowerCase())) {
						return true;
					}
				}
			}
		} else {
			for (String sss : bannedwords) {
				if (s.toLowerCase().contains(sss.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<String> bannedwords = new ArrayList<String>(Arrays.asList("Alcoholic","Arse","Arselicker","Ass","Ass master","Ass-wipe","Asshole","Bastard","Bitch","Bum-fucker","Butt","Buttfucker","Clit","Cock master","Cock up","Cockfucker","Cunt","uck","Fuck face","Fuck head","Fuck noggin","Fucker","Homo","Homosexual","Horse fucker","Idiot","Jack-ass","Jerk","Mtherfucker","Oil dick","Pencil dick", "Pervert","Pornofreak","Queer","Shit eater","Shithead","Son of a bitch","Stripper","Wanker","faggot","fagfuck","nigger"));
}
