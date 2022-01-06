package it.univpm.exam_project.controller;

import java.util.Vector;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.exam_project.filters.GenreFilter;
import it.univpm.exam_project.filters.SegmentFilter;
import it.univpm.exam_project.filters.StatesFilter;
import it.univpm.exam_project.filters.CountryFilter;
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
			
			Vector<Events> filteredEvents = null;

			SegmentFilter filterVector = new SegmentFilter();
			filteredEvents=filterVector.SegFilter(segment, vector);
			
			JSONObject JSONEvent_segment = EventServiceImpl.ToJson(filteredEvents);
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
				
				Vector<Events> filteredEvents = null;

				GenreFilter filterVector = new GenreFilter();
				filteredEvents=filterVector.genFilter(genre, vector);
				
				JSONObject JSONEvent_genre = EventServiceImpl.ToJson(filteredEvents);
				return new ResponseEntity<>(JSONEvent_genre, HttpStatus.OK); 
				
			} catch(Exception e) {
				return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
			}
		}
	
	//Route 4 /getEventForCountryCode (Search by Country, returns all the events of that Country)
	@RequestMapping(value = "/getEventForCountryCode")
	public ResponseEntity<Object> getEventfromCountryCode(@RequestParam(name="countryCode", defaultValue="PL") String countryCode) {
		try {
			Vector<Events> vector = EventServiceImpl.connection_country(countryCode);
			
			Vector<Events> filteredEvents = null;
			CountryFilter filterVector = new CountryFilter();
			filteredEvents = filterVector.countryFilter(countryCode, vector);
			
			JSONObject JSONEvent_country = EventServiceImpl.ToJson( filteredEvents);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/cmpUsCa")
	public ResponseEntity<Object> cmpUsCa(@RequestParam(name="genre", defaultValue="Basketball") String genre) {
		try {
			Vector<Events> vectorUS = EventServiceImpl.connection_country("US");
			Vector<Events> vectorCA = EventServiceImpl.connection_country("CA");
			
			Vector<Events> filteredEventsUS = null;
			Vector<Events> filteredEventsCA = null;
			CountryFilter filterVector = new CountryFilter();
			filteredEventsUS = filterVector.countryFilter("US", vectorUS);
			filteredEventsCA = filterVector.countryFilter("CA", vectorCA);
			
			GenreFilter filterVectorGen = new GenreFilter();
			filteredEventsUS = filterVectorGen.genFilter(genre, filteredEventsUS);
			filteredEventsCA = filterVectorGen.genFilter(genre, filteredEventsCA);
			
			JSONObject JSONEvent_country = EventServiceImpl.CmpToJson( filteredEventsUS, filteredEventsCA);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getStats")
	public ResponseEntity<Object> getStats(@RequestParam(name="genre", defaultValue="Basketball") String genre, @RequestParam(name="state_code", defaultValue="Arizona") String state_code) {
		try {
			Vector<Events> vector = EventServiceImpl.connection_genre(genre);
			
			Vector<Events> filteredEvents = null;	
			
			StatesFilter filterVectorState = new StatesFilter();
			filteredEvents = filterVectorState.stateFilter(state_code, vector);
			
			JSONObject JSONEvent_country = EventServiceImpl.StatsToJson( filteredEvents);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
	}
	
	}
	
	
	
}
