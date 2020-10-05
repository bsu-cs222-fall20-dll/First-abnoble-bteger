package edu.bsu.cs222;

import java.util.ArrayList;

public class UserInterface {


   public String requestWikiSearch() {
       return null;
   }

    public void showMostRecentRevisions(ArrayList<Revision> revisions) {
        System.out.println("Here are the most recent changes to the page:");
        for (int i = 0; i < revisions.size(); i++) {
            System.out.printf("\t%d.)\t%s\n", i+1, revisions.get(i).getUser());
            System.out.printf("\t    \t%s\n\n", revisions.get(i).getTimeStamp());
        }
    }

    public void showRedirection(String original, String redirected) {
        System.out.printf("Search was redirected from %s to %s\n", original, redirected);
        System.out.println();
    }
}


