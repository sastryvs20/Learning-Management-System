package com.cme.controller.AdminController;

import com.cme.entity.User.UserDetails;
import com.cme.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchControllerAdminForEmployee {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/search")
    public String searchEmployees(@RequestParam("employeeName") String name, Model model) {
        // Use the service to fetch the users by name
        List<UserDetails> userList = userService.searchUsersByName(name);

        if (userList.isEmpty()) {
            model.addAttribute("message", "No employees found with the name: " + name);
        } else {
            model.addAttribute("users", userList);
        }
        return "searchResultsAdmin"; // Ensure this view exists and the table columns align with UserDetails fields
    }
}