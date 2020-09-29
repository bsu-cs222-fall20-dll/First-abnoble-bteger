package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WikiConnection {

    public InputStream sendGetRequest(URL testUrl) throws IOException {
        HttpURLConnection con = (HttpURLConnection) testUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Revision Tracker/0.1 (bteger@bsu.edu)");
        InputStream inputStream = con.getInputStream();
        return inputStream;
    }
}