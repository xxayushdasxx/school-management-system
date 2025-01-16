package com.example.hellofx.ui;

import com.example.hellofx.model.Attendance;
import com.example.hellofx.model.AttendanceRecord;
import com.example.hellofx.utils.SchoolManagementSystem;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class StudentAttendance {
    private Stage stage;
    private Attendance attendance; // Reference to the Attendance model
    private SchoolManagementSystem mainApp; // Reference to the main application

    public StudentAttendance(Stage stage, Attendance attendance, SchoolManagementSystem mainApp) {
        this.stage = stage;
        this.attendance = attendance; // Store reference to attendance
        this.mainApp = mainApp; // Store reference to the main application
    }

    public void display() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20;");

        Text title = new Text("Your Attendance");
        title.setStyle("-fx-font-size: 24;");

        // Create a TableView to display attendance data
        TableView<AttendanceRecord> tableView = new TableView<>();
        TableColumn<AttendanceRecord, String> subjectColumn = new TableColumn<>("Subject");
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());

        TableColumn<AttendanceRecord, Integer> attendanceColumn = new TableColumn<>("Attendance (%)");
        attendanceColumn.setCellValueFactory(cellData -> cellData.getValue().attendanceProperty().asObject());

        tableView.getColumns().addAll(subjectColumn, attendanceColumn);
        tableView.setItems(attendance.getAttendanceRecords()); // Get attendance records from the Attendance model

        // Back button to return to the main page
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> mainApp.showMainPage()); // Call the method to show the main page

        layout.getChildren().addAll(title, tableView, backButton);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
    }
}