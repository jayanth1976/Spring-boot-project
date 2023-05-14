package com.assignment.instructor_details.controller;

import com.assignment.instructor_details.entity.Course;
import com.assignment.instructor_details.entity.Instructor;
import com.assignment.instructor_details.service.CourseService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseControllerTest {
    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @Mock
    private Model theModel;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private InstructorService instructorService;

    @Mock
    private CourseService courseService;

    @Mock
    private Instructor instructor;


    Course course = new Course();


    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddCourse() {
        int theId = 1;
        String result = courseController.addCourse(theModel, theId);
        assertEquals("course/course-form", result);
        verify(theModel).addAttribute(eq("course"), any(Course.class));
        verify(theModel).addAttribute("instructorId", theId);
    }

    @Test
    void testSaveCourseWithoutValidation(){
        String theId = "7";
        when(bindingResult.hasErrors()).thenReturn(false);
        when(instructorService.findById(theId)).thenReturn(instructor);

        String result = courseController.saveCourse(course, bindingResult, theId,theModel);

        assertEquals("redirect:/courses/allCourses",result);
        verify(instructor).add(course);
        verify(courseService).save(course);

    }

    @Test
    void testSaveCourseWithValidation(){
        String theId = "7";
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = courseController.saveCourse(course, bindingResult, theId,theModel);

        assertEquals("course/course-form",result);
        verify(instructor,never()).add(course);
        verify(courseService,never()).save(course);
    }

    @Test
    void testInstructorCourseList(){
        String theId = "1";
        when(instructorService.findById(theId)).thenReturn(instructor);
        String result = courseController.instructorCourseList(theModel,theId);

        Course course1 = new Course();
        Course course2 = new Course();
        instructor.add(course1);
        instructor.add(course2);

        verify(theModel).addAttribute("courses",instructor.getCourses());
        verify(theModel).addAttribute("name",instructor.getFirstName()+" "+instructor.getLastName());
        assertEquals("course/instructor-course-list", result);
    }

    @Test
    void testGetAllCourses(){
        List<Course> theCourses = new ArrayList<>();
        theCourses.add(new Course());
        theCourses.add(new Course());
        when(courseService.findAll()).thenReturn(theCourses);

        String view = courseController.getAllCourses(theModel);

        verify(theModel).addAttribute("allCourses",theCourses);
        assertEquals("course/all-courses",view);
    }

    @Test
    void testUpdate(){
        int courseId = 1;
        int instructorId = 2;
        Course course = new Course();
        course.setId(courseId);
        Instructor instructor = new Instructor();
        instructor.setId(instructorId);
        course.setInstructor(instructor);
        when(courseService.findById("1")).thenReturn(course);

        String view = courseController.update("1", theModel);

        verify(courseService).findById("1");
        verify(theModel).addAttribute("course", course);
        verify(theModel).addAttribute("instructorId", instructorId);
        assertEquals("course/course-form", view);
    }

    @Test
    void testDeleteCourse() {
        int courseId = 1;

        String view = courseController.deleteCourse(courseId);

        verify(courseService, times(1)).deleteById(courseId);
        assertEquals("redirect:/courses/allCourses", view);
    }


}