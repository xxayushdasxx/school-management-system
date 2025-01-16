package com.example.hellofx.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

// AttendanceRecord class to hold individual attendance records
public class AttendanceRecord {
    private final SimpleStringProperty subject;
    private final SimpleIntegerProperty attendance;

    public AttendanceRecord(String subject, int attendance) {
        this.subject = new SimpleStringProperty(subject);
        this.attendance = new SimpleIntegerProperty(attendance);
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public SimpleIntegerProperty attendanceProperty() {
        return attendance;
    }
}
