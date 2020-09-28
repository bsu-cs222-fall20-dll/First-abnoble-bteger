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

    public ArrayList<Revision> parse(InputStream jsonInputStream) {
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

        // 0      1    2      3   ...
        // user1, ts1, user2, ts2 --> revision(user1, ts1), revision(user2, ts2)
        ArrayList<Revision> revisions = new ArrayList<>();
        for (int i = 0; i < newArray.size(); i++) {
            Revision revision = new Revision(newArray.get(i), newArray.get(++i));
            revisions.add(revision);
        }
        return revisions;
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
