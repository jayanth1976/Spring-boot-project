package com.assignment.instructor_details.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotEmpty(message="*firstName is required")
    @Column(name="first_name")
    private String firstName;

    @NotEmpty(message="*LastName is required")
    @Column(name="last_name")
    private String lastName;

    @NotEmpty(message="*email is required")
    @Column(name="email")
    @Email(message = "*should enter valid email")
    private String email;

    @OneToMany(mappedBy="instructor",
            cascade= {CascadeType.ALL})
    private List<Course> courses;

    public void add(Course tempCourse) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(tempCourse);

        tempCourse.setInstructor(this);
    }
}
