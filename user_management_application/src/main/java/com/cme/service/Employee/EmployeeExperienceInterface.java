package com.cme.service.Employee;

import com.cme.entity.Employee.EmployeeExperienceDetails;

public interface EmployeeExperienceInterface {
    EmployeeExperienceDetails saveEmployeeExperienceDetails(EmployeeExperienceDetails experienceDetails);

    EmployeeExperienceDetails findByEmployeeID(String employeeID);

    boolean updateExperienceDetails(EmployeeExperienceDetails experienceDetails);
}
