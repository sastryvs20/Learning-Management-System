package com.cme.repository.User;

import com.cme.entity.User.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
    boolean existsByEmail(String email);
    UserDetails findByEmail(String email);

    @Query("SELECT u FROM UserDetails u WHERE LOWER(u.first_name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(u.last_name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<UserDetails> findByName(@Param("name") String name);

    @Query("SELECT COUNT(u) > 0 FROM UserDetails u WHERE u.email = :email")
    boolean emailExists(@Param("email") String email);

    UserDetails findByEmployeeId(String employeeId);
    boolean existsByEmployeeId(String employeeId);

    @Query("SELECT u FROM UserDetails u WHERE u.user_type = :userType")
    List<UserDetails> findByUserType(@Param("userType") String userType);

    @Query("SELECT u.email FROM UserDetails u WHERE u.employeeId = :employeeId")
    Optional<String> findEmailByEmployeeId(String employeeId);
}
