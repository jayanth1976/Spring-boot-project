package com.assignment.instructor_details.error_handling;

public class CourseNotFoundException extends RuntimeException {
        public CourseNotFoundException(String message) {
            super(message);
        }
}