package com.cme.service.Employee;

import com.cme.entity.Employee.EmployeeAddress;

public interface ManageEmployeeAddressInterface {
    EmployeeAddress saveAddress(EmployeeAddress address);
    EmployeeAddress editAddress(EmployeeAddress address);
    EmployeeAddress findByEmployeeId(String employeeId);
}
