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

    private String searchWiki(String search) throws IOException {
        WikiConnection wikiConnection = new WikiConnection();
        RevisionParser parser = new RevisionParser();
        String redirectedSearch;
        String redirectedMessage = "";

        if (search.isEmpty()) {
            return null;
        }

        URL getRequest = wikiConnection.generateHTTPRequest(search);
        InputStream initialInputStream = wikiConnection.pullRevisionData(getRequest);

        if (initialInputStream == null) {
            return generateNoConnectionMessage();
        } else {
            redirectedSearch = parser.checkIsRedirected(initialInputStream);

            InputStream refreshedInputStream = wikiConnection.pullRevisionData(getRequest);

            ArrayList<Revision> revisions = parser.parse(refreshedInputStream);

            if (revisions == null) {
                return generateNoPageFoundMessage();
            }

            if (!redirectedSearch.isEmpty()) {
                redirectedMessage = generateRedirectedMessage(search, redirectedSearch);
            }

            return redirectedMessage + formatRevisions(revisions);
        }
    }

    private void printResults(String formattedResults) {
        resultArea.setText(formattedResults);
    }

    private String formatRevisions(ArrayList<Revision> revisions) {
        StringBuilder result = new StringBuilder("Here are the most recent revisions to the page: \n");
        for (int i = 0; i < revisions.size(); i++) {
            result.append(String.format("\t%d.)\t%s\n", i + 1, revisions.get(i).getUser()));
            result.append(String.format("\t    \t%s\n\n", revisions.get(i).getTimeStamp()));
        }
        return result.toString();
    }

    private String generateNoPageFoundMessage() {
        return "No page found";
    }

    private String generateNoConnectionMessage() {
        return "Failed to connect to Wikipedia";
    }


    private String generateRedirectedMessage(String original, String redirection) {
        return String.format("Search was redirected from %s to %s\n", original, redirection);
    }
}

