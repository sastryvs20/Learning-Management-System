package com.cme.service.Mentor;

import com.cme.entity.Mentor.MockDetails;
import com.cme.repository.Mentor.MockDetailsRepository;
import com.cme.repository.User.UserDetailsRepository;
import com.cme.service.Mentor.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MockDetailsService implements MockDetailsInterface {

    private final MockDetailsRepository mockDetailsRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final EmailService emailService;

    @Autowired
    public MockDetailsService(MockDetailsRepository mockDetailsRepository, UserDetailsRepository userDetailsRepository, EmailService emailService) {
        this.mockDetailsRepository = mockDetailsRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.emailService = emailService;
    }

    @Override
    public MockDetails saveMockDetails(MockDetails mockDetails) {
        // Save mock details
        MockDetails savedMockDetails = mockDetailsRepository.save(mockDetails);

        // Fetch employee email using employeeID
        Optional<String> employeeEmail = userDetailsRepository.findEmailByEmployeeId(mockDetails.getEmployeeID());

        if (employeeEmail.isPresent()) {
            // Send email if employee email exists
            emailService.sendMockInterviewDetails(
                    employeeEmail.get(),
                    mockDetails.getMentorID(),
                    mockDetails.getMentorName(),
                    mockDetails.getEmployeeID(),
                    mockDetails.getTechnology(),
                    mockDetails.getMockDate().toString(),
                    mockDetails.getMockTime().toString()
            );
        }

        return savedMockDetails;
    }

    @Override
    public MockDetails updateMockDetails(MockDetails mockDetails) {
        // Use employeeID to check for existence
        if (mockDetailsRepository.existsById(mockDetails.getEmployeeID())) {
            return mockDetailsRepository.save(mockDetails);
        } else {
            throw new RuntimeException("Mock details not found for Employee ID: " + mockDetails.getEmployeeID());
        }
    }

    @Override
    public Optional<MockDetails> getMockDetailsByEmployeeID(String employeeID) {
        return mockDetailsRepository.findById(employeeID);
    }

    @Override
    public List<MockDetails> getAllMockDetails() {
        return mockDetailsRepository.findAll();
    }

    @Override
    public void deleteMockDetails(String employeeID) {
        // Use employeeID to delete mock details
        if (mockDetailsRepository.existsById(employeeID)) {
            mockDetailsRepository.deleteById(employeeID);
        } else {
            throw new RuntimeException("Mock details not found for Employee ID: " + employeeID);
        }
    }

    // Method to handle both creating and updating mock details based on action type (create/edit)
    public MockDetails saveOrUpdateMockDetails(MockDetails mockDetails, String actionType) {
        if ("create".equalsIgnoreCase(actionType)) {
            return saveMockDetails(mockDetails);
        } else if ("edit".equalsIgnoreCase(actionType)) {
            return updateMockDetails(mockDetails);
        } else {
            throw new IllegalArgumentException("Invalid action type");
        }
    }
}