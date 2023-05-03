package com.assignment.instructor_details.error_handling;

public class InstructorNotFoundException extends RuntimeException {
        public InstructorNotFoundException(String message) {
            super(message);
        }
    }