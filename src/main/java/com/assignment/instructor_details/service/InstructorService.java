package com.assignment.instructor_details.service;

import com.assignment.instructor_details.entity.Instructor;

import java.util.List;

public interface InstructorService {
    public List<Instructor> findAll();

    public Instructor findById(int theId);

    public void save(Instructor theInstructor);

    public void deleteById(int theId);
}
