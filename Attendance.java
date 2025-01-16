package com.example.hellofx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Attendance {
    private ObservableList<AttendanceRecord> attendanceRecords;

    public Attendance() {
        attendanceRecords = FXCollections.observableArrayList();
    }

    public void addAttendance(String subject, int attendance) {
        attendanceRecords.add(new AttendanceRecord(subject, attendance));
    }

    public ObservableList<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }
}

