package com.cme.service.Admin;

import com.cme.entity.Admin.BatchDetails;
import com.cme.entity.User.UserDetails;

import java.util.List;

public interface ManageBatchInterface {

    BatchDetails createBatch(BatchDetails batch); // Create a new batch
    boolean deleteBatch(Long batchId); // Delete a batch by ID
    List<BatchDetails> findBatchByName(String name); // Find batches by name
    BatchDetails updateBatch(BatchDetails batch);
    public List<UserDetails> getAllMentors();// Update batch details
}
