package com.cme.repository.Employee;

import com.cme.entity.Employee.EmployeeContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeContactInfoRepository extends JpaRepository<EmployeeContactInfo, String> {
    EmployeeContactInfo findByEmployeeID(String employeeID);
}
