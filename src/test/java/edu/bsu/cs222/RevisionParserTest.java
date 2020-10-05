package edu.bsu.cs222;

import com.google.gson.*;

import java.io.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RevisionParserTest {
    private final RevisionParser PARSER = new RevisionParser();
    private final InputStream SAMPLE_INPUT_STREAM = getClass().getClassLoader().getResourceAsStream("sample.json");
    private final JsonArray JSON_ARRAY = makeSampleJsonArray();

    @Test
    public void testParseSize(){
        ArrayList<Revision> revisions = PARSER.parse(SAMPLE_INPUT_STREAM);
        Assertions.assertEquals(4, revisions.size());
    }

    @Test
    public void convertToRevisionArrayTestSize() {
        Assertions.assertEquals(3, PARSER.convertToRevisionArray(JSON_ARRAY).size());
    }

    @Test
    public void convertToRevisionArrayTestIndex0() {
        Assertions.assertEquals("\"123\"", PARSER.convertToRevisionArray(JSON_ARRAY).get(0).getTimeStamp());
    }

    @Test
    public void convertToRevisionArrayTestIndex1() {
        Assertions.assertEquals("\"Ben\"", PARSER.convertToRevisionArray(JSON_ARRAY).get(1).getUser());
    }


    @Test
    public void convertToRevisionArrayTestIndex2() {
        Assertions.assertEquals("\"789\"", PARSER.convertToRevisionArray(JSON_ARRAY).get(2).getTimeStamp());
    }

    @Test
    public void testCheckIsRedirected() {
        Assertions.assertEquals("Frank Zappa", PARSER.checkIsRedirected(SAMPLE_INPUT_STREAM));
    }

    private JsonArray makeSampleJsonArray() {
        JsonArray jsonArray = new JsonArray();

        JsonObject sampleUser = new JsonObject();
        sampleUser.add("user", new JsonPrimitive("Josh"));
        sampleUser.add("timestamp", new JsonPrimitive("123"));
        JsonObject sampleUser2 = new JsonObject();
        sampleUser2.add("user", new JsonPrimitive("Ben"));
        sampleUser2.add("timestamp", new JsonPrimitive("456"));
        JsonObject sampleUser3 = new JsonObject();
        sampleUser3.add("user", new JsonPrimitive("Sam"));
        sampleUser3.add("timestamp", new JsonPrimitive("789"));

        jsonArray.add(sampleUser);
        jsonArray.add(sampleUser2);
        jsonArray.add(sampleUser3);

        return jsonArray;
    }

}
