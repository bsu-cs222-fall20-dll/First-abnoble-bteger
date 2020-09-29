package edu.bsu.cs222;

import com.google.gson.*;

import java.io.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RevisionParserTest {
    private final RevisionParser PARSER = new RevisionParser();

    @Test
    public void testParse(){
        InputStream sampleInputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        ArrayList<Revision> revisions = PARSER.parse(sampleInputStream);
        Assertions.assertEquals(4, revisions.size());
    }

    @Test
    public void testConvertJsonArray() {
        RevisionParser parser = new RevisionParser();

        JsonObject sampleUser = new JsonObject();
        sampleUser.add("user", new JsonPrimitive("Josh"));
        sampleUser.add("timestamp", new JsonPrimitive("123"));
        JsonObject sampleUser2 = new JsonObject();
        sampleUser2.add("user", new JsonPrimitive("Ben"));
        sampleUser2.add("timestamp", new JsonPrimitive("456"));
        JsonObject sampleUser3 = new JsonObject();
        sampleUser3.add("user", new JsonPrimitive("Sam"));
        sampleUser3.add("timestamp", new JsonPrimitive("789"));

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(sampleUser);
        jsonArray.add(sampleUser2);
        jsonArray.add(sampleUser3);

        ArrayList<Revision> revisions = parser.convertToRevisionArray(jsonArray);
        Assertions.assertEquals(3, revisions.size());
        Assertions.assertEquals("\"Josh\"", revisions.get(0).getUser());
        Assertions.assertEquals("\"123\"", revisions.get(0).getTimeStamp());
        Assertions.assertEquals("\"Ben\"", revisions.get(1).getUser());
        Assertions.assertEquals("\"456\"", revisions.get(1).getTimeStamp());
        Assertions.assertEquals("\"Sam\"", revisions.get(2).getUser());
        Assertions.assertEquals("\"789\"", revisions.get(2).getTimeStamp());
    }

    @Test
    public void testCheckIsRedirected() {
        InputStream sampleInputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        String redirectedSearch = PARSER.checkIsRedirected(sampleInputStream);
        Assertions.assertEquals("Frank Zappa", redirectedSearch);
    }
}
