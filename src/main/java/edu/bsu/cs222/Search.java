package edu.bsu.cs222;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Search {

    public URL generateHTTPRequest(String search) throws MalformedURLException, UnsupportedEncodingException {
        String encodedSearch = URLEncoder.encode(search, "UTF-8");
        System.out.println(encodedSearch);
        URL url = new URL("https://en.wikipedia.org/w/api.php?" +
                "action=query&format=json&" +
                "prop=revisions&" + "titles=" + encodedSearch + "&" +
                "rvprop=timestamp|user&rvlimit=20&redirects");
        return url;
    }
}
