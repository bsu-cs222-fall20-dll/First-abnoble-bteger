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
    TextField searchBar;
    TextArea resultArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox parent = createUI();
        parent.setAlignment(Pos.TOP_CENTER);

        primaryStage.setScene(new Scene(parent));
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);
        primaryStage.show();
    }

    private VBox createUI() {
        Label searchLabel = new Label("Orwellian News Service Wiki Searcher");
        searchBar = new TextField();
        resultArea = new TextArea();
        Button searchButton = createSearchButton();
        return new VBox(searchLabel, searchBar, searchButton, resultArea);
    }

    private Button createSearchButton() {
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            try {
                printResults(searchWiki(searchBar.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return searchButton;
    }

    private void printResults(String formattedResults) {
        resultArea.setText(formattedResults);
    }

    private String searchWiki(String search) throws IOException {
        WikiConnection wikiConnection = new WikiConnection();
        URL getRequest = wikiConnection.generateHTTPRequest(search);
        InputStream inputStream = wikiConnection.pullRevisionData(getRequest);
        RevisionParser parser = new RevisionParser();
        ArrayList<Revision> revisions = parser.parse(inputStream);
        return formatRevisions(revisions);
    }

    private String formatRevisions(ArrayList<Revision> revisions) {
        StringBuilder result = new StringBuilder("Here are the most recent revisions to the page: \n");
        for (int i = 0; i < revisions.size(); i++) {
            result.append(String.format("\t%d.)\t%s\n", i + 1, revisions.get(i).getUser()));
            result.append(String.format("\t    \t%s\n\n", revisions.get(i).getTimeStamp()));
        }
        return result.toString();
    }
}

