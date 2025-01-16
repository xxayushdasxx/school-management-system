package com.example.hellofx.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Payment {
    private int amount;
    private String method;
    private int semester;
    private String receiptNumber;
    private String paymentDate; // New field for date and time
    private int transactionId;   // New field for transaction ID
    private Date date;

    // Constructor
    public Payment(int amount, String method, int semester, String receiptNumber, int totalInstallments) {
        this.amount = amount;
        this.method = method;
        this.semester = semester;
        this.receiptNumber = receiptNumber;
        this.paymentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); // Set current date
        this.transactionId = generateTransactionId(); // Generate a random transaction ID
        this.date = new Date();
    }

    // Generate a random transaction ID
    private int generateTransactionId() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Generates a 6-digit random transaction ID
    }

    // Getters
    public String getReceiptNumber() {
        return receiptNumber;
    }

    public int getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public int getSemester() {
        return semester;
    }

    public Date getDate() {
        return date;
    }

    public int getTransactionId() {
        return transactionId; // New getter for transaction ID
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", method='" + method + '\'' +
                ", semester=" + semester +
                ", receiptNumber='" + receiptNumber + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", transactionId=" + transactionId +
                '}';
    }
}
