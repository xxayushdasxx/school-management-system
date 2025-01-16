package com.example.hellofx.ui;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiptDetailPage {
    private Stage primaryStage;
    private Payment payment;
    private Scene previousScene;  // Reference to the previous scene

    public ReceiptDetailPage(Stage stage, Payment payment, Scene previousScene) {
        this.primaryStage = stage;
        this.payment = payment;
        this.previousScene = previousScene;  // Initialize previousScene
    }

    public void displayDetail() {
        VBox detailLayout = new VBox(10);
        detailLayout.setStyle("-fx-padding: 20;");

        // Display payment details
        Label receiptNumberLabel = new Label("Receipt Number: " + payment.getReceiptNumber());
        Label amountLabel = new Label("Amount: " + payment.getAmount());
        Label methodLabel = new Label("Payment Method: " + payment.getMethod());
        Label semesterLabel = new Label("Semester: " + payment.getSemester());
        Label dateLabel = new Label("Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payment.getDate())); // Use the date from Payment

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(previousScene));  // Set the scene back to previousScene

        detailLayout.getChildren().addAll(receiptNumberLabel, amountLabel, methodLabel, semesterLabel, dateLabel, backButton);
        Scene detailScene = new Scene(detailLayout, 400, 300);
        primaryStage.setScene(detailScene);
    }
}
