package com.payroll.model;

import java.sql.Timestamp;

public class Payslip {
    private int id;
    private int employeeId;
    private int month;
    private int year;
    private double basic;
    private double hra;
    private double deductions;
    private double net;
    private Timestamp generatedAt;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getBasic() { return basic; }
    public void setBasic(double basic) { this.basic = basic; }
    public double getHra() { return hra; }
    public void setHra(double hra) { this.hra = hra; }
    public double getDeductions() { return deductions; }
    public void setDeductions(double deductions) { this.deductions = deductions; }
    public double getNet() { return net; }
    public void setNet(double net) { this.net = net; }
    public Timestamp getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(Timestamp generatedAt) { this.generatedAt = generatedAt; }
}
