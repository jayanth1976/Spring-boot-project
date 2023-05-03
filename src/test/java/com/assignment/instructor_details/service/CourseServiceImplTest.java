package com.assignment.instructor_details.service;

import com.assignment.instructor_details.dao.CourseRepository;
import com.assignment.instructor_details.entity.Course;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

}