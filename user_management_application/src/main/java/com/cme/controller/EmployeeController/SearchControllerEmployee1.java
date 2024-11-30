package com.cme.controller.EmployeeController;

import com.cme.entity.User.UserDetails;
import com.cme.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchControllerEmployee1 {

    @Autowired
    private UserService userService; // Injecting UserManagementService

    // Search employees by name
    @GetMapping("/employee/search")
    public String searchEmployees(@RequestParam(value = "name", required = false) String name, Model model) {
        // Validate input
        if (name == null || name.trim().isEmpty()) {
            model.addAttribute("message", "Please enter a valid name to search.");
            return "searchResultsEmployee"; // Display page with validation message
        }

        // Trim the input and search for users
        name = name.trim();
        List<UserDetails> userList = userService.searchUsersByName(name);

        // Check if users are found and populate the model
        if (userList == null || userList.isEmpty()) {
            model.addAttribute("message", "No users found with the name: " + name);
        } else {
            model.addAttribute("users", userList);
        }

        return "searchResultsEmployee"; // Return the view name
    }
}