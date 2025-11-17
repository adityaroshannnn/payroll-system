package com.payroll.model;

import java.sql.Date;

public class Attendance {
    private int id;
    private int employeeId;
    private Date attDate;
    private String status; // PRESENT / ABSENT

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
    public Date getAttDate() { return attDate; }
    public void setAttDate(Date attDate) { this.attDate = attDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
