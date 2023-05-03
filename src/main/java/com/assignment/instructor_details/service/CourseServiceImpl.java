package com.assignment.instructor_details.service;

import com.assignment.instructor_details.dao.CourseRepository;
import com.assignment.instructor_details.entity.Course;
import com.assignment.instructor_details.error_handling.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(String theId) {
        int id;
        try {
            id = Integer.parseInt(theId);
        } catch (NumberFormatException ex) {
            throw new CourseNotFoundException("Course Id cannot be string.");
        }
        Optional<Course> result =  courseRepository.findById(id);
        Course course = null;
        if (result.isPresent()) {
            course = result.get();
        }
        else {
            throw new CourseNotFoundException("Cannot find course with id - " + theId);
        }
        return course;
    }

    @Override
    public void save(Course theCourse) {
        courseRepository.save(theCourse);
    }

    @Override
    public void deleteById(int theId) {
        courseRepository.deleteById(theId);
    }
}
