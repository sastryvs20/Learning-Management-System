package com.cme.service.Mentor;

import com.cme.entity.Mentor.Attendance;
import com.cme.entity.User.UserDetails;
import com.cme.repository.Mentor.AttendanceRepository;
import com.cme.repository.User.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Fetch all employees by user type
    public List<UserDetails> getAllEmployees() {
        return userDetailsRepository.findByUserType("Employee"); // Returns list of employees
    }

    // Fetch an employee by their ID
    public UserDetails getEmployeeByID(String employeeId) {
        return userDetailsRepository.findByEmployeeId(employeeId); // Assuming a method in your UserDetailsRepository
    }

    // Mark attendance for the employees
    public void markAttendance(List<Attendance> attendanceList) {
        LocalDate date = LocalDate.now(); // Set current date
        for (Attendance attendance : attendanceList) {
            attendance.setDate(date); // Set the date for each attendance record
            attendanceRepository.save(attendance); // Save the attendance record
        }
    }
}

