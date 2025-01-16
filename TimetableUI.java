package com.example.hellofx.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TimetableUI {

    public VBox createTimetableUI() {
        VBox timetableLayout = new VBox(10);
        timetableLayout.setPadding(new Insets(20));

        Label timetableLabel = new Label("Your Timetable");
        timetableLabel.setFont(new Font(20));
        timetableLayout.getChildren().add(timetableLabel);

        TableView<TimetableEntry> timetableTable = new TableView<>();

        TableColumn<TimetableEntry, String> dayColumn = new TableColumn<>("Day");
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        dayColumn.setMinWidth(80);

        TableColumn<TimetableEntry, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeColumn.setMinWidth(100);

        TableColumn<TimetableEntry, String> subjectColumn = new TableColumn<>("Subject");
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));

        timetableTable.getColumns().addAll(dayColumn, timeColumn, subjectColumn);

        // Sample timetable data
        ObservableList<TimetableEntry> timetableData = FXCollections.observableArrayList(
                new TimetableEntry("Mon", "8:00 - 9:00", "Maths"),
                new TimetableEntry("Mon", "9:00 - 10:00", "OOP Lab (B1)")

                // Add more timetable entries as needed
        );

        timetableTable.setItems(timetableData);

        timetableLayout.getChildren().add(timetableTable);

        return timetableLayout;
    }

    private class TimetableEntry {
        private final String day;
        private final String time;
        private final String subject;

        public TimetableEntry(String day, String time, String subject) {
            this.day = day;
            this.time = time;
            this.subject = subject;
        }

        public String getDay() {
            return day;
        }

        public String getTime() {
            return time;
        }

        public String getSubject() {
            return subject;
        }
    }
}