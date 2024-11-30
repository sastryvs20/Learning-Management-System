package com.cme.repository.Employee;

import com.cme.entity.Employee.EmployeeBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeBankDetailsRepository extends JpaRepository<EmployeeBankDetails,Long> {
    EmployeeBankDetails findByAccountNumber(String accountNumber);
}
