package com.example.hellofx.ui;

import com.example.hellofx.data.ProfileData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateProfilePage {
    private final Stage primaryStage;

    public CreateProfilePage() {
        Stage stage = null;
        this.primaryStage = stage;
    }

    public void display() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // Create input fields
        TextField enrollmentField = createTextField("Enrollment Number", grid, 0);
        TextField applicationField = createTextField("Application Number", grid, 1);
        TextField nameField = createTextField("Name", grid, 2);
        TextField academicYearField = createTextField("Academic Year", grid, 3);
        TextField programField = createTextField("Program / Branch", grid, 4);
        TextField dojField = createTextField("DOJ - Class start date", grid, 5);
        TextField dobField = createTextField("DOB", grid, 6);
        TextField genderField = createTextField("Gender", grid, 7);
        TextField mobileField = createTextField("Mobile Number", grid, 8);
        TextField emailField = createTextField("Email ID", grid, 9);
        TextField bloodGroupField = createTextField("Blood Group", grid, 10);
        TextField categoryField = createTextField("Category of Admission", grid, 11);
        TextField nationalityField = createTextField("Nationality", grid, 12);
        TextField religionField = createTextField("Religion", grid, 13);
        TextField socialCategoryField = createTextField("Social Category", grid, 14);
        TextField motherTongueField = createTextField("Mother Tongue", grid, 15);
        TextField maritalStatusField = createTextField("Marital Status", grid, 16);
        TextField domicileStateField = createTextField("Domicile State", grid, 17);

        // Save button
        Button saveButton = new Button("Save Profile");
        saveButton.setOnAction(e -> {
            ProfileData profileData = ProfileData.getInstance();
            profileData.setEnrollmentNumber(enrollmentField.getText());
            profileData.setApplicationNumber(applicationField.getText());
            profileData.setName(nameField.getText());
            // Set all other fields

            // Redirect to login or main page
            primaryStage.setScene(new Scene(new Label("Profile saved! Please log in."), 400, 200));
        });

        grid.add(saveButton, 1, 18);
        primaryStage.setScene(new Scene(grid, 600, 800));
    }

    private TextField createTextField(String labelText, GridPane grid, int rowIndex) {
        Label label = new Label(labelText);
        TextField textField = new TextField();
        grid.add(label, 0, rowIndex);
        grid.add(textField, 1, rowIndex);
        return textField;
    }
}
