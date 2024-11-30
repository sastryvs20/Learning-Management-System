package com.cme.repository.Employee;

import com.cme.entity.Employee.EmployeeEnrolled;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEnrolledRepository extends JpaRepository<EmployeeEnrolled,Long> {
}
