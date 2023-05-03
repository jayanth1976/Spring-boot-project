package com.assignment.instructor_details;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.jupiter.api.Assertions.assertTrue;


class InstructorDetailsApplicationTests {

	@Rule
	public MockitoRule mockitoRule= MockitoJUnit.rule();

	@BeforeEach
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void contextLoads() {
		InstructorDetailsApplication.main(new String[] {});
		assertTrue(true);
	}

}
