package com.cme.service.Admin;

import com.cme.entity.Admin.BatchDetails;
import com.cme.entity.User.UserDetails;
import com.cme.repository.Admin.BatchDetailsRepository;
import com.cme.repository.User.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageBatchService implements ManageBatchInterface {

    @Autowired
    private BatchDetailsRepository batchRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    // Create a new batch
    @Override
    public BatchDetails createBatch(BatchDetails batch) {
        return batchRepository.save(batch);
    }

    // Delete a batch by ID
    @Override
    public boolean deleteBatch(Long batchId) {
        if (batchRepository.existsById(batchId)) {
            batchRepository.deleteById(batchId);
            return true;
        }
        return false;
    }

    // Find batches by name
    @Override
    public List<BatchDetails> findBatchByName(String name) {
        return batchRepository.findBatchByName(name);
    }

    // Update batch details
    @Override
    public BatchDetails updateBatch(BatchDetails batch) {
        if (batchRepository.existsById(batch.getBatchId())) {
            return batchRepository.save(batch);
        } else {
            throw new RuntimeException("Batch not found for editing.");
        }
    }

    // Fetch mentors
    @Override
    public List<UserDetails> getAllMentors() {
        List<UserDetails> mentors = userDetailsRepository.findByUserType("Mentor");
        System.out.println("Fetched Mentors: " + mentors);  // Debugging log
        return mentors;
    }

}
