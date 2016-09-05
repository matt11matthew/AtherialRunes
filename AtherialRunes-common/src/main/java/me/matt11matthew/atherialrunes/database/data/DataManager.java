package me.matt11matthew.atherialrunes.database.data;

import java.util.HashMap;

public class DataManager {
	
	public static HashMap<String, Data> data_map = new HashMap<String, Data>();
	
	public static void registerData(Data data) {
		data_map.put(data.getClass().getSimpleName(), data);
	}
	
	public static void loadData() {

	}

}
