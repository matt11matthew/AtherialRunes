package me.matt11matthew.atherialrunes.game.utils.json;

import me.matt11matthew.atherialrunes.exceptions.NotJsonFileException;
import me.matt11matthew.atherialrunes.game.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONUtils {

    public static JSONObject convertStringToJSONObject(File file) throws NotJsonFileException {
        if (!file.getName().endsWith(".json")) {
            throw new NotJsonFileException(file.getName());
        } else {
            JSONObject new_obj = null;
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader(Main.getInstance().getDataFolder() + "/custom_items/" + file.getName()));
                new_obj = (JSONObject) obj;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new_obj;
        }
    }
}