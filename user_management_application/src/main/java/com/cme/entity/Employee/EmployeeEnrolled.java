package com.cme.entity.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmployeeEnrolled {
    @Id
    private Long BatchId;
    private String BatchName;
    private String MentorName;
    private String MaxStrength;
    private String Enrolled;

    public Long getBatchId() {
        return BatchId;
    }

    public void setBatchId(Long batchId) {
        BatchId = batchId;
    }

    public String getBatchName() {
        return BatchName;
    }

    public void setBatchName(String batchName) {
        BatchName = batchName;
    }

    public String getMentorName() {
        return MentorName;
    }

    public void setMentorName(String mentorName) {
        MentorName = mentorName;
    }

    public String getMaxStrength() {
        return MaxStrength;
    }

    public void setMaxStrength(String maxStrength) {
        MaxStrength = maxStrength;
    }

    public String getEnrolled() {
        return Enrolled;
    }

    public void setEnrolled(String enrolled) {
        Enrolled = enrolled;
    }
}
