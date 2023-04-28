package com.assignment.instructor_details.dao;


import com.assignment.instructor_details.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
}
