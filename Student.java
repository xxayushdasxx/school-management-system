package com.example.hellofx.model;

public class Student extends User {
    private int[] grades;

    public Student(String name, String registrationNumber, String password, int[] grades) {
        super(name, registrationNumber, password);
        this.grades = grades;
    }

    public int[] getGrades() {
        return grades;
    }
}
