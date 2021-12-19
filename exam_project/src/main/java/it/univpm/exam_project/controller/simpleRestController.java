package it.univpm.exam_project.controller;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.exam_project.models.Events;
import it.univpm.exam_project.services.EventServiceImpl;
@RestController
public class simpleRestController {
	
	@Autowired	
	EventServiceImpl EventServiceImpl;
	
	@RequestMapping(value = "/getEvents")
	public ResponseEntity<Object> getEvents() {
		return new ResponseEntity<>(EventServiceImpl.getEvents(), HttpStatus.OK);
	}
	
	//Search by genre, returns all events of that kind and the total of events
	@RequestMapping(value = "/getEventForGenre")
	public ResponseEntity<Object> getEventfromGenre(@RequestParam(name="genre", defaultValue="Sports") String genre) {
		try {
			Vector<Events> vector = EventServiceImpl.connection_api();
			JSONObject JSONEvent_genre = EventServiceImpl.FromGenreToJson(genre, vector);
			return new ResponseEntity<>(JSONEvent_genre, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getEventForCountryCode")
	public ResponseEntity<Object> getEventfromCountryCode(@RequestParam(name="countryCode", defaultValue="US") String countryCode) {
		try {
			Vector<Events> vector = EventServiceImpl.connection_api();
			JSONObject JSONEvent_country = EventServiceImpl.FromCountryToJson(countryCode, vector);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
