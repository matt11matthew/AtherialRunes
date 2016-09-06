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
	
	public static String generateDots(String bannedword) {
		String return_string = "";
		for (int i = 0; i < bannedword.length(); i++) {
			return_string = return_string + "*";
		}
		return return_string.trim();
	}
	
	public static String replaceBannedWords(String s) {
		for (String ss : bannedwords) {
			if (s.toLowerCase().contains(ss.toLowerCase())) {
				s = s.toLowerCase();	
				s = s.replaceAll(ss, generateDots(ss));
				return s.trim();
			}
		}
		return s;
	}
	
	public static String filter(String s) {
		String return_string = "";
		if (s.contains(" ")) {
			for (String ss : s.split(" ")) {
				String s1 = replaceBannedWords(ss);
				return_string = return_string + " " + s1;
			}
		} else {
			String s1 = replaceBannedWords(s);
			return_string = return_string + " " + s1;
		}
		return return_string.trim();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<String> bannedwords = new ArrayList<String>(Arrays.asList("crap", "Alcoholic","Arse","Arselicker","Ass","Ass master","Ass-wipe","Asshole","Bastard","Bitch","Bum-fucker","Butt","Buttfucker","Clit","Cock master","Cock up","Cockfucker","Cunt","uck","Fuck face","Fuck head","Fuck noggin","Fucker","Homo","Homosexual","Horse fucker","Idiot","Jack-ass","Jerk","Mtherfucker","Oil dick","Pencil dick", "Pervert","Pornofreak","Queer","Shit eater","Shithead","Son of a bitch","Stripper","Wanker","faggot","fagfuck","nigger"));
}
