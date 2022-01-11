package it.univpm.exam_project.controller;

import java.io.IOException;
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
	EventServiceImpl EventService;
	
	// Route1  /GetEvents (used to get the metadata)
	@RequestMapping(value = "/getEvents")
	public ResponseEntity<Object> getEvents() {
		return new ResponseEntity<>(EventService.getEvents(), HttpStatus.OK);
	}
	
	//Route2  /getEventForSegment (Search by segment, returns all events of that kind and the total of events)
	@RequestMapping(value = "/getEventForSegment")
	public ResponseEntity<Object> getEventfromSegment(@RequestParam(name="segment", defaultValue="Sports") String segment,
													  @RequestParam(name="seeEvents", defaultValue= "no")String condition) {
		try {
			boolean seeEvents=false;
			if(condition.equals("no")||condition.equals("No")||condition.equals("NO")||condition.equals("n")||condition.equals("N")||condition.equals("false"))
				seeEvents=false;
			else 
				seeEvents=true;
			Vector<Events> vector = EventService.connection_segment(segment);

			SegmentFilter filterVector = new SegmentFilter();
			vector=filterVector.SegFilter(segment, vector);
			
			JSONObject JSONEvent_segment = new JSONObject();
			EventService.GrouppedToJson(vector, seeEvents, JSONEvent_segment);
			return new ResponseEntity<>(JSONEvent_segment, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Route 3  /getEventForGenre (Search by genre, returns all events of that kind and the total of events)
		@RequestMapping(value = "/getEventForGenre")
		public ResponseEntity<Object> getEventfromGenre(@RequestParam(name="genre", defaultValue="Basketball") String genre) {
			try {
				Vector<Events> vector = EventService.connection_genre(genre);

				GenreFilter filterVector = new GenreFilter();
				vector=filterVector.genFilter(genre, vector);
				
				JSONObject JSONEvent_genre = new JSONObject();
				
				EventService.ToJson(vector,JSONEvent_genre);
				return new ResponseEntity<>(JSONEvent_genre, HttpStatus.OK); 
				
			} catch(Exception e) {
				return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
			}
		}
	
	//Route 4 /getEventForCountryCode (Search by Country, returns all the events of that Country)
	@RequestMapping(value = "/getEventForCountryCode")
	public ResponseEntity<Object> getEventfromCountryCode(@RequestParam(name="countryCode", defaultValue="PL") String countryCode,
														  @RequestParam(name="seeEvents", defaultValue= "no")String condition) {
		try {			
			boolean seeEvents=false;
			if(condition.equals("no")||condition.equals("No")||condition.equals("NO")||condition.equals("n")||condition.equals("N")||condition.equals("false"))
				seeEvents=false;
			else 
				seeEvents=true;
			Vector<Events> vector = EventService.connection_country(countryCode);
			
			CountryFilter filterVector = new CountryFilter();
			vector = filterVector.countryFilter(countryCode, vector);
			
			JSONObject JSONEvent_country = new JSONObject();
			
			EventService.GrouppedToJson(vector, seeEvents, JSONEvent_country);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/compareUSCA")
	public ResponseEntity<Object> cmpUsCa(@RequestParam(name="genre", required=false) String genre) {
		try {
			Vector<Events> vectorUS = EventService.connection_country("US");
			Vector<Events> vectorCA = EventService.connection_country("CA");
			
			CountryFilter filterVector = new CountryFilter();
			vectorUS = filterVector.countryFilter("US", vectorUS);
			vectorCA = filterVector.countryFilter("CA", vectorCA);
			if(genre!=null) {
				GenreFilter filterVectorGen = new GenreFilter();
				vectorUS = filterVectorGen.genFilter(genre, vectorUS);
				vectorCA = filterVectorGen.genFilter(genre, vectorCA);
			}
			JSONObject JSONEvent_country = EventService.CmpToJson( vectorUS, vectorCA);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * 
	 */
	@RequestMapping(value = "/getStats")
	public ResponseEntity<Object> getStats(@RequestParam(name="genre", defaultValue="Hockey") String genre,
										   @RequestParam(name="genre2", required=false) String genre2,
										   @RequestParam(name="state_code", defaultValue="CA") String state_code,
										   @RequestParam(name="state_code2", required=false)String state_code2,
										   @RequestParam(name="seeEvents", defaultValue= "no")String condition) {
		try {
			try {
				StatesFilter filterVectorState = new StatesFilter();
				boolean seeEvents=false;
				if(condition.equals("no")||condition.equals("No")||condition.equals("NO")||condition.equals("n")||condition.equals("N")||condition.equals("false"))
					seeEvents=false;
				else 
					seeEvents=true;

				Vector<Events> vectorGen11 = EventService.connection_genre(genre);
				Vector<Events> vectorGen22= null;
				Vector<Events> vectorGen12 = EventService.connection_genre(genre);
				Vector<Events> vectorGen21= null;
				
				vectorGen11 = filterVectorState.stateFilter(state_code, vectorGen11);
				if(genre2!=null) {
					vectorGen21 = EventService.connection_genre(genre2);
					vectorGen21 = filterVectorState.stateFilter(state_code, vectorGen21);
					if(state_code2!=null) {
						vectorGen22 = EventService.connection_genre(genre2);
						vectorGen12 = filterVectorState.stateFilter(state_code2, vectorGen12);
						vectorGen22 = filterVectorState.stateFilter(state_code2, vectorGen22);
					}
				}

				JSONObject JSONEvent_country = null;
				if(state_code2==null && genre2==null)
					JSONEvent_country = EventService.StatsToJson( vectorGen11, state_code, genre, seeEvents);
				else if(state_code2==null)
					JSONEvent_country = EventService.StatsToJson_genre( vectorGen11, vectorGen21, state_code, genre, genre2, seeEvents );
				else if(genre2==null)
					JSONEvent_country = EventService.StatsToJson_state( vectorGen11, vectorGen12, state_code, genre, state_code2, seeEvents);
				else
					JSONEvent_country = EventService.StatsToJson( vectorGen11, vectorGen21, vectorGen12, vectorGen22, state_code, genre, state_code2, genre2, seeEvents);
				return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
				
			}catch(IOException e) {
				return new ResponseEntity<>("Error1", HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/getStatsforState")
	public ResponseEntity<Object> getStatsforState(@RequestParam(name="state_code", defaultValue="CA") String state_code) {
		try {
			
				StatesFilter filterVectorState = new StatesFilter();
				Vector<Events> vectorState = EventService.connection_state(state_code);
				vectorState = filterVectorState.stateFilter(state_code, vectorState);

				JSONObject JSONEvent_country = null;

				JSONEvent_country = EventService.StatsToJson(vectorState, state_code);
				return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
				
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}

	}

}


