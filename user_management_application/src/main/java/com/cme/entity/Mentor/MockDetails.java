package com.cme.entity.Mentor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class MockDetails {

    @Id
    @NotNull(message = "Mentor ID cannot be null.")
    private String mentorID;

    private String employeeID; // Panel 2 mentor's employee ID
    private String technology;
    private String mentorName; // Panel 2 mentor's name
    private LocalDate mockDate;
    private LocalTime mockTime;

    // Getters and setters

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getMentorID() {
        return mentorID;
    }

    public void setMentorID(String mentorID) {
        this.mentorID = mentorID;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public LocalDate getMockDate() {
        return mockDate;
    }

    public void setMockDate(LocalDate mockDate) {
        this.mockDate = mockDate;
    }

    public LocalTime getMockTime() {
        return mockTime;
    }

    public void setMockTime(LocalTime mockTime) {
        this.mockTime = mockTime;
    }
}
