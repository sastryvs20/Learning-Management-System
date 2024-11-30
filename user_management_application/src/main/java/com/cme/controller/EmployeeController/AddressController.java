package com.cme.controller.EmployeeController;

import com.cme.entity.Employee.EmployeeAddress;
import com.cme.service.Employee.ManageEmployeeAddressInterface;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddressController {

    @Autowired
    private ManageEmployeeAddressInterface addressService;

    @GetMapping("/employee/manageAddress")
    public String manageAddress(@RequestParam(value = "employeeId", required = false) String employeeId, Model model) {
        if (employeeId != null && !employeeId.isEmpty()) {
            try {
                EmployeeAddress address = addressService.findByEmployeeId(employeeId);
                model.addAttribute("address", address);
            } catch (RuntimeException e) {
                model.addAttribute("error", e.getMessage());
            }
        }
        return "manageAddress";  // Ensure the address model attribute is passed here.
    }

    @PostMapping("/employee/manageAddress")
    public String handleAddress(@RequestParam("actionType") String actionType,
                                @ModelAttribute EmployeeAddress address,
                                Model model) {
        if (address.getEmployeeId() == null || address.getEmployeeId().isEmpty()) {
            model.addAttribute("error", "Employee ID is required.");
            return "manageAddress";
        }

        try {
            if ("save".equals(actionType)) {
                addressService.saveAddress(address);
                model.addAttribute("message", "Address saved successfully.");
            } else if ("edit".equals(actionType)) {
                addressService.editAddress(address);
                model.addAttribute("message", "Address updated successfully.");
            }
        } catch (ValidationException e) {
            model.addAttribute("error", "Validation error: " + e.getMessage());
        } catch (RuntimeException e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "manageAddress";
    }
}
