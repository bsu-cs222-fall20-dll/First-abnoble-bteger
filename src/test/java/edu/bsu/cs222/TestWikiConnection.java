package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class TestWikiConnection {
    String SAMPLE_GET_REQUEST = "https://en.wikipedia.org/w/api.php?" +
            "action=query&format=json&" +
            "prop=revisions&titles=Zappa&" +
            "rvprop=timestamp|user&rvlimit=20&redirects";

    @Test
    public void sendGetRequestTest() throws IOException {
        WikiConnection connection = new WikiConnection();
        URL testUrl = new URL("https://en.wikipedia.org/w/api.php?" +
                "action=query&format=json&" +
                "prop=revisions&titles=Zappa&" +
                "rvprop=timestamp|user&rvlimit=4&redirects");
        InputStream inputStream = connection.pullRevisionData(testUrl);
        Assertions.assertNotNull(inputStream);
    }

    @Test
    public void generateHTTPRequestTest() throws MalformedURLException {
        WikiConnection wikiConnection = new WikiConnection();
        URL getRequest = wikiConnection.generateHTTPRequest("Zappa");
        Assertions.assertEquals(SAMPLE_GET_REQUEST, getRequest.toString());
    }
}
