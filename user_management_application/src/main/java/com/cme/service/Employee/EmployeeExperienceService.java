package com.cme.service.Employee;

import com.cme.entity.Employee.EmployeeExperienceDetails;
import com.cme.repository.Employee.EmployeeExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeExperienceService implements EmployeeExperienceInterface {

    @Autowired
    private EmployeeExperienceRepository experienceDetailsRepo;

    @Override
    public EmployeeExperienceDetails saveEmployeeExperienceDetails(EmployeeExperienceDetails experienceDetails) {
        if (experienceDetails.getEmployeeID() != null && !experienceDetails.getEmployeeID().isEmpty()) {
            return experienceDetailsRepo.save(experienceDetails);
        }
        throw new IllegalArgumentException("Employee ID is required.");
    }

    @Override
    public EmployeeExperienceDetails findByEmployeeID(String employeeID) {
        return experienceDetailsRepo.findByEmployeeID(employeeID);
    }

    @Override
    public boolean updateExperienceDetails(EmployeeExperienceDetails experienceDetails) {
        EmployeeExperienceDetails existingDetails = findByEmployeeID(experienceDetails.getEmployeeID());
        if (existingDetails != null) {
            if (experienceDetails.getLastExperience() != null) {
                existingDetails.setLastExperience(experienceDetails.getLastExperience());
            }
            if (experienceDetails.getRole() != null) {
                existingDetails.setRole(experienceDetails.getRole());
            }
            if (experienceDetails.getLastEmployer() != null) {
                existingDetails.setLastEmployer(experienceDetails.getLastEmployer());
            }
            if (experienceDetails.getYears() > 0) {
                existingDetails.setYears(experienceDetails.getYears());
            }
            experienceDetailsRepo.save(existingDetails);
            return true;
        }
        return false;
    }
}
