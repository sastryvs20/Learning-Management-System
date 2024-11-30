package com.cme.service.Admin;

import com.cme.entity.Admin.MentorDetails;
import com.cme.repository.Admin.MentorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MentorService {

    @Autowired
    private MentorDetailsRepository mentorRepo;

    // Create a new mentor
    public MentorDetails createMentor(MentorDetails mentor) {
        return mentorRepo.save(mentor);
    }

    // Update an existing mentor
    public MentorDetails updateMentor(MentorDetails mentor) {
        Optional<MentorDetails> existingMentor = mentorRepo.findById(mentor.getMentorID());
        if (existingMentor.isPresent()) {
            MentorDetails updatedMentor = existingMentor.get();
            updatedMentor.setMentorName(mentor.getMentorName());
            updatedMentor.setMentorEmailID(mentor.getMentorEmailID());
            updatedMentor.setMentorSkills(mentor.getMentorSkills());
            return mentorRepo.save(updatedMentor);
        }
        throw new RuntimeException("Mentor not found with ID: " + mentor.getMentorID());
    }

    // Delete a mentor by ID
    public boolean deleteMentorById(Long mentorId) {
        Optional<MentorDetails> mentor = mentorRepo.findById(mentorId);
        if (mentor.isPresent()) {
            mentorRepo.delete(mentor.get());
            return true;
        }
        return false;
    }
}
