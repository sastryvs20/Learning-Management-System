package com.cme.controller.HomePageController;

import com.cme.service.User.SetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SetPasswordController {

    @Autowired
    private SetPasswordService setPasswordService;

    // Show the set password page (based on email)
    @GetMapping("/set-password")
    public String showSetPasswordPage(@RequestParam("email") String email, Model model) {
        // Fetching the user and passing it to the service for validation
        boolean userExists = setPasswordService.checkUserExistence(email);

        if (!userExists) {
            model.addAttribute("error", "Email not found.");
            return "error-page"; // Redirect to error page if user not found
        }

        model.addAttribute("email", email); // Pass email to the view
        return "set-password"; // View for setting the password
    }

    // Reset password and save the new password for the extracted email
    @PostMapping("/set-password")
    public String resetPassword(@RequestParam("password") String password,
                                @RequestParam("confirmPassword") String confirmPassword,
                                @RequestParam("email") String email, Model model) {
        // Handle password reset through service
        String responseMessage = setPasswordService.resetPassword(password, confirmPassword, email);

        if (responseMessage.startsWith("Error")) {
            model.addAttribute("error", responseMessage);
            model.addAttribute("email", email); // Pass email to the view
            return "set-password"; // Stay on the same page and show error
        }

        model.addAttribute("success", responseMessage);
        return "set-password"; // Stay on the same page and show success message
    }
}
