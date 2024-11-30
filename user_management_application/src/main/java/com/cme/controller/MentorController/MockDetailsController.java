package com.cme.controller.MentorController;

import com.cme.entity.Mentor.MockDetails;
import com.cme.service.Mentor.MockDetailsService;
import com.cme.repository.User.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class MockDetailsController {

    private final MockDetailsService mockDetailsService;
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public MockDetailsController(MockDetailsService mockDetailsService, UserDetailsRepository userDetailsRepository) {
        this.mockDetailsService = mockDetailsService;
        this.userDetailsRepository = userDetailsRepository;
    }

    // Render the page for creating/editing mock details
    @GetMapping("/mentor/MockDetails")
    public String showMockDetailsForm(@RequestParam(required = false) String employeeID, Model model) {
        if (employeeID != null) {
            MockDetails mockDetails = mockDetailsService.getMockDetailsByEmployeeID(employeeID).orElse(null);
            if (mockDetails != null) {
                model.addAttribute("mockDetails", mockDetails);
            } else {
                model.addAttribute("error", "No mock details found for the provided Employee ID.");
            }
        } else {
            model.addAttribute("mockDetails", new MockDetails());
        }
        model.addAttribute("actionType", employeeID != null ? "edit" : "create");
        return "MockDetails";
    }

    // Handle the create or edit action
    @PostMapping("/mentor/MockDetails")
    public String createOrUpdateMockDetails(@Valid @ModelAttribute MockDetails mockDetails,
                                            @RequestParam("actionType") String actionType,
                                            BindingResult bindingResult,
                                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Validation failed. Please correct the errors in the form.");
            return "redirect:/mentor/MockDetails";
        }

        try {
            // Use the service method that handles both create and edit based on action type
            MockDetails savedMockDetails = mockDetailsService.saveOrUpdateMockDetails(mockDetails, actionType);
            if ("create".equalsIgnoreCase(actionType)) {
                redirectAttributes.addFlashAttribute("message", "Mock details created successfully for Employee ID: " + savedMockDetails.getEmployeeID());
            } else if ("edit".equalsIgnoreCase(actionType)) {
                redirectAttributes.addFlashAttribute("message", "Mock details updated successfully for Employee ID: " + savedMockDetails.getEmployeeID());
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid action type.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while processing mock details. Please try again.");
        }
        return "redirect:/mentor/MockDetails";
    }

    // Delete Mock Details
    @PostMapping("/mentor/deleteMockDetails")
    public String deleteMockDetails(@RequestParam String employeeID, RedirectAttributes redirectAttributes) {
        try {
            mockDetailsService.deleteMockDetails(employeeID);
            redirectAttributes.addFlashAttribute("message", "Mock details deleted successfully for Employee ID: " + employeeID);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting mock details. Employee not found.");
        }
        return "redirect:/mentor/MockDetails";
    }
}
