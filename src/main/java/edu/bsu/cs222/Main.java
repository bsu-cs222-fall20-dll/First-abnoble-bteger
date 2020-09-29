package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        UserInterface userInterface = new UserInterface();
        String search = userInterface.requestWikiSearch();

        Search searcher = new Search();
        URL getRequest = searcher.generateHTTPRequest(search);
        WikiConnection wikiConnection = new WikiConnection();
        InputStream inputStream = wikiConnection.sendGetRequest(getRequest);
        RevisionParser parser = new RevisionParser();
        ArrayList<Revision> revisions = parser.parse(inputStream);

        InputStream inputStreamReset = wikiConnection.sendGetRequest(getRequest);
        String redirected = parser.checkIsRedirected(inputStreamReset);

        if (redirected != "") {
            userInterface.showRedirection(search, redirected);
        }

        userInterface.showMostRecentRevisions(revisions);
    }
}
