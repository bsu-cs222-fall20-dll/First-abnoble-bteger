package edu.bsu.cs222;

import com.google.gson.*;

import java.io.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RevisionParserTest {

    @Test
    public void testParse(){
        RevisionParser parser = new RevisionParser();
        InputStream sampleInputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        ArrayList<Revision> revisions = parser.parse(sampleInputStream);
        Assertions.assertEquals(3, revisions.size());
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

        ArrayList<String> userData = parser.convert(jsonArray);
        Assertions.assertEquals("\"Josh\"", userData.get(0));
        Assertions.assertEquals("\"123\"", userData.get(1));
        Assertions.assertEquals("\"Ben\"", userData.get(2));
        Assertions.assertEquals("\"456\"", userData.get(3));
        Assertions.assertEquals("\"Sam\"", userData.get(4));
        Assertions.assertEquals("\"789\"", userData.get(5));
    }
}
