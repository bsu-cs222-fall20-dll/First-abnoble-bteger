package edu.bsu.cs222;

import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

public class RevisionParser {
    private final JsonParser PARSER = new JsonParser();

    public ArrayList<Revision> parse(InputStream jsonInputStream) {
        Reader reader = new InputStreamReader(jsonInputStream);
        JsonElement rootElement = PARSER.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray jsonArray = null;

        for (Map.Entry<String,JsonElement> entry: pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            if (entryObject.has("\"-1\"")) {
                return null;
            }
            jsonArray = entryObject.getAsJsonArray("revisions");
        }

        if (jsonArray != null) {
            return convertToRevisionArray(jsonArray);
        }
        return null;
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

    public String checkIsRedirected(InputStream sampleInputStream) {
        Reader reader = new InputStreamReader(sampleInputStream);
        JsonElement rootElement = PARSER.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        if (rootObject.getAsJsonObject("query").has("redirects")) {
            JsonArray redirectionData = rootObject.getAsJsonObject("query").getAsJsonArray("redirects");
            JsonObject toAndFrom = redirectionData.get(0).getAsJsonObject();
            JsonPrimitive redirectedTo = toAndFrom.getAsJsonPrimitive("to");
            return redirectedTo.getAsString();
        }
        return "";
    }
}
