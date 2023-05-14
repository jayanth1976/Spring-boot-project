package com.assignment.instructor_details.service;

import com.assignment.instructor_details.dao.CourseRepository;
import com.assignment.instructor_details.entity.Course;
import com.assignment.instructor_details.error_handling.CourseNotFoundException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {
    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @Test
    void testFindAll() {
        Course course1 = new Course();
        Course course2 = new Course();
        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseServiceImpl.findAll();

        verify(courseRepository).findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(course1));
        assertTrue(result.contains(course2));
    }

    @Test
    void testSaveCourse() {
        Course theCourse = new Course();
        courseServiceImpl.save(theCourse);
        verify(courseRepository).save(theCourse);
    }

    @Test
    void testDeleteById() {
        int courseId = 1;

        courseServiceImpl.deleteById(courseId);

        verify(courseRepository, times(1)).deleteById(courseId);
    }
    @Test
    void testFindById() {
        Course course = new Course();
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        Course result = courseServiceImpl.findById("1");

        verify(courseRepository).findById(1);
        assertEquals(course, result);
    }

    @Test
    void testFindByIdThrowsException() {
        when(courseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            courseServiceImpl.findById("1");
        });

        verify(courseRepository).findById(1);
    }
    @Test
    void testFindById_WhenIdIsString_ThrowsCourseNotFoundException() {
        String id = "abc";

        assertThrows(CourseNotFoundException.class, () -> {
            courseServiceImpl.findById(id);
        }, "Course Id cannot be string.");
    }



}