package com.cme.controller.EmployeeController;

import com.cme.entity.Employee.EmployeeBankDetails;
import com.cme.service.Employee.EmployeeBankDetailsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ManageBankDetails {

    @Autowired
    private EmployeeBankDetailsInterface bankDetailsService;

    // Render Bank Details Page
    @GetMapping("/employee/BankDetails")
    public String manageBankDetailsPage(Model model) {
        model.addAttribute("bankDetails", new EmployeeBankDetails()); // For form-binding
        return "BankDetails"; // Thymeleaf template for the manage bank details page
    }

    // Handle Create and Edit Actions (using actionType parameter)
    @PostMapping("/employee/BankDetails")
    public String manageBankDetails(@ModelAttribute EmployeeBankDetails bankDetails,
                                    @RequestParam("actionType") String actionType,
                                    RedirectAttributes redirectAttributes) {
        try {
            if ("create".equalsIgnoreCase(actionType)) {
                // Create Bank Details
                EmployeeBankDetails createdBankDetails = bankDetailsService.saveEmployeeBankDetails(bankDetails);
                redirectAttributes.addFlashAttribute("message", "Bank details saved successfully for Account: " + createdBankDetails.getAccountNumber());
            } else if ("edit".equalsIgnoreCase(actionType)) {
                // Edit Bank Details
                boolean isUpdated = bankDetailsService.updateBankDetails(bankDetails);
                if (isUpdated) {
                    redirectAttributes.addFlashAttribute("message", "Bank details updated successfully for Account: " + bankDetails.getAccountNumber());
                } else {
                    redirectAttributes.addFlashAttribute("error", "Bank account not found for editing.");
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid action type.");
            }
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while processing bank details. Please try again.");
        }
        return "redirect:/employee/BankDetails"; // Stay on the same page after action
    }
}
