package com.assignment.instructor_details;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class InstructorDetailsApplicationTests {

	@Test
	public void contextLoads() {
		InstructorDetailsApplication application = new InstructorDetailsApplication();
		assertNotNull(application);	}

}
