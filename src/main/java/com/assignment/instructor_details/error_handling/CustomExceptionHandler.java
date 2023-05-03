package com.assignment.instructor_details.error_handling;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public String handleCourseNotFoundException(CourseNotFoundException e, Model model) {
        model.addAttribute("errorMsg", e.getMessage());
        return "error";
    }

    @ExceptionHandler(InstructorNotFoundException.class)
    public String handleInstructorNotFoundException(InstructorNotFoundException e, Model model) {
        model.addAttribute("errorMsg", e.getMessage());
        return "error";
    }
}