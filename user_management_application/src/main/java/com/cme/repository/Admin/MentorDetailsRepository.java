package com.cme.repository.Admin;

import com.cme.entity.Admin.MentorDetails;
import com.cme.entity.User.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MentorDetailsRepository extends JpaRepository<MentorDetails,Long> {
    @Query("SELECT u FROM UserDetails u WHERE LOWER(u.first_name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(u.last_name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<UserDetails> findByName(@Param("name") String name);
}
