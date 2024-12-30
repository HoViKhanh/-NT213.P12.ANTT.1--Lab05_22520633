package com.example.bai3;

public class EmployeeFullTime extends Employee {
    private double luong;

    public EmployeeFullTime(String maNV, String tenNV, double luong) {
        super(maNV, tenNV);
        this.luong = luong;
    }

    @Override
    public String toString() {
        return super.toString() + " -->FullTime=" + luong;
    }
}
