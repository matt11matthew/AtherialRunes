package me.matt11matthew.atherialrunes.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils {
	
    public static void executeCommand(String command) {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        builder.redirectErrorStream(true);
        Process p;
        try {
            p = builder.start();
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String colorCodes(String s) {
    	s = s.replaceAll("&", Unicodes.SECTION_SIGN.get());
    	return s;
    }
    
	public static String parseMilis(long l) {
		long day = TimeUnit.MILLISECONDS.toDays(l);
		long min = TimeUnit.MILLISECONDS.toMinutes(l);
		long hour = TimeUnit.MILLISECONDS.toHours(l) - TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());
		if (hour - TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis()) > 0) {
			hour = hour - TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());
		}
		if (day - TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis()) > 0) {
			day = day - TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis());
		}
		if (min - TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()) > 0) {
			min = min - TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
		}
		if (day == 1) {
			return day + " Day";
		} else if (day > 1) {
			return day + " Days";
		} else if (min == 1) {
			return min + " Minute";
		} else if (min > 1) {
			return min + " Minutes";
		} else if (hour == 1) {
			return hour + " Hour";
		} else if (hour > 1) {
			return hour + " Hours";
		}
		return null;
	}
	
	public static int convertStringToInt(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			
		}
		return i;
	}
	
	public static long convertStringToMillis(String s) {
		try {
			if (s.contains("day")) {
				return System.currentTimeMillis() + TimeUnit.DAYS.toMillis(convertStringToInt(s.split("day")[0]));
			}
			if (s.contains("minute")) {
				return System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(convertStringToInt(s.split("minute")[0]));
			}
			if (s.equalsIgnoreCase("perm")) {
				return -1;
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static boolean canParseTime(String rawtime) {
		return (convertStringToMillis(rawtime) == 0) ? false : true;
	}

	public static List<String> getPossibleCompletionsForGivenArgs(String[] args, String[] strings, boolean addPlayers) {
		String arg = args[args.length-1].toLowerCase();
		ArrayList<String> possableComplete = new ArrayList<>();
		
		for (String s : strings) {
			if (s.toLowerCase().startsWith(arg)) {
				possableComplete.add(s);
			}
		}
		if (addPlayers) {
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.getName().startsWith(arg)) {
					possableComplete.add(p.getName());
				} else {
					possableComplete.add(p.getName());
				}
			}
		}
		return possableComplete;
	}
	
	public static List<String> getPossibleCompletionsForGivenArgs(String[] args, List<String> strings, boolean addPlayers) {
		String arg = args[args.length-1].toLowerCase();
		ArrayList<String> possableComplete = new ArrayList<>();
		
		for (String s : strings) {
			if (s.toLowerCase().startsWith(arg)) {
				possableComplete.add(s);
			}
		}
		if (addPlayers) {
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.getName().startsWith(arg)) {
					possableComplete.add(p.getName());
				} else {
					possableComplete.add(p.getName());
				}
			}
		}
		return possableComplete;
	}
	
    public static String asCentered(String text) {
        StringBuilder builder = new StringBuilder(text);
        char space = ' ';
        int distance = (45 - text.length()) / 2;
        for (int i = 0; i < distance; ++i) {
            builder.insert(0, space);
            builder.append(space);
        }
        return builder.toString();
    }
	
	public static ArrayList<String> getOnlinePlayerNames(String[] args) {
		ArrayList<String> onlinePlayerNames = new ArrayList<>();
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			onlinePlayerNames.add(p.getName());
		}
		String arg = args[args.length-1].toLowerCase();
		ArrayList<String> possableComplete = new ArrayList<>();
		
		for (String s : onlinePlayerNames) {
			if (s.toLowerCase().startsWith(arg)) {
				possableComplete.add(s);
			}
		}
		return possableComplete;
	}
}