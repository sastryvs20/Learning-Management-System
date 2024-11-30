package com.cme.repository.Mentor;

import com.cme.entity.Mentor.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Find attendance records by date
    List<Attendance> findByDate(LocalDate date);

}
