package com.cme.service.Employee;

import com.cme.entity.Admin.BatchDetails;
import com.cme.repository.Admin.BatchDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchEnrolledService {

    @Autowired
    private BatchDetailsRepository batchDetailsRepository;

    // Enroll an employee in a batch (increment the enrolled count)
    public String enrollInBatch(Long batchId) {
        // Fetch the batch by its ID
        BatchDetails batch = batchDetailsRepository.findById(batchId).orElse(null);

        // If the batch exists and there is space to enroll
        if (batch != null) {
            if (batch.getEnrolled() < batch.getMaxStrength()) {
                batch.setEnrolled(batch.getEnrolled() + 1);
                batchDetailsRepository.save(batch); // Save the updated batch
                return "Successfully enrolled in the batch.";
            } else {
                return "Batch enrollment failed. Maximum capacity reached.";
            }
        }
        return "Batch not found.";
    }
}
