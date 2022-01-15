package it.univpm.exam_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.exam_project.exception.InvalidInputException;
import it.univpm.exam_project.services.EventServiceImpl;

class InvalidInputExceptionTestTest {
	
	String inputValue ="treu";
	
	boolean exitValue;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		 Throwable exception = assertThrows(InvalidInputException.class, () -> {
			 exitValue=EventServiceImpl.setCnd(inputValue);
			 });
		 assertEquals("Error: Input invalid", exception.getMessage());
	}

}