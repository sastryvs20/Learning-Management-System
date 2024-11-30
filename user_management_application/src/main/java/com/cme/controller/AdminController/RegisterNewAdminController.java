package com.cme.controller.AdminController;

import com.cme.entity.User.UserDetails;
import com.cme.service.User.SetPasswordService;
import com.cme.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegisterNewAdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private SetPasswordService setPasswordService; // Injecting the SetPasswordService to send reset password email

    // Displays the registration page for Admin
    @GetMapping("admin/AdminRegister")
    public String adminRegistration() {
        return "AdminRegister"; // Returns the registration page view
    }

    // Handles the registration of a new admin
    @PostMapping("admin/AdminRegister")
    public String userRegistration(@ModelAttribute UserDetails user, Model model) {
        // Call the registration service to register the user
        Map<String, Object> response = userService.registerUser(user);

        if ("success".equals(response.get("status"))) {
            // Registration successful, now send the reset password email
            setPasswordService.sendResetPasswordEmail(user.getEmail()); // Send password reset email

            // Registration successful, show success message
            model.addAttribute("success", response.get("message"));
        } else {
            // Registration failed (e.g., email already exists or other issues)
            model.addAttribute("error", response.get("message"));
            return "AdminRegister"; // Stay on the registration page in case of error
        }

        return "AdminRegister"; // Stay on the registration page after successful registration
    }
}
