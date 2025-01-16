package com.example.hellofx.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceiptsPage {
    private Stage primaryStage;
    private FeesPage feesPage;
    private Scene previousScene;  // Reference to the previous scene

    public ReceiptsPage(Stage stage, FeesPage feesPage, Scene previousScene) {
        this.primaryStage = stage;
        this.feesPage = feesPage;
        this.previousScene = previousScene;  // Initialize previousScene
    }

    public void displayReceipts() {
        VBox receiptsLayout = new VBox(15);
        receiptsLayout.setPadding(new Insets(20));
        receiptsLayout.setAlignment(Pos.TOP_CENTER);

        // Header Label to keep always visible
        Label headerLabel = new Label("Receipts");
        headerLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-padding: 10;");

        // Container for receipt items
        VBox receiptItems = new VBox(10);
        receiptItems.setPadding(new Insets(10, 0, 0, 0));

        // Display clickable receipt numbers
        for (Payment payment : feesPage.getPaymentHistory()) {
            Label receiptLabel = new Label(payment.getReceiptNumber());
            receiptLabel.setStyle("-fx-text-fill: black; -fx-font-size: 16;");

            // Add hover effect for blue underline on mouse enter
            receiptLabel.setOnMouseEntered(e -> {
                receiptLabel.setStyle("-fx-text-fill: blue; -fx-underline: true; -fx-font-size: 16;");
            });
            // Reset style when mouse exits
            receiptLabel.setOnMouseExited(e -> {
                receiptLabel.setStyle("-fx-text-fill: black; -fx-font-size: 16;");
            });

            // Show payment details on click
            receiptLabel.setOnMouseClicked(e -> displayPaymentDetails(payment));

            receiptItems.getChildren().add(receiptLabel);
        }

        // Add back button to return to previous scene
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(previousScene)); // Go back to previous scene
        receiptItems.getChildren().add(backButton);

        // Add header and receipt items to the main layout
        receiptsLayout.getChildren().addAll(headerLabel, receiptItems);

        Scene receiptsScene = new Scene(receiptsLayout, 600, 400);
        primaryStage.setScene(receiptsScene);
    }

    // Method to display payment details in an alert
    private void displayPaymentDetails(Payment payment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Details");
        alert.setHeaderText("Details for Receipt: " + payment.getReceiptNumber());
        alert.setContentText("Amount: " + payment.getAmount() +
                "\nMethod: " + payment.getMethod() +
                "\nSemester: " + payment.getSemester() +
                "\nDate: " + payment.getDate() +
                "\nTransaction ID: " + payment.getTransactionId());
        alert.showAndWait();
    }
}
