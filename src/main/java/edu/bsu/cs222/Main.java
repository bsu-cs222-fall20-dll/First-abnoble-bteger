package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter A Search");

        String search = scanner.nextLine();
        Search searcher = new Search();
        URL getRequest = searcher.generateHTTPRequest(search);
        WikiConnection wikiConnection = new WikiConnection();
        InputStream inputStream = wikiConnection.sendGetRequest(getRequest);
        RevisionParser parser = new RevisionParser();
        ArrayList<Revision> revisions = parser.parse(inputStream);

        for (int i = 0; i <= 19; i++) {
            System.out.println(revisions.get(i).getUser());
            System.out.println(revisions.get(i).getTimeStamp());
        }
    }
}
