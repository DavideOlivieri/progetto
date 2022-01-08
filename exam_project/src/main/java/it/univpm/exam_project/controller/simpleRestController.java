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

			SegmentFilter filterVector = new SegmentFilter();
			vector=filterVector.SegFilter(segment, vector);
			
			JSONObject JSONEvent_segment = EventServiceImpl.ToJson(vector);
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

				GenreFilter filterVector = new GenreFilter();
				vector=filterVector.genFilter(genre, vector);
				
				JSONObject JSONEvent_genre = EventServiceImpl.ToJson(vector);
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
			
			CountryFilter filterVector = new CountryFilter();
			vector = filterVector.countryFilter(countryCode, vector);
			
			JSONObject JSONEvent_country = EventServiceImpl.ToJson( vector);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/compareUSCA")
	public ResponseEntity<Object> cmpUsCa(@RequestParam(name="genre", defaultValue="Basketball") String genre) {
		try {
			Vector<Events> vectorUS = EventServiceImpl.connection_country("US");
			Vector<Events> vectorCA = EventServiceImpl.connection_country("CA");
			
			CountryFilter filterVector = new CountryFilter();
			vectorUS = filterVector.countryFilter("US", vectorUS);
			vectorCA = filterVector.countryFilter("CA", vectorCA);
			
			GenreFilter filterVectorGen = new GenreFilter();
			vectorUS = filterVectorGen.genFilter(genre, vectorUS);
			vectorCA = filterVectorGen.genFilter(genre, vectorCA);
			
			JSONObject JSONEvent_country = EventServiceImpl.CmpToJson( vectorUS, vectorCA);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getStats")
	public ResponseEntity<Object> getStats(@RequestParam(name="genre", defaultValue="Hockey") String genre, @RequestParam(name="state_code", defaultValue="CA") String state_code) {
		try {
			Vector<Events> vectorGen = EventServiceImpl.connection_genre(genre);
			Vector<Events> vectorState = EventServiceImpl.connection_state(state_code);

			GenreFilter filterVectorGen = new GenreFilter();
			vectorState = filterVectorGen.genFilter(genre, vectorState);
			
			StatesFilter filterVectorState = new StatesFilter();
			vectorGen = filterVectorState.stateFilter(state_code, vectorGen);
			
			Vector<Events> vector = EventServiceImpl.concateneted(vectorGen, vectorState);
	
			JSONObject JSONEvent_country = EventServiceImpl.StatsToJson( vector, state_code, genre);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
	}
	
	}
	
	
	
}
