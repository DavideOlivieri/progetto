package it.univpm.exam_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.exam_project.exception.genreParamException;
import it.univpm.exam_project.services.EventServiceImpl;

/**
 * 
 * @author Jacopo Coloccioni
 * @author Davide Olivieri
 *
 */
class genreControllerExceptionTest {
	
	String inputGenre ="music";

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		 genreParamException exception = assertThrows(genreParamException.class, () -> {
			 EventServiceImpl.genreController(inputGenre);
			 });
		 assertEquals("Error: The genre searched for does not exist or is not present in our databases. \n"
					+ "The first letter must be uppercase for every genre. \n"
					+ "These are some examples of the most popular genres: \n"
					+ "Baseball, Basketball, Boxing, Hockey; Classical, Hip-Hop/Rap, Blues; Comedy; Hobby/Special Interest Expos", exception.getMsg());
	}

}
