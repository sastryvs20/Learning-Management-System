package com.cme.controller.MentorController;

import com.cme.entity.Admin.BatchDetails;
import com.cme.service.Admin.ManageBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchBatchController {

    @Autowired
    private ManageBatchService batchService;

    @GetMapping("/mentor/searchBatch")
    public String searchBatches(@RequestParam("batchName") String batchName, Model model) {
        try {
            List<BatchDetails> batchList = batchService.findBatchByName(batchName);
            if (batchList.isEmpty()) {
                model.addAttribute("message", "No batches found with the name: " + batchName);
            } else {
                model.addAttribute("batches", batchList);
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while searching for batches. Please try again.");
        }
        return "searchResultsforMentorBatch"; // Ensure this view is correctly implemented
    }
}
