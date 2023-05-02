package com.assignment.instructor_details;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class InstructorDetailsApplicationTests {

	@Test
	public void contextLoads() {
		InstructorDetailsApplication.main(new String[] {});
		assertTrue(true, "Application context loaded successfully");
	}

}
