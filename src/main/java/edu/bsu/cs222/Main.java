package edu.bsu.cs222;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Main extends Application {
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Label("Hello"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
