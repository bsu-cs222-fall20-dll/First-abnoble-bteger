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

    @Test
    public void testWikiConnection() throws IOException {
        URL myUrl = new URL("https://en.wikipedia.org/w/api.php?" +
                "action=query&format=json&" +
                "prop=revisions&titles=Zappa&" +
                "rvprop=timestamp|user&rvlimit=4&redirects");
        HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Revision Tracker/0.1 (bteger@bsu.edu)");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
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
        InputStream inputStream = connection.sendGetRequest(testUrl);
        Assertions.assertNotNull(inputStream);
    }
}
