package com.cme.controller.AdminController;

import com.cme.entity.Admin.MentorDetails;
import com.cme.service.Admin.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MentorDetailsController {

    @Autowired
    private MentorService mentorService;

    // Display the "Manage Mentor" page (create, edit, and delete form)
    @GetMapping("/admin/manageMentor")
    public String showManageMentorForm(Model model) {
        model.addAttribute("mentor", new MentorDetails());
        return "manageMentor"; // Return to the same page for all mentor management actions
    }

    // Handle the form submission and create, edit, or delete a mentor based on the action type
    @PostMapping("/admin/manageMentor")
    public String manageMentor(@ModelAttribute MentorDetails mentor,
                               @RequestParam String actionType,
                               @RequestParam(required = false) Long mentorId,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        try {
            switch (actionType) {
                case "create":
                    // Create a new mentor
                    mentorService.createMentor(mentor);
                    redirectAttributes.addFlashAttribute("message", "Mentor created successfully");
                    break;

                case "edit":
                    // Edit an existing mentor
                    if (mentorId != null) {
                        mentor.setMentorID(mentorId); // Set mentor ID for editing
                        try {
                            mentorService.updateMentor(mentor);
                            redirectAttributes.addFlashAttribute("message", "Mentor updated successfully");
                        } catch (RuntimeException e) {
                            redirectAttributes.addFlashAttribute("error", e.getMessage());
                        }
                    } else {
                        redirectAttributes.addFlashAttribute("error", "Mentor ID is required for editing");
                    }
                    break;

                case "delete":
                    // Delete a mentor
                    if (mentorId != null) {
                        boolean mentorDeleted = mentorService.deleteMentorById(mentorId);
                        if (mentorDeleted) {
                            redirectAttributes.addFlashAttribute("message", "Mentor deleted successfully");
                        } else {
                            redirectAttributes.addFlashAttribute("error", "Mentor does not exist");
                        }
                    } else {
                        redirectAttributes.addFlashAttribute("error", "Mentor ID is required for deletion");
                    }
                    break;

                default:
                    redirectAttributes.addFlashAttribute("error", "Invalid action type");
                    break;
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error processing mentor action. Please try again.");
        }
        return "redirect:/admin/manageMentor"; // Redirect to the ManageMentor page with status messages
    }
}
