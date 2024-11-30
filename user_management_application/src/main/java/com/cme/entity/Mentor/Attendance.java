package com.cme.entity.Mentor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

import java.time.LocalDate;

@Entity
public class Attendance {

    @Id
    @Column(name = "employee_id", nullable = false)
    private String employeeId; // Employee ID

    @Column(name = "employee_name", nullable = false)
    private String employeeName; // Employee Name

    @Column(name = "status", nullable = false)
    private String status; // Attendance Status (PRESENT/ABSENT)


    @Column(name = "attendance_date", nullable = false)
    private LocalDate date; // Date of attendance


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Automatically set the date to the current date before persisting the entity
    @PrePersist
    public void prePersist() {
        if (this.date == null) {
            this.date = LocalDate.now(); // Set the current date if date is not provided
        }
    }
}
