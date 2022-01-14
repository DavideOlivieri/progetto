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
import it.univpm.exam_project.exception.InvalidInputException;
import it.univpm.exam_project.filters.CountryFilter;
import it.univpm.exam_project.models.Events;
import it.univpm.exam_project.services.EventServiceImpl;

/**
 * @author Davide Olivieri
 * @author Jacopo Coloccioni
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


	/*
	 * Route1 
	 * /GetEvents (used to get the metadata)
	 */
	@RequestMapping(value = "/getEvents")
	public ResponseEntity<Object> getEvents() {
		return new ResponseEntity<>(EventService.getEvents(), HttpStatus.OK);
	}


	/**
	 * Route2  
	 * /getEventForSegment (Search by segment, returns all events of that kind and the total of events)
	 * 
	 * @param segment
	 * @param condition
	 * @return
	 * @throws SegmentNotFoundException
	 */
	@RequestMapping(value = "/getEventsForSegment")
	public ResponseEntity<Object> getEventsfromSegment(@RequestParam(name="segment", defaultValue="Sports") String segment,
			@RequestParam(name="seeEvents", defaultValue= "no")String condition){
		try {
			boolean seeEvents = EventService.setCnd(condition);
			Vector<Events> vector = EventService.connection_segment(segment);

			SegmentFilter filterVector = new SegmentFilter();
			vector=filterVector.SegFilter(segment, vector);

			JSONObject JSONEvent_segment = new JSONObject();
			EventService.GrouppedGenreToJson(vector, seeEvents, JSONEvent_segment);
			EventService.GrouppedStateToJson(vector, false, JSONEvent_segment);
			return new ResponseEntity<>(JSONEvent_segment, HttpStatus.OK);
		} catch(InvalidInputException e) {
			return new ResponseEntity<>(e.getMsg(), HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}


	/**
	 * Route 3  
	 * /getEventForGenre (Search by genre, returns all events of that kind and the total of events).
	 * 
	 * @param genre
	 * @return JSONObject containing all the events for the choosen genre.
	 */
	@RequestMapping(value = "/getEventsForGenre")
	public ResponseEntity<Object> getEventsfromGenre(@RequestParam(name="genre", defaultValue="Basketball") String genre,
			@RequestParam(name="seeEvents", defaultValue= "no")String condition){
		try {
			boolean seeEvents = EventService.setCnd(condition);
			Vector<Events> vector = EventService.connection_genre(genre);

			GenreFilter filterVector = new GenreFilter();
			vector=filterVector.genFilter(genre, vector);

			JSONObject JSONEvent_genre = new JSONObject();
			
			EventService.GrouppedStateToJson(vector, seeEvents, JSONEvent_genre);
			return new ResponseEntity<>(JSONEvent_genre, HttpStatus.OK); 
		} catch(InvalidInputException e) {
			return new ResponseEntity<>(e.getMsg(), HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}

	
	/**
	 * Route 4 
	 * /getEventForCountryCode (Search by Country, returns all the events of that Country)
	 * 
	 * @param countryCode
	 * @param condition
	 * @return JSONObject containing all the events for the chosen countrycode
	 */
	@RequestMapping(value = "/getEventsForCountryCode")
	public ResponseEntity<Object> getEventsfromCountryCode(@RequestParam(name="countryCode", defaultValue="CA") String countryCode,
			@RequestParam(name="seeEvents", defaultValue= "no")String condition) {
		try {			
			boolean seeEvents = EventService.setCnd(condition);
			Vector<Events> vector = EventService.connection_country(countryCode);

			CountryFilter filterVector = new CountryFilter();
			vector = filterVector.countryFilter(countryCode, vector);

			JSONObject JSONEvent_country = new JSONObject();

			EventService.GrouppedGenreToJson(vector, seeEvents, JSONEvent_country);
			EventService.GrouppedStateToJson(vector, false, JSONEvent_country);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
		} catch(InvalidInputException e) {
			return new ResponseEntity<>(e.getMsg(), HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Route 5
	 *  /compareUSCA (Displays the statistics between the United States and Canada)
	 *  Compare the total of events, the monthly average and the month with the most and the least events
	 * 
	 * @param genre
	 * @return JSONObject 
	 */
	@RequestMapping(value = "/compareUSCA")
	public ResponseEntity<Object> cmpUsCa(@RequestParam(name="genre", defaultValue="Rock") String genre) {
		try {

			Vector<Events> vectorGen = EventService.connection_genre(genre);
			Vector<Events> vectorUS = EventService.connection_country("US");
			Vector<Events> vectorCA = EventService.connection_country("CA");
		    Vector<Events> vectorCountry = EventService.concateneted(vectorUS, vectorCA);
			CountryFilter filterVector = new CountryFilter();
			Vector<Events> vector = EventService.concateneted(vectorGen, vectorCountry);
			vectorUS = filterVector.countryFilter("US", vector);
			vectorCA = filterVector.countryFilter("CA", vector);
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

	/**
	 * Route 6
	 *  /getStats (Displays statistics of 1 or 2 certain state with 1 or 2 genres)
	 * 
	 * @param genre
	 * @param genre2
	 * @param state_code
	 * @param state_code2
	 * @param condition : with this parameter it is possible to show or not the list of events.
	 * @return JSONObject
	 */
	@RequestMapping(value = "/getStats")
	public ResponseEntity<Object> getStats(@RequestParam(name="genre", defaultValue="Hockey") String genre,
			@RequestParam(name="genre2", required=false) String genre2,
			@RequestParam(name="state_code", defaultValue="CA") String state_code,
			@RequestParam(name="state_code2", required=false)String state_code2,
			@RequestParam(name="seeEvents", defaultValue= "no")String condition){
		try {
			try {

				StatesFilter filterVectorState = new StatesFilter();
				boolean seeEvents = EventService.setCnd(condition);

				Vector<Events> vectorGen11 = EventService.connection_genre(genre);
				Vector<Events> vectorGen22= null;
				Vector<Events> vectorGen12 = EventService.connection_genre(genre);
				Vector<Events> vectorGen21= null;

				vectorGen11 = filterVectorState.stateFilter(state_code, vectorGen11);
				if(state_code2!=null)
				vectorGen12 = filterVectorState.stateFilter(state_code2, vectorGen12);
				if(genre2!=null) {
					vectorGen21 = EventService.connection_genre(genre2);
					vectorGen21 = filterVectorState.stateFilter(state_code, vectorGen21);
					if(state_code2!=null) {
						vectorGen22 = EventService.connection_genre(genre2);
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
			} catch(InvalidInputException e) {
				return new ResponseEntity<>(e.getMsg(), HttpStatus.BAD_REQUEST);
			}catch(IOException e) {
				return new ResponseEntity<>("Error1", HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Route 7
	 *  /getStatsforState (Displays the statistic for a given State)
	 *  Shows the number of events for genre from a given State
	 * 
	 * @param state_code
	 * @return JSONObject : Statistics for a given state
	 */
	@RequestMapping(value = "/getStatsforState")
	public ResponseEntity<Object> getStatsforState(@RequestParam(name="state_code", defaultValue="CA") String state_code,
			                                       @RequestParam(name="seeEvents", defaultValue= "no")String condition) {
		try {
			boolean seeEvents = EventService.setCnd(condition);

			StatesFilter filterVectorState = new StatesFilter();
			Vector<Events> vectorState = EventService.connection_state(state_code);
			vectorState = filterVectorState.stateFilter(state_code, vectorState);

			JSONObject JSONEvent_country = null;

			JSONEvent_country = EventService.StatsToJson(vectorState, state_code, seeEvents);
			return new ResponseEntity<>(JSONEvent_country, HttpStatus.OK);
			
		} catch(InvalidInputException e) {
			return new ResponseEntity<>(e.getMsg(), HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}

	}

}


