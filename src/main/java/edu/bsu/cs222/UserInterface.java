package edu.bsu.cs222;

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
            System.out.printf("\t%d.)\t%s\n", i+1, revisions.get(i).getUser());
            System.out.printf("\t    \t%s\n\n", revisions.get(i).getTimeStamp());
        }
    }

    public void showNoNetworkConnection() {
        System.out.println("A network connection could not be established with Wikipedia");
    }

    public void showRedirection(String original, String redirected) {
        System.out.printf("Search was redirected from %s to %s\n", original, redirected);
        System.out.println();
    }
}


