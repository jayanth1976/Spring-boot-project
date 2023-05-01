package com.assignment.instructor_details.controller;

import com.assignment.instructor_details.entity.Course;
import com.assignment.instructor_details.entity.Instructor;
import com.assignment.instructor_details.service.CourseService;
import com.assignment.instructor_details.service.InstructorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CourseControllerTest {

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

    @Test
    public void testAddCourse() {
        int theId = 1;
        System.out.println(theModel);
        String result = courseController.addCourse(theModel, theId);
        assertEquals("course/course-form", result);
        verify(theModel).addAttribute(eq("course"), any(Course.class));
        verify(theModel).addAttribute(eq("instructorId"), eq(theId));
    }

    @Test
    public void testSaveCourseWithoutErrors(){
        int theId = 7;
        when(bindingResult.hasErrors()).thenReturn(false);
        when(instructorService.findById(eq(theId))).thenReturn(instructor);

        String result = courseController.saveCourse(course, bindingResult, theId);

        assertEquals("redirect:/courses/allCourses",result);
        verify(instructor).add(eq(course));
        verify(courseService).save(course);

    }

    @Test
    public void testSaveCourseWithErrors(){
        int theId = 7;
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = courseController.saveCourse(course, bindingResult, theId);

        assertEquals("redirect:/courses/add?instructorId="+theId,result);
        verify(instructor,never()).add(eq(course));
        verify(courseService,never()).save(course);
    }

    @Test
    public void testInstructorCourseList(){
        int theId = 1;
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
    public void testGetAllCourses(){
        List<Course> theCourses = new ArrayList<>();
        theCourses.add(new Course());
        theCourses.add(new Course());
        when(courseService.findAll()).thenReturn(theCourses);

        String view = courseController.getAllCourses(theModel);

        verify(theModel).addAttribute("allCourses",theCourses);
        assertEquals("course/all-courses",view);
    }


}