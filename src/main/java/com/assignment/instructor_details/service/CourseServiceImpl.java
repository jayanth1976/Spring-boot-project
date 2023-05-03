package com.assignment.instructor_details.service;

import com.assignment.instructor_details.dao.CourseRepository;
import com.assignment.instructor_details.entity.Course;
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

    public static class CourseNotFoundException extends RuntimeException {
        public CourseNotFoundException(String message) {
            super(message);
        }
    }

    @Override
    public Course findById(int theId) {
        Optional<Course> result =  courseRepository.findById(theId);
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
