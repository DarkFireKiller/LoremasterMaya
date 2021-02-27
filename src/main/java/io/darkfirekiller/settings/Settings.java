package io.darkfirekiller.settings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import io.darkfirekiller.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Settings {

    private static final String defaultFolder = "C://Path/To/Config/";
    public static String destFolder;

    public static String token = "";
    public static String prefix = "";

    public static ArrayList<String> aList = new ArrayList<>();

    public static void load(String dir) {
        destFolder = (Main.TESTING || dir == null) ? defaultFolder : dir;

        final JSONParser jsonParser = new JSONParser();
        Object object;

        try {

            object = jsonParser.parse(new String(getConfig()));
            JSONObject mainBlock = (JSONObject) object;

            token = (String) mainBlock.get("token");
            prefix = (String) mainBlock.get("prefix");

            if (token.equals("") || prefix.equals("")) {
                System.out.println("Prefix or token missing.");
                System.exit(0);
            }

            JSONArray admins = (JSONArray) mainBlock.get("admins");
            if (admins == null || admins.size() == 0) {
                System.out.println("Warning, no admins");
            } else {
                for (Object a : admins) aList.add((String) a);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getConfig() {
        try {
            return Files.readAllBytes(Paths.get(Settings.destFolder + ((Main.TESTING) ? ("configTEST.json") : ("config.json"))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
