package com.cme.service.User;

public interface SetPasswordInterface {

    void sendResetPasswordEmail(String email); // Send reset password email

    boolean checkUserExistence(String email); // Check if user exists by email

    String resetPassword(String password, String confirmPassword, String email); // Reset password
}
