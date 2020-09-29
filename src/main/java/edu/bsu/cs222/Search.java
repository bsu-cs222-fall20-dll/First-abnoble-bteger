package edu.bsu.cs222;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Search {

    public URL generateHTTPRequest(String search) throws MalformedURLException {
        String encodedSearch = URLEncoder.encode(search, StandardCharsets.UTF_8);
        return new URL("https://en.wikipedia.org/w/api.php?" +
                "action=query&format=json&" +
                "prop=revisions&" + "titles=" + encodedSearch + "&" +
                "rvprop=timestamp|user&rvlimit=20&redirects");
    }
}
