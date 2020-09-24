package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class ParserTest {

    @Test
    public void testJSON(){

        /*
        We finally got the parser to work and print specific parts of the JSON array
        We did this by iterating through the JSON elements of the array and getting
        them as JSON objects. This allowed us to print information with the .get()
        method.

        We commented out the code from the video, and now we are writing a test for
        the Parser class. This code should accept Json data as some sort of JSON
        Object and convert it to a readable array.
         */
        Parser parser = new Parser();
        JsonArray array = null;

        int expectedNumOfElts = 3;
        Assert.assertEquals(expectedNumOfElts, array);


//        JsonParser parser = new JsonParser();
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
//        Reader reader = new InputStreamReader(inputStream);
//        JsonElement rootElement = (JsonElement) parser.parse(reader);
//        JsonObject rootObject = rootElement.getAsJsonObject();
//        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
//        JsonArray array = null;
//        for (Map.Entry<String,JsonElement> entry: pages.entrySet()){
//            JsonObject entryObject = entry.getValue().getAsJsonObject();
//            array = entryObject.getAsJsonArray("revisions");
//        }
//
//        for (JsonElement user : array) {
//            System.out.println(user.getAsJsonObject().get("user"));
//            System.out.println(user.getAsJsonObject().get("timestamp"));
//        }
    }
}
