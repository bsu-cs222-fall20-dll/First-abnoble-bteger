package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Main extends Application{
    public static void main(String[] args) throws IOException {
        launch(args);
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
    public void start(Stage primaryStage) {
        Label searchLabel = new Label("Orwellian News Service Wiki Searcher");
        TextField searchBar = new TextField();
        Button searchButton = new Button("Search");
        TextArea resultArea = new TextArea();
        searchButton.setOnAction(event -> resultArea.setText(searchBar.getText()));


        VBox parent = new VBox(searchLabel, searchBar, searchButton, resultArea);
        parent.setAlignment(Pos.TOP_CENTER);

        primaryStage.setScene(new Scene(parent));
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);
        primaryStage.show();
    }
}
