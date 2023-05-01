package com.assignment.instructor_details.service;

import com.assignment.instructor_details.dao.InstructorRepository;
import com.assignment.instructor_details.entity.Instructor;
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
    public Instructor findById(int theId) {
        Optional<Instructor> result =  instructorRepository.findById(theId);
        Instructor instructor = null;
        if (result.isPresent()) {
            instructor = result.get();
        }
        else {
            throw new RuntimeException("Did not find instructor id - " + theId);
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
