package com.cme.controller.HomePageController;

import com.cme.entity.User.UserDetails;
import com.cme.service.User.UserInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserInterface userService;

    // Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDetails());
        return "register";
    }

    // Handle registration
    @PostMapping("/register")
    public String userRegistration(@ModelAttribute("user") UserDetails user, Model model) {
        Map<String, Object> registrationResult = userService.registerUser(user);
        model.addAttribute("message", registrationResult.get("message"));
        model.addAttribute("status", registrationResult.get("status"));

        if ("error".equals(registrationResult.get("status"))) {
            return "register";
        }

        return "register";
    }

    // Show login form with error/message handling
    @GetMapping("/sign-in")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "message", required = false) String message,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid credentials. Please try again.");
        }
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String login(@RequestParam("employeeId") String employeeId,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes,
                        HttpServletRequest request,  // Access the request object to set session
                        Model model) {

        try {
            // Validate user credentials
            var validationResponse = userService.validateUser(employeeId, password);

            if ("success".equals(validationResponse.get("status"))) {
                // Set user role (userType) in session
                request.getSession().setAttribute("userType", validationResponse.get("userType"));  // Store role in session

                // Redirect to the appropriate page based on role
                String redirectUrl = validationResponse.get("redirectUrl");
                redirectAttributes.addFlashAttribute("userName", validationResponse.get("firstName"));
                return "redirect:" + redirectUrl;
            } else {
                model.addAttribute("error", validationResponse.get("message"));
                return "sign-in";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred. Please try again.");
            return "sign-in";
        }
    }

    // Admin Page
    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("userName", "Admin");
        return "admin";
    }

    // Mentor Page
    @GetMapping("/mentor")
    public String showMentorPage(Model model) {
        model.addAttribute("userName", "Mentor");
        return "mentor";
    }

    // Employee Page
    @GetMapping("/employee")
    public String showEmployeePage(Model model) {
        model.addAttribute("userName", "Employee");
        return "employee";
    }
}
