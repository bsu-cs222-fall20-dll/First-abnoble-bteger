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
        String URL_STRING = "https://en.wikipedia.org/w/api.php?" +
                "action=query&format=json&prop=revisions&titles=" +
                "Zappa&rvprop%20=timestamp|user&rvlimit=4&redirects";
        URL getRequest = new URL(URL_STRING);
        WikiConnection wikiConnection = new WikiConnection();
        InputStream inputStream = wikiConnection.sendGetRequest(getRequest);
        RevisionParser parser = new RevisionParser();
        ArrayList<Revision> revisions = parser.parse(inputStream);

        for (int i = 0; i <= 3; i++) {
            System.out.println(revisions.get(i).getUser());
            System.out.println(revisions.get(i).getTimeStamp());
        }
        /*System.out.println(revisions.get(0).getUser());
        System.out.println(revisions.get(0).getTimeStamp());
        System.out.println(revisions.get(1).getUser());
        System.out.println(revisions.get(1).getTimeStamp());
        System.out.println(revisions.get(2).getUser());
        System.out.println(revisions.get(2).getTimeStamp());
        System.out.println(revisions.get(3).getUser());
        System.out.println(revisions.get(3).getTimeStamp());*/
    }
}
