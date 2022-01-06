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

/**
 * @author JacopoColoccioni
 *
 */

@RestController
public class simpleRestController {
	
	/**
	 * @see it.univpm.exam_project.models.Events
	 * @see it.univpm.exam_project.services.EventServiceImpl
	 * 
	 * Creation of different routes
	 */
	
	
	@Autowired	
	EventServiceImpl EventServiceImpl;
	
	// Route1  /GetEvents (used to get the metadata)
	@RequestMapping(value = "/getEvents")
	public ResponseEntity<Object> getEvents() {
		return new ResponseEntity<>(EventServiceImpl.getEvents(), HttpStatus.OK);
	}
	
	//Route2  /getEventForSegment (Search by segment, returns all events of that kind and the total of events)
	@RequestMapping(value = "/getEventForSegment")
	public ResponseEntity<Object> getEventfromSegment(@RequestParam(name="segment", defaultValue="Sports") String segment) {
		try {
			Vector<Events> vector = EventServiceImpl.connection_segment(segment);
			JSONObject JSONEvent_segment = EventServiceImpl.FromSegmentToJson(segment, vector);
			return new ResponseEntity<>(JSONEvent_segment, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Route 3  /getEventForGenre (Search by genre, returns all events of that kind and the total of events)
		@RequestMapping(value = "/getEventForGenre")
		public ResponseEntity<Object> getEventfromGenre(@RequestParam(name="genre", defaultValue="Basketball") String genre) {
			try {
				Vector<Events> vector = EventServiceImpl.connection_genre(genre);
				JSONObject JSONEvent_genre = EventServiceImpl.FromGenreToJson(genre, vector);
				return new ResponseEntity<>(JSONEvent_genre, HttpStatus.OK);  ////
				
			} catch(Exception e) {
				return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
			}
		}
	
	//Route 4 /getEventForCountryCode (Search by Country, returns all the events of that Country)
	@RequestMapping(value = "/getEventForCountryCode")
	public ResponseEntity<Object> getEventfromCountryCode(@RequestParam(name="countryCode", defaultValue="PL") String countryCode) {
		try {
			Vector<Events> vector = EventServiceImpl.connection_country(countryCode);
			JSONObject JSONEvent_country = EventServiceImpl.FromCountryToJson(countryCode, vector);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	/*
	@RequestMapping(value = "/cmpUsCa")
	public ResponseEntity<Object> cmpUsCa(@RequestParam(name="genre") String genre) {
		
	}
	
	@RequestMapping(value = "/getStats")
	public ResponseEntity<Object> getStats(@RequestParam(name="genre") String genre, @RequestParam(name="state") String state) {
		
	}
	*/
	
	
}
