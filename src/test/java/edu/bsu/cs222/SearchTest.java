package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchTest {
    String SAMPLE_GET_REQUEST = "https://en.wikipedia.org/w/api.php?" +
            "action=query&format=json&" +
            "prop=revisions&titles=Zappa&" +
            "rvprop=timestamp|user&rvlimit=20&redirects";

    @Test
    public void generateHTTPRequestTest() throws MalformedURLException, UnsupportedEncodingException {
        Search search = new Search();
        URL getRequest = search.generateHTTPRequest("Zappa");
        Assertions.assertEquals(SAMPLE_GET_REQUEST, getRequest.toString());
    }
}
