package com.assignment.instructor_details.service;

import com.assignment.instructor_details.entity.Course;


import java.util.List;

public interface CourseService {
    public List<Course> findAll();
//
//    public Instructor findById(int theId);

    public void save(Course theCourse);

    public void deleteById(int theId);
}
