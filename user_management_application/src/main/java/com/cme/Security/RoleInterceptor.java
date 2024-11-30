package com.cme.Security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userType = (String) request.getSession().getAttribute("userType");  // Get userType from session

        // If userType is missing, redirect to login page with an error message
        if (userType == null) {
            response.sendRedirect("/sign-in?error=missingRole");
            return false;
        }

        // Get the requested URL path
        String path = request.getRequestURI();

        // Check access rights based on user role and requested path
        if (path.startsWith("/admin") && !"admin".equalsIgnoreCase(userType)) {
            // Set error message in request and forward to unauthorized page
            request.setAttribute("errorMessage", "You are not authorized to access this page.");
            request.getRequestDispatcher("/unauthorized").forward(request, response);
            return false;
        } else if (path.startsWith("/mentor") && !"mentor".equalsIgnoreCase(userType)) {
            // Set error message in request and forward to unauthorized page
            request.setAttribute("errorMessage", "You are not authorized to access this page.");
            request.getRequestDispatcher("/unauthorized").forward(request, response);
            return false;
        } else if (path.startsWith("/employee") && !"employee".equalsIgnoreCase(userType)) {
            // Set error message in request and forward to unauthorized page
            request.setAttribute("errorMessage", "You are not authorized to access this page.");
            request.getRequestDispatcher("/unauthorized").forward(request, response);
            return false;
        }

        // Allow access if userType matches the required role
        return true;
    }
}
