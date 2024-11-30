package com.cme.service.Mentor;

import com.cme.entity.Mentor.MockDetails;

import java.util.List;
import java.util.Optional;

public interface MockDetailsInterface {

    // Method to create MockDetails
    public MockDetails saveMockDetails(MockDetails mockDetails);

    // Method to update MockDetails
    public MockDetails updateMockDetails(MockDetails mockDetails);

    // Method to fetch MockDetails by employee ID
    Optional<MockDetails> getMockDetailsByEmployeeID(String employeeID);

    // Method to get all mock details
    List<MockDetails> getAllMockDetails();

    // Method to delete mock details by employee ID
    void deleteMockDetails(String employeeID);
}
