package com.cme.controller.EmployeeController;

import com.cme.entity.Admin.BatchDetails;
import com.cme.service.Employee.BatchEnrolledService;
import com.cme.service.Employee.FetchAllBatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BatchEnrolledController {

    @Autowired
    private BatchEnrolledService batchEnrolledService;

    @Autowired
    private FetchAllBatchesService fetchAllBatchesService;

    // Display batches with enrolled students <= 60
    @GetMapping("/employee/BatchEnroll")
    public String showAvailableBatches(Model model) {
        // Fetch available batches from the FetchAllBatchesService
        List<BatchDetails> batches = fetchAllBatchesService.getAvailableBatches();
        model.addAttribute("batches", batches);
        return "BatchEnroll"; // Return the view name
    }

    // Enroll in a batch and update the enrolled count
    @PostMapping("/employee/BatchEnroll")
    public String enrollInBatch(@RequestParam("batchId") Long batchId, Model model) {
        // Enroll the employee using the service, the logic is handled in the service layer
        String message = batchEnrolledService.enrollInBatch(batchId);
        model.addAttribute("message", message);

        // Fetch updated batches and display them
        List<BatchDetails> batches = fetchAllBatchesService.getAvailableBatches();
        model.addAttribute("batches", batches);
        return "BatchEnroll"; // Redirect back to the 'BatchEnroll' page with updated data
    }
}
