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

    public JsonArray parse(InputStream json) {
        Reader reader = new InputStreamReader(json);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String,JsonElement> entry: pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }

        return array;
    }

    public ArrayList<String> convert(JsonArray jsonArray) {
        ArrayList<String> userData = new ArrayList<>();
        for (JsonElement user: jsonArray) {
            userData.add(user.getAsJsonObject().get("user").toString());
        }
        return userData;
    }
}
