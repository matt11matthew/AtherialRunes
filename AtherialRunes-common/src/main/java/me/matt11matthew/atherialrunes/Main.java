package me.matt11matthew.atherialrunes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class Main {

	/**
	 *
	 * @param args main method
     */
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();

		obj.put("uuid", "48593458439");
		obj.put("ign", "matt11matthew");

		print("\n");
		System.out.print(obj);
		print("\n");

		print("UUID: " + obj.get("uuid"));
		print("IGN: " + obj.get("ign"));

		print("\n");

		JSONParser parser = new JSONParser();
		JSONObject new_obj = new JSONObject();
		try {
			new_obj.putAll((Map) parser.parse(obj.toJSONString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		print("\n");
		System.out.print(new_obj);
		print("\n");
	}

	/**
	 *
	 * @param s prints
     */
	public static void print(String s) {
		System.out.println(s);
	}
}
