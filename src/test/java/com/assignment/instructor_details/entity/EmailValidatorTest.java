package com.assignment.instructor_details.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import jakarta.validation.ConstraintValidatorContext;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

class EmailValidatorTest {

    private EmailValidator emailValidator;
    private ConstraintValidatorContext constraintValidatorContext;

    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @BeforeEach
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }


    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
        constraintValidatorContext = null;
        emailValidator.initialize(null);
    }

    @Test
    void testValidEmail() {
        assertTrue(emailValidator.isValid("test@example.com", constraintValidatorContext));
    }

    @Test
    void testInvalidEmail() {
        assertFalse(emailValidator.isValid("test@example", constraintValidatorContext));
    }

    @Test
    void testNullEmail() {
        assertFalse(emailValidator.isValid(null, constraintValidatorContext));
    }
}
