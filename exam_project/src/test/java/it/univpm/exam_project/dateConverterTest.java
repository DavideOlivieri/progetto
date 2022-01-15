package it.univpm.exam_project;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.exam_project.services.EventServiceImpl;

class dateConverterTest {
	
	private String dateString;
	
	private LocalDate localDateString, trueValue = LocalDate.of(2022, 1, 15);

	
	@BeforeEach
	public void setUp() {
		
		dateString = "2022-01-15";
		
		localDateString = EventServiceImpl.dateConverter(dateString);
		
	}
	
	@AfterEach
	public void TearDown() {}
	
	@Test
	public void testDateConverter() {
		assertEquals(localDateString, trueValue);
	}


}
