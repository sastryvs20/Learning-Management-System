package com.cme.service.User;

import com.cme.entity.User.UserDetails;
import com.cme.repository.User.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SetPasswordService implements SetPasswordInterface {

    @Autowired
    private UserDetailsRepository userRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendResetPasswordEmail(String email) {
        UserDetails user = userRepo.findByEmail(email);
        if (user != null) {
            // Construct the reset password URL (directly using the email instead of a token)
            String resetPasswordUrl = "http://localhost:8080/set-password?email=" + email;

            // Send email with reset password link
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset Request");
            message.setText("Click the link below to reset your password:\n" + resetPasswordUrl);
            mailSender.send(message);
        }
    }

    @Override
    public boolean checkUserExistence(String email) {
        UserDetails user = userRepo.findByEmail(email);
        return user != null;
    }

    @Override
    public String resetPassword(String password, String confirmPassword, String email) {
        // Check if the password and confirm password match
        if (!password.equals(confirmPassword)) {
            return "Error: Passwords do not match."; // Return error message if passwords do not match
        }

        // Find the user by email
        UserDetails user = userRepo.findByEmail(email);
        if (user != null) {
            user.setPassword(password); // Set the new password
            userRepo.save(user); // Update the user in the database
            return "Password reset successfully."; // Return success message
        } else {
            return "Error: Email not found."; // Return error message if email does not exist
        }
    }
}
