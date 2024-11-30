package com.cme.controller.AdminController;

import com.cme.entity.Admin.BatchDetails;
import com.cme.entity.User.UserDetails;
import com.cme.service.Admin.ManageBatchService;  // Import the consolidated service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ManageBatchController {

    @Autowired
    private ManageBatchService manageBatchService;  // Inject the consolidated service

    // Render Manage Batch Page
    @GetMapping("/admin/manageBatch")
    public String manageBatchPage(Model model) {
        // Fetch list of mentors
        List<UserDetails> mentors = manageBatchService.getAllMentors();  // Fetch mentors using the new service
        model.addAttribute("mentors", mentors); // Add mentors to the model
        model.addAttribute("batch", new BatchDetails()); // For form-binding
        return "manageBatch"; // The Thymeleaf template for the manage batch page
    }

    // Handle Create, Edit, and Delete Actions (using actionType parameter)
    @PostMapping("/admin/manageBatch")
    public String manageBatch(@ModelAttribute BatchDetails batch,
                              @RequestParam("actionType") String actionType,
                              RedirectAttributes redirectAttributes) {
        try {
            if ("create".equalsIgnoreCase(actionType)) {
                // Create Batch
                BatchDetails createdBatch = manageBatchService.createBatch(batch);
                redirectAttributes.addFlashAttribute("message", "Batch created successfully with ID: " + createdBatch.getBatchId());
            } else if ("edit".equalsIgnoreCase(actionType)) {
                // Edit Batch
                BatchDetails updatedBatch = manageBatchService.updateBatch(batch);
                redirectAttributes.addFlashAttribute("message", "Batch updated successfully with ID: " + updatedBatch.getBatchId());
            } else if ("delete".equalsIgnoreCase(actionType)) {
                // Delete Batch
                boolean isDeleted = manageBatchService.deleteBatch(batch.getBatchId());
                if (isDeleted) {
                    redirectAttributes.addFlashAttribute("message", "Batch deleted successfully with ID: " + batch.getBatchId());
                } else {
                    redirectAttributes.addFlashAttribute("error", "Batch not found with ID: " + batch.getBatchId());
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid action type.");
            }
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred. Please try again.");
        }
        return "redirect:/admin/manageBatch"; // Stay on the same page after action
    }
}
