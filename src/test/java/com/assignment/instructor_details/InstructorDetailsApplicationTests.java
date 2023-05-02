package com.assignment.instructor_details;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;




public class InstructorDetailsApplicationTests {

	@Rule
	public MockitoRule mockitoRule= MockitoJUnit.rule();

	@BeforeEach
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void contextLoads() {
		InstructorDetailsApplication.main(new String[] {});
	}

}
