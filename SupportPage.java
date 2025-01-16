package com.example.hellofx.ui;

import com.example.hellofx.utils.SchoolManagementSystem;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupportPage<T> {
    private Stage stage;
    private ExecutorService executorService;
    private SchoolManagementSystem mainApp; // Reference to the main application

    public SupportPage(Stage stage, SchoolManagementSystem mainApp) {
        this.stage = stage;
        this.mainApp = mainApp; // Store reference to the main application
        this.executorService = Executors.newFixedThreadPool(2); // Create a thread pool
    }

    public void display() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Text title = new Text("Support Page");
        title.setStyle("-fx-font-size: 24;");

        TextArea queryArea = new TextArea();
        queryArea.setPromptText("Enter your support query here...");
        queryArea.setWrapText(true);

        Button submitButton = new Button("Submit");
        Text responseText = new Text();

        // Action for the submit button
        submitButton.setOnAction(e -> {
            String query = queryArea.getText();
            if (!query.isEmpty()) {
                // Use a separate thread to process the query
                executorService.submit(() -> handleQuery(query, responseText));
            } else {
                Platform.runLater(() -> responseText.setText("Please enter a query."));
            }
        });

        // Back button to return to the main page
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            mainApp.showMainPage(); // Call the method to show the main page
        });

        layout.getChildren().addAll(title, queryArea, submitButton, responseText, backButton);
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }

    private void handleQuery(String query, Text responseText) {
        // Simulate processing time
        try {
            Thread.sleep(2000); // Simulate a delay for processing
            Platform.runLater(() -> {
                mainApp.supportQueries.add(query); // Add the query to the main application list
                responseText.setText("Response: Thank you for your query. We will get back to you shortly.");
            });
        } catch (InterruptedException e) {
            Platform.runLater(() -> responseText.setText("Error processing your query."));
        }
    }

    private String generateResponse(String query) {
        // Here you can implement logic to generate a response based on the query
        // For simplicity, we will return a mock response
        return "Thank you for your query: \"" + query + "\". We will get back to you shortly.";
    }

    public void shutdown() {
        executorService.shutdown(); // Shutdown the executor service when done
    }
}