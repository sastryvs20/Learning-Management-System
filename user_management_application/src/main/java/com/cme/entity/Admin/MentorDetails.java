package com.cme.entity.Admin;

import jakarta.persistence.*;

@Entity
public class MentorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentor_ID;

    private String mentorName;

    @Column(name = "mentor_email_id")
    private String mentorEmailID;
    private String mentorSkills;

    public Long getMentorID() {
        return mentor_ID;
    }

    public void setMentorID(Long mentorID) {
        this.mentor_ID = mentorID;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorEmailID() {
        return mentorEmailID;
    }

    public void setMentorEmailID(String mentor_Email_ID) {
        this.mentorEmailID = mentor_Email_ID;
    }

    public String getMentorSkills() {
        return mentorSkills;
    }

    public void setMentorSkills(String mentorSkills) {
        this.mentorSkills = mentorSkills;
    }
}
