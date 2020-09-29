package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public String requestWikiSearch() {
        System.out.println("Enter the name of a Wiki page");
        return scanner.nextLine();
    }

    public void showMostRecentRevisions(ArrayList<Revision> revisions) {
        System.out.println("Here are the most recent changes to the page:");
        for (int i = 0; i < revisions.size(); i++) {
            System.out.println(String.format("\t%d.)\t%s", i+1, revisions.get(i).getUser()));
            System.out.println(String.format("\t    \t%s\n", revisions.get(i).getTimeStamp()));
        }
    }

    public void showNoNetworkConnection() {
        System.out.println("A network connection could not be established with Wikipedia");
    }

    public void showRedirection(String original, String redirected) {
        System.out.printf("Search was redirected from %s to %s\n", original, redirected);
    }
}


