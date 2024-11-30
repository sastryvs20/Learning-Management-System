package com.cme.service.Employee;

import com.cme.entity.Employee.EmployeeContactInfo;
import com.cme.repository.Employee.EmployeeContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactDetailsService {

    @Autowired
    private EmployeeContactInfoRepository contactInfoRepository;

    public EmployeeContactInfo saveContactDetails(EmployeeContactInfo contactInfo) {
        try {
            return contactInfoRepository.save(contactInfo);
        } catch (Exception e) {
            throw new RuntimeException("Error saving contact details", e);
        }
    }

    public EmployeeContactInfo findByEmployeeID(String employeeID) {
        try {
            return contactInfoRepository.findByEmployeeID(employeeID);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching contact details", e);
        }
    }

    public String editContactDetails(EmployeeContactInfo contactInfo) {
        try {
            EmployeeContactInfo existingContact = contactInfoRepository.findByEmployeeID(contactInfo.getEmployeeID());
            if (existingContact != null) {
                existingContact.setPhoneNumber(contactInfo.getPhoneNumber());
                existingContact.setEmail(contactInfo.getEmail());
                existingContact.setAlternatePhoneNumber(contactInfo.getAlternatePhoneNumber());
                contactInfoRepository.save(existingContact);
                return "Contact details updated successfully.";
            } else {
                return "Contact details not found for employee ID: " + contactInfo.getEmployeeID();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error editing contact details", e);
        }
    }
}
