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

        ArrayList<Revision> revisions = convertToRevisionArray(jsonArray);
        return revisions;
    }

    public ArrayList<Revision> convertToRevisionArray(JsonArray jsonArray) {
        ArrayList<Revision> revisions = new ArrayList<>();

        for (JsonElement revision: jsonArray) {
            String username = revision.getAsJsonObject().get("user").toString();
            String timestamp = revision.getAsJsonObject().get("timestamp").toString();
            revisions.add(new Revision(username, timestamp));
        }
        return revisions;
    }
}
