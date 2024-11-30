package com.cme.repository.Admin;
import com.cme.entity.Admin.BatchDetails;
import com.cme.entity.User.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatchDetailsRepository extends JpaRepository<BatchDetails,Long>{
    @Query("SELECT b FROM BatchDetails b WHERE LOWER(b.batchName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<BatchDetails> findBatchByName(String name);
    List<BatchDetails> findByEnrolledLessThanEqual(int enrolled);

}
