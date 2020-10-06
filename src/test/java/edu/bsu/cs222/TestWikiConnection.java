package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TestWikiConnection {
    final private WikiConnection CONNECTION = new WikiConnection();
    final private String SAMPLE_URL_STRING = "https://en.wikipedia.org/w/api.php?" +
            "action=query&format=json&" +
            "prop=revisions&titles=Zappa&" +
            "rvprop=timestamp|user&rvlimit=20&redirects";

    @Test
    public void pullRevisionDataTest() throws IOException {
        Assertions.assertNotNull(CONNECTION.pullRevisionData(new URL(SAMPLE_URL_STRING)));
    }

    @Test
    public void generateHTTPRequestTest() throws MalformedURLException {
        Assertions.assertEquals(SAMPLE_URL_STRING, CONNECTION.generateHTTPRequest("Zappa").toString());
    }
}
