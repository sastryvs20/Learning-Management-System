package com.cme.repository.Employee;

import com.cme.entity.Employee.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, String> {
    Optional<EmployeeAddress> findByEmployeeId(String employeeId);
}
