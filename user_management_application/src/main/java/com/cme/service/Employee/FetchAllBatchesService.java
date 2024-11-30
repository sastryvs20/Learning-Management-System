package com.cme.service.Employee;

import com.cme.entity.Admin.BatchDetails;
import com.cme.repository.Admin.BatchDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchAllBatchesService {

    @Autowired
    private BatchDetailsRepository batchDetailsRepository;

    // Fetch all batches with enrolled <= 60
    public List<BatchDetails> getAvailableBatches() {
        return batchDetailsRepository.findByEnrolledLessThanEqual(60);
    }
}
