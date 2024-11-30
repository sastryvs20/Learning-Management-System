package com.cme.service.Employee;

import com.cme.entity.Employee.EmployeeBankDetails;

public interface EmployeeBankDetailsInterface {
    EmployeeBankDetails saveEmployeeBankDetails(EmployeeBankDetails bankDetails);
    EmployeeBankDetails findByAccountNumber(String accountNumber);
    boolean updateBankDetails(EmployeeBankDetails bankDetails);
}
