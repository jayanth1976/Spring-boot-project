package com.assignment.instructor_details.service;

import com.assignment.instructor_details.dao.CourseRepository;
import com.assignment.instructor_details.dao.InstructorRepository;
import com.assignment.instructor_details.entity.Course;
import com.assignment.instructor_details.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void save(Course theCourse) {
        courseRepository.save(theCourse);
    }


}
