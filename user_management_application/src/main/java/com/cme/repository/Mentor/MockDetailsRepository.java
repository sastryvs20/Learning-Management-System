package com.cme.repository.Mentor;

import com.cme.entity.Mentor.MockDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockDetailsRepository extends JpaRepository<MockDetails, String> {
    // No need to change anything else here
}
