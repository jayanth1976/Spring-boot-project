package com.assignment.instructor_details.dao;

import com.assignment.instructor_details.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CourseRepository extends JpaRepository<Course,Integer> {
}
