package com.example.lab2_bai4;

public class Employee {
    private String fullName;
    private boolean isManager;

    public Employee(String fullName, boolean isManager) {
        this.fullName = fullName;
        this.isManager = isManager;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isManager() {
        return isManager;
    }
}

