package com.cme.controller.MentorController;

import com.cme.entity.User.UserDetails;
import com.cme.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchEmployeeController {

    @Autowired
    private UserService userService;

    @GetMapping("/mentor/searchEmployee")
    public String searchEmployees(@RequestParam(value = "employeeName", required = false) String employeeName, Model model) {
        // Validate input
        if (employeeName == null || employeeName.trim().isEmpty()) {
            model.addAttribute("message", "Please enter a valid employee name.");
            return "searchResultsForMentorEmployee"; // Show message and render the same page
        }

        // Trim and fetch employees
        employeeName = employeeName.trim();
        List<UserDetails> userList = userService.searchUsersByName(employeeName);

        // Check results and populate model
        if (userList == null || userList.isEmpty()) {
            model.addAttribute("message", "No employees found with the name: " + employeeName);
        } else {
            model.addAttribute("users", userList);
            model.addAttribute("message", "Search results for: " + employeeName);
        }

        return "searchResultsForMentorEmployee"; // Display results on the same page
    }
}
