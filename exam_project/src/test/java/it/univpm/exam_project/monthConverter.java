package it.univpm.exam_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.exam_project.services.EventServiceImpl;

class monthConverterTest {
	
	private int month = 12;

	private String finalValue;
	
	@BeforeEach
	public void setUp() {
		
		finalValue=EventServiceImpl.convertMonth(month);
		
	}
	
	@AfterEach
	public void TearDown() {}
	
	@Test
	public void testDateConverter() {
		assertEquals(finalValue, "December");
	}

}
