package com.cme.entity.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

@Entity
public class EmployeeExperienceDetails {

    @Id
    @NotNull
    private String employeeID; // Align getter/setter name with field name

    private int years;

    private String lastExperience;
    private String role;
    private String lastEmployer;

    // Getters and Setters
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getLastExperience() {
        return lastExperience;
    }

    public void setLastExperience(String lastExperience) {
        this.lastExperience = lastExperience;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastEmployer() {
        return lastEmployer;
    }

    public void setLastEmployer(String lastEmployer) {
        this.lastEmployer = lastEmployer;
    }
}
