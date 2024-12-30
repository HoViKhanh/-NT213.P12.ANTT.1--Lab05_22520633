package com.example.bai3;

public class EmployeePartTime extends Employee {
    private double luong;

    public EmployeePartTime(String maNV, String tenNV, double luong) {
        super(maNV, tenNV);
        this.luong = luong;
    }

    @Override
    public String toString() {
        return super.toString() + " -->PartTime=" + luong;
    }
}
