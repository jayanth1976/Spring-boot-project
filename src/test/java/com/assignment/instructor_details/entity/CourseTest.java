package com.assignment.instructor_details.entity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    Instructor instructor;

    Course course = new Course();

    @Test
    public void testConstructorWith(){
        Course course1 = new Course(1,"Python Programming",instructor);
        assertNotNull(course1);
    }

    @Test
    public void testId() {
        int id = 1;
        course.setId(id);
        assertEquals(id, course.getId());
    }

    @Test
    public void testTitle() {
        String title = "Java Programming";
        course.setTitle(title);
        assertEquals(title, course.getTitle());
    }

    @Test
    public void testInstructor() {
        Instructor instructor = new Instructor();
        course.setInstructor(instructor);
        assertNotNull(course.getInstructor());
    }
}