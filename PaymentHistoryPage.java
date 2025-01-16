package com.example.hellofx.ui;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.text.SimpleDateFormat;

public class PaymentHistoryPage {
    private Stage primaryStage;
    private FeesPage feesPage;
    private BorderPane mainLayout;

    public PaymentHistoryPage(Stage stage, FeesPage feesPage, BorderPane mainLayout) {
        this.primaryStage = stage;
        this.feesPage = feesPage;
        this.mainLayout = mainLayout;
    }

    public void displayPaymentHistory() {
        VBox historyLayout = new VBox(10);
        historyLayout.setStyle("-fx-padding: 20;");

        StringBuilder historyList = new StringBuilder("Payment History:\n\n");

        for (Payment payment : feesPage.getPaymentHistory()) {
            String paymentDetails = String.format("""
                Receipt Number: %s
                Transaction ID: %d
                Amount: ₹%d
                Payment Method: %s
                Semester: %d
                Date: %s
                """,
                    payment.getReceiptNumber(),
                    payment.getTransactionId(),
                    payment.getAmount(),
                    payment.getMethod(),
                    payment.getSemester(),
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payment.getDate())); // Include date);


            historyList.append(paymentDetails);
        }

        TextArea historyTextArea = new TextArea(historyList.toString());
        historyTextArea.setEditable(false);
        historyTextArea.setWrapText(true);
        historyTextArea.setPrefRowCount(15);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            VBox feesContent = new VBox();
            Scene feesScene = feesPage.displayFees();
            mainLayout.setCenter(feesScene.getRoot());
        });

        historyLayout.getChildren().addAll(historyTextArea, backButton);
        mainLayout.setCenter(historyLayout);
    }
}