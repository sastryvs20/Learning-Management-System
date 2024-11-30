package com.cme.controller.AdminController;

import com.cme.entity.Admin.BatchDetails;
import com.cme.service.Admin.ManageBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchControllerForAdminBatch {

    @Autowired
    private ManageBatchService batchService;

    // Updated the URL mapping and parameter name
    @GetMapping("/admin/searchBatch")
    public String searchBatchByName(@RequestParam(name = "batchName") String batchName, Model model) {
        // Fetch batches by name from the service
        List<BatchDetails> batches = batchService.findBatchByName(batchName);

        // Add a message if no batches are found
        if (batches.isEmpty()) {
            model.addAttribute("message", "No batches found for the given name.");
        } else {
            model.addAttribute("batches", batches);
        }

        // Return the view to display the results
        return "searchResultsAdminBatch";  // Ensure this is the correct name of the Thymeleaf template
    }
}
