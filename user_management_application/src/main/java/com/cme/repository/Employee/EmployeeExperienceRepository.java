package com.cme.repository.Employee;

import com.cme.entity.Employee.EmployeeExperienceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeExperienceRepository extends JpaRepository<EmployeeExperienceDetails, String> {
    EmployeeExperienceDetails findByEmployeeID(String employeeID); // Match exact casing with field
}
