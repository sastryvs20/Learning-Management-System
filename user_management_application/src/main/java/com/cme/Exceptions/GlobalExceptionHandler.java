package com.cme.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles generic exceptions.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex, Model model) {
        // Log the exception (use a logger in production)
        model.addAttribute("error", "An unexpected error occurred: " + ex.getMessage());
        return "errorPage";  // Return the error page view
    }

    /**
     * Handles runtime exceptions.
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        // Log the exception (use a logger in production)
        model.addAttribute("error", "A runtime error occurred: " + ex.getMessage());
        return "errorPage";  // Return the error page view
    }

    /**
     * Handles validation exceptions for invalid method arguments.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException ex, Model model) {
        StringBuilder errorMessages = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error ->
                errorMessages.append(error.getDefaultMessage()).append("<br/>")
        );
        model.addAttribute("error", "Validation failed: " + errorMessages.toString());
        return "errorPage";  // Return the error page view
    }

    /**
     * Handles constraint violation exceptions for bean validation errors.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex, Model model) {
        StringBuilder errorMessages = new StringBuilder();
        ex.getConstraintViolations().forEach(violation ->
                errorMessages.append(violation.getMessage()).append("<br/>")
        );
        model.addAttribute("error", "Constraint violation: " + errorMessages.toString());
        return "errorPage";  // Return the error page view
    }

    /**
     * Handles resource not found exceptions (e.g., 404).
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("error", "Resource not found: " + ex.getMessage());
        return "errorPage";  // Return the error page view
    }

    /**
     * Handles access denied exceptions (e.g., 403).
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) {
        model.addAttribute("error", "Access denied: " + ex.getMessage());
        return "errorPage";  // Return the error page view
    }

    /**
     * Handles invalid input or bad request exceptions.
     */
    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidInputException(InvalidInputException ex, Model model) {
        model.addAttribute("error", "Invalid input: " + ex.getMessage());
        return "errorPage";  // Return the error page view
    }
}
