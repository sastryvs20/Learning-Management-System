package com.cme.controller.EmployeeController;

import com.cme.entity.Employee.EmployeeContactInfo;
import com.cme.service.Employee.ContactDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactDetailsController {

    @Autowired
    private ContactDetailsService contactDetailsService;

    @GetMapping("/employee/EmployeeContactInfo")
    public String showContactInfoForm(Model model) {
        model.addAttribute("employeeContactInfo", new EmployeeContactInfo());
        return "EmployeeContactInfo"; // HTML page for the form
    }

    @PostMapping("/employee/EmployeeContactInfo")
    public String createOrUpdateContactDetails(@Valid @ModelAttribute EmployeeContactInfo contactInfo,
                                               BindingResult result,
                                               @RequestParam("action") String action,
                                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Invalid phone number. Ensure it is 10 digits.");
            return "redirect:/employee/EmployeeContactInfo";
        }

        try {
            if ("save".equalsIgnoreCase(action)) {
                EmployeeContactInfo savedContactInfo = contactDetailsService.saveContactDetails(contactInfo);
                redirectAttributes.addFlashAttribute("message",
                        "Contact details saved successfully with Employee ID: " + savedContactInfo.getEmployeeID());
            } else if ("edit".equalsIgnoreCase(action)) {
                String resultMessage = contactDetailsService.editContactDetails(contactInfo);
                redirectAttributes.addFlashAttribute("message", resultMessage);
            }
            return "redirect:/employee/EmployeeContactInfo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error processing contact details. Please try again.");
            return "redirect:/employee/EmployeeContactInfo";
        }
    }
}
