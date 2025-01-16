package com.example.hellofx.ui;

import com.example.hellofx.model.Attendance;
import com.example.hellofx.utils.SchoolManagementSystem; // Import your main class
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateAttendance {

    private Stage stage;
    private SchoolManagementSystem mainApp; // Reference to main application
    private Attendance attendance; // Reference to Attendance model

    public UpdateAttendance(Stage stage, SchoolManagementSystem mainApp, Attendance attendance) {
        this.stage = stage;
        this.mainApp = mainApp; // Store the reference
        this.attendance = attendance; // Store the reference to attendance
    }

    public void display() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20;");

        Text title = new Text("Update Attendance");
        title.setStyle("-fx-font-size: 24;");

        // Subject selection
        ComboBox<String> subjectComboBox = new ComboBox<>();
        subjectComboBox.getItems().addAll("Math", "Physics", "Chemistry");
        subjectComboBox.setPromptText("Select Subject");

        // Attendance input
        TextField attendanceField = new TextField();
        attendanceField.setPromptText("Enter Attendance (0-100)");

        Button updateButton = new Button("Update Attendance");
        updateButton.setOnAction(e -> {
            String selectedSubject = subjectComboBox.getValue();
            String attendanceInput = attendanceField.getText();

            if (selectedSubject != null && !attendanceInput.isEmpty()) {
                try {
                    int attendanceValue = Integer.parseInt(attendanceInput);
                    if (attendanceValue >= 0 && attendanceValue <= 100) {
                        // Store the attendance in the Attendance model
                        attendance.addAttendance(selectedSubject, attendanceValue);
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Attendance updated successfully!");
                        successAlert.showAndWait();
                    } else {
                        showError("Attendance must be between 0 and 100.");
                    }
                } catch (NumberFormatException ex) {
                    showError("Please enter a valid number for attendance.");
                }
            } else {
                showError("Please select a subject and enter attendance.");
            }
        });

        Button backButton = new Button("Back to Admin Page");
        backButton.setOnAction(e -> {
            mainApp.showAdminPage(); // Call the method to show the admin page
        });

        layout.getChildren().addAll(title, subjectComboBox, attendanceField, updateButton, backButton);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }

    private void showError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, message);
        errorAlert.showAndWait();
    }
}