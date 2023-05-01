package com.assignment.instructor_details.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class InstructorTest {

    @Mock
    Course course;

//    Instructor instructor = new Instructor();
    @InjectMocks
    Instructor instructor;

    @Test
    public void testAllArgsConstructor() {
        List<Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(new Course());
        expectedCourses.add(new Course());
        Instructor instructor1 = new Instructor(1, "Joe", "Dane", "joeDane@gmail.com", expectedCourses);
        assertEquals(instructor1.getId(), 1);
        assertEquals(instructor1.getFirstName(), "Joe");
        assertEquals(instructor1.getLastName(), "Dane");
        assertEquals(instructor1.getEmail(), "joeDane@gmail.com");
        assertEquals(instructor1.getCourses(), expectedCourses);
    }

    @Test
    public void testId() {
        int expectedId = 123;
        instructor.setId(expectedId);
        int actualId = instructor.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testFirstName() {
        String expectedFirstName = "John";
        instructor.setFirstName(expectedFirstName);
        String actualFirstName = instructor.getFirstName();
        assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    public void testLastName() {
        String expectedLastName = "Doe";
        instructor.setLastName(expectedLastName);
        String actualLastName = instructor.getLastName();
        assertEquals(expectedLastName, actualLastName);
    }

    @Test
    public void testEmail() {
        String expectedEmail = "johndoe@example.com";
        instructor.setEmail(expectedEmail);
        String actualEmail = instructor.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testCourses() {
        List<Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(new Course());
        expectedCourses.add(new Course());
        instructor.setCourses(expectedCourses);
        List<Course> actualCourses = instructor.getCourses();
        assertNotNull(actualCourses);
        assertTrue(actualCourses.size() == 2);
    }

    @Test
    public void testAdd() {

        Course course = new Course();
        course.setId(1);
        course.setTitle("Test Course");

        instructor.add(course);

        List<Course> courses = instructor.getCourses();
        assertEquals(1, courses.size());
        assertEquals(course, courses.get(0));
    }


}