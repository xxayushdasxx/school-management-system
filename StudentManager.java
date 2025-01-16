package com.example.hellofx.model;

import com.example.hellofx.utils.Login;

public class StudentManager implements Login {
    private static final String VALID_ID = "abc";
    private static final String VALID_PASSWORD = "123";

    @Override
    public boolean validateLogin(String regNumber, String password) {
        return VALID_ID.equals(regNumber) && VALID_PASSWORD.equals(password);
    }

    public Student getAuthenticatedStudent() {
        // Create a sample Student object after successful login
        return new Student("Ayush Das", "230958124", VALID_PASSWORD, new int[]{85, 90, 78});
    }
}
