package com.assignment.instructor_details.controller;

import com.assignment.instructor_details.entity.Course;
import com.assignment.instructor_details.entity.Instructor;
import com.assignment.instructor_details.service.InstructorService;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class InstructorControllerTest {

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    Model theModel;

    @Mock
    InstructorService instructorService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Instructor instructor;

    @Mock
    Course course;

    @InjectMocks
    InstructorController instructorController;

    @Test
    void findAll() {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor());
        instructors.add(new Instructor());
        when(instructorService.findAll()).thenReturn(instructors);

        String view = instructorController.findAll(theModel);

        verify(theModel).addAttribute("instructors",instructors);
        assertEquals("instructor/instructor-list",view);
    }

    @Test
    void saveInstructorWithoutValidation() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String view = instructorController.saveInstructor(instructor,bindingResult);

        assertEquals("redirect:/instructors/list",view);
    }

    @Test
    void saveInstructorWithValidation() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = instructorController.saveInstructor(instructor,bindingResult);

        assertEquals("instructor/instructor-form",view);
    }

    @Test
    void update() {
        String theId = "7";
        when(instructorService.findById(theId)).thenReturn(instructor);

        String view = instructorController.update(theId,theModel);

        verify(theModel).addAttribute("instructor",instructor);
        assertEquals("instructor/instructor-form",view);

    }

    @Test
    void showFormForAdd() {
        String viewName = instructorController.showFormForAdd(theModel);
        assertEquals("instructor/instructor-form", viewName);
    }

    @Test
    void deleteInstructor() {
        int instructorId = 123;
        doNothing().when(instructorService).deleteById(instructorId);
        String result = instructorController.deleteInstructor(instructorId);
        assertEquals("redirect:/instructors/list", result);
        verify(instructorService, times(1)).deleteById(instructorId);
    }

}