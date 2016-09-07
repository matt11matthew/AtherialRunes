package me.matt11matthew.atherialrunes.game.utils.json;

import me.matt11matthew.atherialrunes.exceptions.NotJsonFileException;
import me.matt11matthew.atherialrunes.utils.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.util.Map;

public class JSONUtils {

    public static JSONObject convertStringToJSONObject(String s) {
        JSONObject new_obj = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            new_obj.putAll((Map) parser.parse(s));
        } catch (ParseException e) {

        }
        return new_obj;
    }

    public static JSONObject convertStringToJSONObject(File file) throws NotJsonFileException {
        if (!file.getName().endsWith(".json")) {
            throw new NotJsonFileException(file.getName());
        } else {
            String text = FileUtils.getTextOfFile(file.getPath(), file.getName());
            return convertStringToJSONObject(text);
        }
    }
}