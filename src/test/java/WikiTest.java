
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

public class WikiTest {

    @Test
    public void testJSON(){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = (JsonElement) parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String,JsonElement> entry: pages.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        Assert.assertEquals(3, array.size());
    }
}
