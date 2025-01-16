package com.example.hellofx.utils;

import com.example.hellofx.ui.*;
import com.example.hellofx.model.Attendance; // Import the Attendance model
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class SchoolManagementSystem extends Application {

    private BorderPane mainLayout;
    private Stage primaryStage;
    private FeesPage feesPage;
    private Attendance attendance; // Declare attendance instance
    private final String USERNAME = "abc";
    private String password = "123";
    private final String ADMIN_USERNAME = "adminabc";
    private final String ADMIN_PASSWORD = "123";
    public List<String> supportQueries;

    public void start(Stage stage) {
        mainLayout = new BorderPane();
        primaryStage = stage;
        feesPage = new FeesPage(primaryStage);
        attendance = new Attendance(); // Initialize attendance
        supportQueries = new ArrayList<>();

        primaryStage.setTitle("School Management System");
        primaryStage.show();

        // Start with the login page
        showLoginPage();
    }

    private void showLoginPage() {
        VBox loginLayout = new VBox(10);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setStyle("-fx-padding: 20;");

        Text title = new Text("School Management System - Login");
        title.setStyle("-fx-font-size: 24;");

        TextField regNumberField = new TextField();
        regNumberField.setPromptText("Enter Registration Number");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        Text errorText = new Text();
        errorText.setFill(Color.RED);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            if (regNumberField.getText().equals(USERNAME) && passwordField.getText().equals(password)) {
                showMainPage();
            } else {
                errorText.setText("Incorrect username/password");
            }
        });

        Button adminLoginButton = new Button("Admin Login");
        adminLoginButton.setOnAction(e -> showAdminLoginPage());

        Button forgotPasswordButton = new Button("Forgot Password?");
        forgotPasswordButton.setStyle("-fx-background-color: transparent; -fx-text-fill: blue; -fx-underline: true;");
        forgotPasswordButton.setOnAction(e -> showForgotPasswordPage());

        VBox fieldsBox = new VBox(10, regNumberField, passwordField, loginButton, adminLoginButton, forgotPasswordButton, errorText);
        fieldsBox.setAlignment(Pos.CENTER);

        loginLayout.getChildren().addAll(title, fieldsBox);
        primaryStage.setScene(new Scene(loginLayout, 400, 300));
    }

    public void showAdminLoginPage() {
        VBox adminLoginLayout = new VBox(10);
        adminLoginLayout.setAlignment(Pos.CENTER);
        adminLoginLayout.setStyle("-fx-padding: 20;");

        Text title = new Text("Admin Login");
        title.setStyle("-fx-font-size: 24;");

        TextField adminUsernameField = new TextField();
        adminUsernameField.setPromptText("Enter Admin Username");

        PasswordField adminPasswordField = new PasswordField();
        adminPasswordField.setPromptText("Enter Admin Password");

        Text adminErrorText = new Text();
        adminErrorText.setFill(Color.RED);

        Button adminLoginButton = new Button("Login");
        adminLoginButton.setOnAction(e -> {
            if (adminUsernameField.getText().equals(ADMIN_USERNAME) && adminPasswordField.getText().equals(ADMIN_PASSWORD)) {
                showAdminPage(); // Show admin page if credentials match
            } else {
                adminErrorText.setText("Incorrect admin username/password");
            }
        });

        Button backButton = new Button("Back to Login");
        backButton.setOnAction(e -> showLoginPage());

        VBox adminFieldsBox = new VBox(10, adminUsernameField, adminPasswordField, adminLoginButton, backButton, adminErrorText);
        adminFieldsBox.setAlignment(Pos.CENTER);

        adminLoginLayout.getChildren().addAll(title, adminFieldsBox);
        primaryStage.setScene(new Scene(adminLoginLayout, 400, 300));
    }

    public void showAdminPage() {
        VBox adminLayout = new VBox(10);
        adminLayout.setAlignment(Pos.CENTER);
        adminLayout.setStyle("-fx-padding: 20;");

        // Header with title and logout option
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        Text title = new Text("School Management System - Admin");
        title.setStyle("-fx-font-size: 24; -fx-underline: true; -fx-cursor: hand;");
        title.setOnMouseClicked(e -> showMainPage());

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> showLoginPage());

        header.getChildren().addAll(title, logoutButton);

        // Main content area
        VBox contentLayout = new VBox(10);
        contentLayout.setAlignment(Pos.CENTER);

        Button updateAttendanceButton = new Button("Update Attendance");
        updateAttendanceButton.setOnAction(e -> {
            UpdateAttendance updateAttendance = new UpdateAttendance(primaryStage, this, attendance);
            updateAttendance.display();
        });


        Button viewGrievancesButton = new Button("View Grievances");
        viewGrievancesButton.setOnAction(e -> viewGrievances());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminLoginPage()); // Navigate back to the admin login page

        contentLayout.getChildren().addAll(updateAttendanceButton, viewGrievancesButton, backButton);

        adminLayout.getChildren().addAll(header, contentLayout);

        primaryStage.setScene(new Scene(adminLayout, 600, 400));
    }

    public void showUpdateAttendancePage() {
        VBox updateAttendanceLayout = new VBox(10);
        updateAttendanceLayout.setAlignment(Pos.CENTER);
        updateAttendanceLayout.setStyle("-fx-padding: 20;");

        // Header
        Text title = new Text("Update Attendance");
        title.setStyle("-fx-font-size: 24;");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminPage()); // Navigate back to the admin page

        // Add your attendance update logic here
        // For example, a form to update attendance

        updateAttendanceLayout.getChildren().addAll(title, backButton);

        primaryStage.setScene(new Scene(updateAttendanceLayout, 600, 400));
    }


    private void viewGrievances() {
        VBox grievancesLayout = new VBox(10);
        grievancesLayout.setAlignment(Pos.CENTER);
        grievancesLayout.setStyle("-fx-padding: 20;");

        Text title = new Text("Grievances");
        title.setStyle("-fx-font-size: 24;");

        if (supportQueries.isEmpty()) {
            Text noQueriesText = new Text("No grievances submitted yet.");
            grievancesLayout.getChildren().addAll(title, noQueriesText);
        } else {
            TextArea queriesArea = new TextArea();
            queriesArea.setEditable(false);
            StringBuilder queriesStringBuilder = new StringBuilder("Submitted Queries:\n\n");
            for (String query : supportQueries) {
                queriesStringBuilder.append(query).append("\n");
            }
            queriesArea.setText(queriesStringBuilder.toString());
            grievancesLayout.getChildren().addAll(title, queriesArea);
        }

        Button backButton = new Button("Back to Admin Page");
        backButton.setOnAction(e -> showAdminPage());
        grievancesLayout.getChildren().add(backButton);

        primaryStage.setScene(new Scene(grievancesLayout, 600, 400));
    }

    private void openGrievanceForm() {
        // Logic to open the grievance form
        System.out.println("Grievance form opened");
    }

    private void showForgotPasswordPage() {
        VBox forgotPasswordLayout = new VBox(10);
        forgotPasswordLayout.setAlignment(Pos.CENTER);
        forgotPasswordLayout.setStyle("-fx-padding: 20;");

        Text title = new Text("Reset Password");
        title.setStyle("-fx-font-size: 24;");

        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Enter New Password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm New Password");

        Text resetErrorText = new Text();
        resetErrorText.setFill(Color.RED);

        Button resetButton = new Button("Reset Password");
        resetButton.setOnAction(e -> {
            if (newPasswordField.getText().equals(confirmPasswordField.getText())) {
                password = newPasswordField.getText(); // Update the password
                showLoginPage(); // Return to login page
            } else {
                resetErrorText.setText("Passwords do not match!");
            }
        });

        Button backButton = new Button("Back to Login");
        backButton.setOnAction(e -> showLoginPage());

        VBox fieldsBox = new VBox(10, newPasswordField, confirmPasswordField, resetButton, backButton, resetErrorText);
        fieldsBox.setAlignment(Pos.CENTER);

        forgotPasswordLayout.getChildren().addAll(title, fieldsBox);

        primaryStage.setScene(new Scene(forgotPasswordLayout, 600, 400));
    }

    public void showMainPage() {
        mainLayout = new BorderPane();
        mainLayout.setTop(createHeader());
        mainLayout.setCenter(createOptionGrid());

        primaryStage.setScene(new Scene(mainLayout, 800, 600));
    }

    private GridPane createOptionGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        String[] options = {"Profile", "Attendance", "Fees", "Support"};
        for (String option : options) {
            Button optionButton = new Button(option);
            optionButton.setPrefSize(200, 200);
            if (option.equals("Profile")) {
                optionButton.setOnAction(e -> openProfilePage());
            } else if (option.equals("Fees")) {
                optionButton.setOnAction(e -> showFeesPage());
            } else if (option.equals("Attendance")) {
                optionButton.setOnAction(e -> {
                    StudentAttendance studentAttendance = new StudentAttendance(primaryStage, attendance, this); // Pass the attendance instance
                    studentAttendance.display(); // Show student attendance
                });
            }
            else if (option.equals("Attendance")) {
                optionButton.setOnAction(e -> {
                    // Create an instance of StudentAttendance and display it
                    StudentAttendance studentAttendance = new StudentAttendance(primaryStage, attendance, this);
                    studentAttendance.display(); // Show student attendance
                });
            }
            else if (option.equals("Support")) {
                optionButton.setOnAction(e -> {
                    SupportPage<String> supportPage = new SupportPage<>(primaryStage, this); // Pass the main application instance
                    supportPage.display(); // Show support page
                });
            }
            else {
                optionButton.setOnAction(e -> openOptionPage(option));
            }
            grid.add(optionButton, grid.getChildren().size() % 3, grid.getChildren().size() / 3);
        }
        return grid;
    }

    private void openProfilePage() {
        ProfilePage profilePage = new ProfilePage();
        profilePage.display(primaryStage, mainLayout);
    }

    public void showFeesPage() {
        // Create a new BorderPane layout for the fees page
        BorderPane feesLayout = new BorderPane();
        feesLayout.setTop(createHeader());  // Keep the header visible
        feesLayout.setCenter(feesPage.displayFees().getRoot());  // Set FeesPage content in the center

        // Update the main layout to display the fees page
        mainLayout.setCenter(feesLayout.getCenter());
    }

    private void openOptionPage(String option) {
        // Logic to open different option pages based on the button clicked
        switch (option) {
            case "Grievances":
                openGrievanceForm(); // Replace with your actual grievance form logic
                break;
            // Add more cases as needed for other options
            default:
                System.out.println("Option not implemented: " + option);
                break;
        }
    }

    private void logout() {
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out?");
        logoutAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                showLoginPage(); // Navigate back to the login page
                System.out.println("Logged out successfully.");
            }
        });
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setStyle("-fx-background-color: blue; -fx-padding: 10;");
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(20);

        Text logo = new Text("School Management System");
        logo.setFill(Color.WHITE);
        logo.setOnMouseClicked(e -> showMainPage());

        Button logoutButton = new Button("Log Out");
        logoutButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-width: 0; -fx-padding: 0;");
        logoutButton.setOnMouseEntered(e -> logoutButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-underline: true; -fx-border-width: 0;"));
        logoutButton.setOnMouseExited(e -> logoutButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-width: 0; -fx-padding: 0;"));

        logoutButton.setOnAction(e -> logout());

        HBox rightBox = new HBox(logoutButton);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        header.getChildren().addAll(logo, rightBox);
        return header;
    }

    public static void main(String[] args) {
        launch(args);
    }
}