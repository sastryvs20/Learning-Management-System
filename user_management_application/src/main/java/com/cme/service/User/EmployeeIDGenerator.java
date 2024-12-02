package com.cme.service.User;

import org.hibernate.HibernateException;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import com.cme.entity.User.UserDetails;

import java.io.Serializable;

public class EmployeeIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // Cast the object to UserDetails to access the user_type
        UserDetails user = (UserDetails) object;

        // Ensure the user_type is valid before generating the ID
        if (user.getUser_type() == null || user.getUser_type().isEmpty()) {
            throw new HibernateException("User type is required for ID generation.");
        }

        // Get the prefix based on the user_type
        String prefix = getPrefixByUserType(user.getUser_type());

        // Generate the ID using the prefix and the current time in milliseconds
        return prefix + System.currentTimeMillis();  // Example ID generation strategy
    }

    // Helper method to return the correct prefix based on user_type
    private String getPrefixByUserType(String userType) {
        switch (userType.toUpperCase()) {
            case "EMPLOYEE":
                return "EM";  // Employee
            case "ADMIN":
                return "AD";  // Admin
            case "MENTOR":
                return "ME";  // Mentor
            default:
                return "UN";  // Default if the user type doesn't match any of the cases
        }
    }
}