package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        UserInterface userInterface = new UserInterface();
        String search = userInterface.requestWikiSearch();

        WikiConnection wikiConnection = new WikiConnection();
        URL getRequest = wikiConnection.generateHTTPRequest(search);
        InputStream inputStream = wikiConnection.pullRevisionData(getRequest);
        RevisionParser parser = new RevisionParser();
        ArrayList<Revision> revisions = parser.parse(inputStream);

        InputStream inputStreamReset = wikiConnection.pullRevisionData(getRequest);
        String redirected = parser.checkIsRedirected(inputStreamReset);

        if (!redirected.equals("")) {
            userInterface.showRedirection(search, redirected);
        }

        userInterface.showMostRecentRevisions(revisions);
    }
}
