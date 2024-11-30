package com.cme.service.User;

import com.cme.entity.User.UserDetails;
import com.cme.repository.User.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserDetailsRepository userRepo;

    @Autowired
    private SetPasswordService setPasswordService;

    @Override
    public Map<String, Object> registerUser(UserDetails user) {
        Map<String, Object> response = new HashMap<>();

        // Check if email exists using custom query
        if (userRepo.emailExists(user.getEmail())) {
            response.put("status", "error");
            response.put("message", "Email already exists!");
            return response;
        }

        try {
            // Ensure the user has a valid user type before saving
            if (user.getUser_type() == null || user.getUser_type().isEmpty()) {
                response.put("status", "error");
                response.put("message", "User type is required.");
                return response;
            }

            // Save the user, which triggers employee ID generation
            userRepo.save(user);
            response.put("status", "success");
            response.put("message", "User registered successfully with ID: " + user.getEmployeeId() + ". Check your mail to set your password.");
            setPasswordService.sendResetPasswordEmail(user.getEmail());
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Registration failed. Please try again.");
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Map<String, String> validateUser(String employeeId, String password) {
        Map<String, String> response = new HashMap<>();

        // Check if employeeId exists
        if (!userRepo.existsByEmployeeId(employeeId)) {
            response.put("status", "error");
            response.put("message", "Employee ID does not exist.");
            return response;
        }

        // Retrieve user by employeeId
        UserDetails user = userRepo.findByEmployeeId(employeeId);

        // Check if the password matches
        if (!user.getPassword().equals(password)) {
            response.put("status", "error");
            response.put("message", "Incorrect credentials.");
            return response;
        }

        response.put("status", "success");
        response.put("message", "Login successful.");
        response.put("userType", user.getUser_type());
        response.put("firstName", user.getFirst_name());
        response.put("redirectUrl", getRedirectUrl(user.getUser_type()));
        return response;
    }

    @Override
    public String redirectUserByEmail(String email) {
        UserDetails user = userRepo.findByEmail(email);
        return (user == null) ? "redirect:/sign-in" : getRedirectUrl(user.getUser_type());
    }

    @Override
    public List<UserDetails> searchUsersByName(String name) {
        return userRepo.findByName(name);
    }

    private String getRedirectUrl(String userType) {
        if (userType == null) return "redirect:/sign-in";

        switch (userType.toLowerCase()) {
            case "admin":
                return "/admin";
            case "mentor":
                return "/mentor";
            case "employee":
                return "/employee";
            default:
                return "/sign-in";
        }
    }
}
