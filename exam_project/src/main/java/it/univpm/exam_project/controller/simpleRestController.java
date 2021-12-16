package it.univpm.exam_project.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.exam_project.services.FromProgramToJson;

@RestController
public class simpleRestController {
	
	FromProgramToJson toJson = new FromProgramToJson();
	
	@RequestMapping(value = "/genre")
	public ResponseEntity<Object> getCurrentWeather(@RequestParam(name="genre") String genre) {

			JSONObject JSONEvent_genre = toJson.FromGenreToJson(genre, null);
			
			return new ResponseEntity<>(JSONEvent_genre, HttpStatus.OK);

	}
}
