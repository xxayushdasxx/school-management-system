package com.example.hellofx.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class FeesPage {
    private Stage primaryStage;
    private ArrayList<Payment> paymentHistory;
    private int currentInstallment; // Track the current installment index
    private int totalInstallments;  // Total number of installments
    private final double fullFeeAmount = 100000; // Fee for each semester
    private Scene mainScene;
    private BorderPane mainLayout;

    public FeesPage(Stage stage) {
        this.primaryStage = stage;
        this.paymentHistory = new ArrayList<>();
        this.currentInstallment = -1; // Start with no installments paid
        this.totalInstallments = 0; // Initialize total installments
        this.mainLayout = new BorderPane();
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene; // Assign the main scene here
    }

    public Scene displayFees() { // Accepting BorderPane as parameter
        VBox feesLayout = new VBox(10);
        feesLayout.setAlignment(Pos.CENTER);
        feesLayout.setStyle("-fx-padding: 20;");

        Text title = new Text("Fees Management System");
        title.setStyle("-fx-font-size: 24;");
        feesLayout.getChildren().add(title); // Add title to fees layout

        Button payFeesButton = new Button("Pay Fees");
        payFeesButton.setOnAction(e -> payFees());

        Button receiptButton = new Button("View Receipts");
        receiptButton.setOnAction(e -> showReceiptsPage());

        Button historyButton = new Button("Show Payment History");
        historyButton.setOnAction(e -> showPaymentHistoryPage());

        Button backButton = new Button("Back to Main Page");
        backButton.setOnAction(e -> {
            if (mainScene != null) {
                primaryStage.setScene(mainScene); // Return to the main scene
            } else {
                System.out.println("Error: mainScene not set.");
            }
        });

        feesLayout.getChildren().addAll(payFeesButton, receiptButton, historyButton, backButton);

        mainLayout.setCenter(feesLayout); // Set the fees layout to the center of the main layout
        return new Scene(mainLayout, 600, 400); // Return the updated scene
    }

    private void payFees() {
        ChoiceDialog<Integer> semesterDialog = new ChoiceDialog<>(1, 1, 2, 3);
        semesterDialog.setTitle("Choose Semester");
        semesterDialog.setHeaderText("Select Semester");
        semesterDialog.setContentText("Choose semester:");

        semesterDialog.showAndWait().ifPresent(semester -> {
            ChoiceDialog<String> paymentMethodDialog = new ChoiceDialog<>("Full", "Full", "EMI");
            paymentMethodDialog.setTitle("Choose Payment Method");
            paymentMethodDialog.setHeaderText("Select Payment Method");

            paymentMethodDialog.showAndWait().ifPresent(method -> {
                if (method.equals("Full")) {
                    processFullPayment(semester);
                } else {
                    ChoiceDialog<Integer> installmentDialog = new ChoiceDialog<>(3, 3, 6);
                    installmentDialog.setTitle("Choose Installments");
                    installmentDialog.setHeaderText("Select Number of Installments");

                    installmentDialog.showAndWait().ifPresent(installments -> {
                        this.totalInstallments = installments;
                        this.currentInstallment = 0;
                        processEMIPayment(semester);
                    });
                }
            });
        });
    }

    private void processFullPayment(int semester) {
        String receiptNumber = generateReceipt();
        paymentHistory.add(new Payment((int) fullFeeAmount, "Full", semester, receiptNumber, totalInstallments));
        showReceipt(receiptNumber, (int) fullFeeAmount, "Full", semester);
    }

    private void processEMIPayment(int semester) {
        if (currentInstallment < totalInstallments) {
            double interestRate = totalInstallments == 3 ? 0.05 : 0.10;
            double totalAmount = fullFeeAmount * (1 + interestRate);
            double installmentAmount = totalAmount / totalInstallments;
            String receiptNumber = generateReceipt();

            paymentHistory.add(new Payment((int) installmentAmount, "EMI", semester, receiptNumber, totalInstallments));
            showReceipt(receiptNumber, (int) installmentAmount, "EMI", semester);
            currentInstallment++;
        } else {
            showAlert("All installments paid", "You have already paid all installments.");
        }
    }

    private String generateReceipt() {
        Random random = new Random();
        return String.format("REC-%d", random.nextInt(10000));
    }

    private void showReceipt(String receiptNumber, int amount, String method, int semester) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Receipt");
        alert.setHeaderText("Payment Successful");
        alert.setContentText(String.format("Receipt Number: %s\nAmount Paid: %d\nPayment Method: %s\nSemester: %d\nDate: %s",
                receiptNumber, amount, method, semester, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        alert.showAndWait();
    }

    private void showReceiptsPage() {
        Scene currentScene = primaryStage.getScene(); // Capture the current scene
        ReceiptsPage receiptsPage = new ReceiptsPage(primaryStage, this, currentScene);
        receiptsPage.displayReceipts();
    }


    public void showPaymentHistoryPage() {
        PaymentHistoryPage paymentHistoryPage = new PaymentHistoryPage(primaryStage, this, mainLayout);
        paymentHistoryPage.displayPaymentHistory();}

    public ArrayList<Payment> getPaymentHistory() {
        return paymentHistory;
    }

    public ObservableList<Payment> getPaymentHistoryAsObservableList() {
        return FXCollections.observableArrayList(paymentHistory);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
