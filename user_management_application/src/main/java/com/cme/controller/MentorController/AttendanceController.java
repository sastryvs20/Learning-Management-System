package com.cme.controller.MentorController;

import com.cme.entity.Mentor.Attendance;
import com.cme.entity.User.UserDetails;
import com.cme.service.Mentor.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("mentor/attendance")
    public String showAttendancePage(Model model) {
        // Fetch employees from the service
        List<UserDetails> employees = attendanceService.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("date", LocalDate.now().toString()); // Current date
        return "attendance"; // Return the attendance page
    }

    @PostMapping("mentor/attendance/mark")
    public String markAttendance(Model model, @RequestParam List<String> attendanceStatus, @RequestParam List<String> employeeIds) {
        // Iterate over employeeIds and map them to Attendance records
        LocalDate date = LocalDate.now();
        List<Attendance> attendanceList = new ArrayList<>();

        for (int i = 0; i < employeeIds.size(); i++) {
            Attendance attendance = new Attendance();

            // Fetch employee details using the employeeId
            UserDetails employee = attendanceService.getEmployeeByID(employeeIds.get(i));

            // Set the attendance details
            attendance.setEmployeeId(employeeIds.get(i));  // Use String as employeeId
            attendance.setEmployeeName(employee.getFirst_name() + " " + employee.getLast_name());  // Set employee's name
            attendance.setStatus(attendanceStatus.get(i));  // Set the status
            attendance.setDate(date); // Set the current date for attendance

            attendanceList.add(attendance);
        }

        // Save attendance records
        attendanceService.markAttendance(attendanceList);

        // Show a success message
        model.addAttribute("message", "Attendance marked successfully!");
        // Return the attendance page with the message
        return "attendance";
    }
}
