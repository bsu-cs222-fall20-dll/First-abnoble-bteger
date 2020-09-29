package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestWikiConnection {
    String SAMPLE_GET_REQUEST = "https://en.wikipedia.org/w/api.php?" +
            "action=query&format=json&" +
            "prop=revisions&titles=Zappa&" +
            "rvprop=timestamp|user&rvlimit=20&redirects";

    @Test
    public void testWikiConnection() throws IOException {
        URL myUrl = new URL(SAMPLE_GET_REQUEST);
        HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Revision Tracker/0.1 (bteger@bsu.edu)");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
    }

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
