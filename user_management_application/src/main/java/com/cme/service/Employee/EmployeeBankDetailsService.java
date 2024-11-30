package com.cme.service.Employee;

import com.cme.entity.Employee.EmployeeBankDetails;
import com.cme.repository.Employee.EmployeeBankDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeBankDetailsService implements EmployeeBankDetailsInterface {

    @Autowired
    private EmployeeBankDetailsRepository bankDetailsRepo;

    @Override
    public EmployeeBankDetails saveEmployeeBankDetails(EmployeeBankDetails bankDetails) {
        // Check if account number is provided and save details
        if (bankDetails.getAccountNumber() != null && !bankDetails.getAccountNumber().isEmpty()) {
            return bankDetailsRepo.save(bankDetails);
        }
        throw new IllegalArgumentException("Account Number is required.");
    }

    @Override
    public EmployeeBankDetails findByAccountNumber(String accountNumber) {
        return bankDetailsRepo.findByAccountNumber(accountNumber);
    }

    @Override
    public boolean updateBankDetails(EmployeeBankDetails bankDetails) {
        EmployeeBankDetails existingDetails = findByAccountNumber(bankDetails.getAccountNumber());
        if (existingDetails != null) {
            // Update fields only if they are not null or empty
            if (bankDetails.getHolderName() != null && !bankDetails.getHolderName().isEmpty()) {
                existingDetails.setHolderName(bankDetails.getHolderName());
            }
            if (bankDetails.getBankName() != null && !bankDetails.getBankName().isEmpty()) {
                existingDetails.setBankName(bankDetails.getBankName());
            }
            if (bankDetails.getBranchName() != null && !bankDetails.getBranchName().isEmpty()) {
                existingDetails.setBranchName(bankDetails.getBranchName());
            }
            if (bankDetails.getIfscCode() != null && !bankDetails.getIfscCode().isEmpty()) {
                existingDetails.setIfscCode(bankDetails.getIfscCode());
            }
            if (bankDetails.getAccountType() != null && !bankDetails.getAccountType().isEmpty()) {
                existingDetails.setAccountType(bankDetails.getAccountType());
            }
            bankDetailsRepo.save(existingDetails);
            return true;
        }
        return false;
    }
}
