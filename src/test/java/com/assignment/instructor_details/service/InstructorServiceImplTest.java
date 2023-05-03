package com.assignment.instructor_details.service;

import com.assignment.instructor_details.dao.InstructorRepository;
import com.assignment.instructor_details.entity.Instructor;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class InstructorServiceImplTest {

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServiceImpl instructorServiceImpl;

    @Test
    void findAll() {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor());
        instructors.add(new Instructor());

        when(instructorRepository.findAll()).thenReturn(instructors);

        List<Instructor> result = instructorServiceImpl.findAll();

        assertEquals(instructors, result);

        verify(instructorRepository).findAll();
    }

    @Test
    void testFindById() {
        Instructor instructor = new Instructor();
        when(instructorRepository.findById(1)).thenReturn(Optional.of(instructor));

        Instructor result = instructorServiceImpl.findById(1);

        verify(instructorRepository).findById(1);
        assertEquals(instructor, result);
    }

    @Test
    void testFindByIdThrowsException() {
        when(instructorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            instructorServiceImpl.findById(1);
        });

        verify(instructorRepository).findById(1);
    }

    @Test
    void save() {
        Instructor instructorToSave = new Instructor();
        instructorToSave.setFirstName("John");
        instructorToSave.setLastName("Doe");
        instructorToSave.setEmail("johndoe@example.com");

        instructorServiceImpl.save(instructorToSave);

        verify(instructorRepository).save(instructorToSave);
    }

    @Test
    void deleteById() {
        int instructorIdToDelete = 1;

        instructorServiceImpl.deleteById(instructorIdToDelete);

        verify(instructorRepository).deleteById(instructorIdToDelete);
    }
}