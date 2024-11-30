package com.cme.controller.EmployeeController;

import com.cme.entity.Employee.EmployeeExperienceDetails;
import com.cme.service.Employee.EmployeeExperienceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employee")
public class ExperienceDetailsController {

    @Autowired
    private EmployeeExperienceInterface experienceDetailsService;

    @GetMapping("/EmployeeExperience")
    public String manageExperienceDetailsPage(@RequestParam(value = "id", required = false) String employeeID, Model model) {
        EmployeeExperienceDetails experienceDetails = (employeeID != null && !employeeID.isEmpty())
                ? experienceDetailsService.findByEmployeeID(employeeID)
                : new EmployeeExperienceDetails();
        model.addAttribute("experienceDetails", experienceDetails);
        return "EmployeeExperience";
    }

    @PostMapping("/EmployeeExperience")
    public String manageExperienceDetails(@ModelAttribute EmployeeExperienceDetails experienceDetails,
                                          @RequestParam("actionType") String actionType,
                                          RedirectAttributes redirectAttributes) {
        try {
            if ("create".equalsIgnoreCase(actionType)) {
                EmployeeExperienceDetails createdExperienceDetails = experienceDetailsService.saveEmployeeExperienceDetails(experienceDetails);
                redirectAttributes.addFlashAttribute("message", "Experience details saved for Employee ID: " + createdExperienceDetails.getEmployeeID());
            } else if ("edit".equalsIgnoreCase(actionType)) {
                boolean isUpdated = experienceDetailsService.updateExperienceDetails(experienceDetails);
                if (isUpdated) {
                    redirectAttributes.addFlashAttribute("message", "Experience details updated for Employee ID: " + experienceDetails.getEmployeeID());
                } else {
                    redirectAttributes.addFlashAttribute("error", "Employee not found for editing.");
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid action type.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error occurred: " + e.getMessage());
        }
        return "redirect:/employee/EmployeeExperience";
    }
}
