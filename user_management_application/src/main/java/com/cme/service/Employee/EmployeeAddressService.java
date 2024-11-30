package com.cme.service.Employee;

import com.cme.entity.Employee.EmployeeAddress;
import com.cme.repository.Employee.EmployeeAddressRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAddressService implements ManageEmployeeAddressInterface {

    @Autowired
    private EmployeeAddressRepository addressRepository;

    @Override
    public EmployeeAddress saveAddress(EmployeeAddress address) {
        if (addressRepository.existsById(address.getEmployeeId())) {
            throw new IllegalArgumentException("Address already exists for employee ID: " + address.getEmployeeId());
        }
        return addressRepository.save(address);
    }

    @Override
    public EmployeeAddress editAddress(EmployeeAddress address) {
        EmployeeAddress existingAddress = addressRepository.findByEmployeeId(address.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Address not found for employee ID: " + address.getEmployeeId()));

        existingAddress.setStreet(address.getStreet());
        existingAddress.setCity(address.getCity());
        existingAddress.setState(address.getState());
        existingAddress.setZipCode(address.getZipCode());
        existingAddress.setCountry(address.getCountry());
        return addressRepository.save(existingAddress);
    }

    @Override
    public EmployeeAddress findByEmployeeId(String employeeId) {
        return addressRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Address not found for employee ID: " + employeeId));
    }
}
