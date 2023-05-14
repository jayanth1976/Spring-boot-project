package com.assignment.instructor_details.service;

import com.assignment.instructor_details.dao.InstructorRepository;
import com.assignment.instructor_details.entity.Instructor;
import com.assignment.instructor_details.error_handling.InstructorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService{



    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(String theId) {
        int id;
        try {
            id = Integer.parseInt(theId);
        } catch (NumberFormatException ex) {
            throw new InstructorNotFoundException("Instructor Id cannot be a string.");
        }
        Optional<Instructor> result =  instructorRepository.findById(id);
        Instructor instructor = null;
        if (result.isPresent()) {
            instructor = result.get();
        }
        else {
            throw new InstructorNotFoundException("Cannot found instructor with id - " + id);
        }
        return instructor;
    }

    @Override
    public void save(Instructor theInstructor) {
        instructorRepository.save(theInstructor);
    }

    @Override
    public void deleteById(int theId) {
        instructorRepository.deleteById(theId);
    }
}
