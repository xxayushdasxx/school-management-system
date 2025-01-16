package com.example.hellofx.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProfilePage {
    public void display(Stage stage, BorderPane mainLayout) {
        // Create a GridPane for displaying profile information
        GridPane profileLayout = new GridPane();
        profileLayout.setStyle("-fx-padding: 20; -fx-background-color: white;");
        profileLayout.setHgap(10);
        profileLayout.setVgap(10);

        // Define profile details in a 2D array (tabular format)
        String[][] details = {
                {"Enrollment Number", "230958124"},
                {"Application Number", "123129935"},
                {"Name (As Per 12th Marks Card)", "AYUSH DAS"},
                {"Academic Year", "2024-2025"},
                {"Program / Branch", "Computer Science & Financial Technology"},
                {"DOJ - Class start date", "31 Jul 2023"},
                {"DOB - DD/MM/YYYY", "13 Feb 2006"},
                {"Gender", "Male"},
                {"Present Mobile Number", "9490018813"},
                {"Earlier Mobile Number", "9490018813"},
                {"Email ID", "AYUSH.DAS0213@GMAIL.COM"},
                {"Blood Group", "A+"},
                {"Category of Admission", "GE"},
                {"Nationality", "INDIA"},
                {"Religion", "HINDU"},
                {"Social Category", "GENERAL"},
                {"Mother Tongue", "BENGALI"},
                {"Marital Status", "Unmarried"},
                {"Domicile State", "Telangana"}
        };

        // Populate the GridPane with profile labels and data
        for (int i = 0; i < details.length; i++) {
            // Create labels for each row in the profile details array
            Label labelKey = new Label(details[i][0]);
            Label labelValue = new Label(details[i][1]);

            // Add the key and value to the GridPane at appropriate positions
            profileLayout.add(labelKey, 0, i); // Column 0 for key
            profileLayout.add(labelValue, 1, i); // Column 1 for value
        }

        // Set the profile layout in the center of the main layout (BorderPane)
        mainLayout.setCenter(profileLayout);
    }



}
