package com.example.bai3;

public class Employee {
    protected String maNV;
    protected String tenNV;

    public Employee(String maNV, String tenNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
    }

    @Override
    public String toString() {
        return maNV + " - " + tenNV;
    }
}
