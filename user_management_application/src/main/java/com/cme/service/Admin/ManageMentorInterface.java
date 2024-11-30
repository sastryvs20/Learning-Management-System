package com.cme.service.Admin;

import com.cme.entity.Admin.MentorDetails;

public interface ManageMentorInterface {

    MentorDetails createMentor(MentorDetails mentor); // Create a new mentor

    boolean deleteMentorById(Long mentorId); // Delete a mentor by ID

    MentorDetails updateMentor(MentorDetails mentor); // Update mentor details
}
