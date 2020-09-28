package edu.bsu.cs222;

import com.google.gson.*;

import java.io.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class RevisionParserTest {
    @Test
    public void testParse(){
        RevisionParser parser = new RevisionParser();
        InputStream sampleInputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        JsonArray editorsAndTimeStampsArray = parser.parse(sampleInputStream);
        Assertions.assertNotNull(editorsAndTimeStampsArray);
        System.out.println(editorsAndTimeStampsArray);
    }


    @Test
    public void testConvertJsonArray() {
        RevisionParser parser = new RevisionParser();

        JsonObject sampleUser = new JsonObject();
        sampleUser.add("user", new JsonPrimitive("Josh"));
        JsonObject sampleUser2 = new JsonObject();
        sampleUser2.add("user", new JsonPrimitive("Ben"));
        JsonObject sampleUser3 = new JsonObject();
        sampleUser3.add("user", new JsonPrimitive("Sam"));

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(sampleUser);
        jsonArray.add(sampleUser2);
        jsonArray.add(sampleUser3);

        ArrayList<String> userData = parser.convert(jsonArray);
        System.out.println(userData.get(0));
        Assertions.assertEquals("\"Josh\"", userData.get(0));
        Assertions.assertEquals("\"Ben\"", userData.get(1));
        Assertions.assertEquals("\"Sam\"", userData.get(2));
    }


    @Test
    public void testJSON(){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String,JsonElement> entry: pages.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }

        String user1 = array.get(0).getAsJsonObject().get("user").toString();
        ArrayList<String> editorsAndTimeStampsArray = new ArrayList<>();
        editorsAndTimeStampsArray.add(user1);

        System.out.println(editorsAndTimeStampsArray);
    }

    @Test
    public void readJSONAsFileObj() throws FileNotFoundException {
        File sampleJson = new File("/Users/beneger/CodeProjects/" +
                "Java/FirstProject-Aric-Ben/src/test/resources/sample.json");
        File sampleJson2 = new File(getClass().getClassLoader().getResource("sample.json").getFile());
        Scanner myReader = new Scanner(sampleJson2);
        while(myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
    }
}
