package com.assignment.instructor_details.error_handling;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CustomExceptionHandlerTest {

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private Model model;

    @Test
    void testHandleCourseNotFoundException() {
        CourseNotFoundException exception = new CourseNotFoundException("Course not found");

        String result = new CustomExceptionHandler().handleCourseNotFoundException(exception, model);

        assertEquals("error", result);
        verify(model).addAttribute("errorMsg", "Course not found");
    }

    @Test
    void testHandleInstructorNotFoundException() {
        InstructorNotFoundException exception = new InstructorNotFoundException("Instructor not found");

        String result = new CustomExceptionHandler().handleInstructorNotFoundException(exception, model);

        assertEquals("error", result);
        verify(model).addAttribute("errorMsg", "Instructor not found");
    }

}