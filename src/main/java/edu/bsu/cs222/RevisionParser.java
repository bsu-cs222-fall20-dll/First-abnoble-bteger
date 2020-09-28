package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

public class RevisionParser {
    private JsonParser parser = new JsonParser();

    public Revision parse(InputStream jsonInputStream) {
        Reader reader = new InputStreamReader(jsonInputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray jsonArray = null;
        for (Map.Entry<String,JsonElement> entry: pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            jsonArray = entryObject.getAsJsonArray("revisions");
        }

        ArrayList<String> newArray = convert(jsonArray);
        Revision mostRecentEditor = new Revision(newArray.get(0), newArray.get(1));
        return mostRecentEditor;
    }

    public ArrayList<String> convert(JsonArray jsonArray) {
        ArrayList<String> userData = new ArrayList<>();
        for (JsonElement user: jsonArray) {
            userData.add(user.getAsJsonObject().get("user").toString());
            userData.add(user.getAsJsonObject().get("timestamp").toString());
            // .toString method returns strings in the format of "\"Bteger\""
        }
        return userData;
    }
}
