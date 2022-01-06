package it.univpm.exam_project.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.univpm.exam_project.filters.GenreFilter;
import it.univpm.exam_project.filters.StatesFilter;
import it.univpm.exam_project.models.Events;
import it.univpm.exam_project.parser.Parser;

/**
 * 
 * @author Davide Olivieri
 * @author Jacopo Coloccioni
 *
 */
@Service
public class EventServiceImpl implements EventService{
    
	
	
	public int[] numEvents(Vector<Events> eventVector) {

		int[] monthEvents = new int[12];
		for(int i = 0; i<eventVector.size();i++) {
			Events currentEvents = eventVector.get(i);

			for(int j = 1; j <= 12; j++) {
				String j_string = Integer.toString(j);
				if(currentEvents.getMonth().equals(j_string)) {
					monthEvents[j]++;
				}
			}
		}
		return monthEvents;
	}
    
	
	/**
	 * Method that return the average of events of all months
	 */
	public float avgEvents(Vector<Events> eventVector) {
		float avgevents=0;
		for(int i = 0; i<12;i++) {
			avgevents += numEvents(eventVector)[i];			
		}
		avgevents/=12;
		return avgevents;
	}
    
	
	/**
	 * Method that return the minimum of events for each month
	 */
	public int minEvents(Vector<Events> eventVector) {
		int app = 0;
		for(int i = 0; i<12;i++) {
			if(numEvents(eventVector)[i] <= app) {
				app = numEvents(eventVector)[i];
			}
		}
		return app;
	}
	
	
	/**
	 * Method that return the maximum of events for each month
	 */
	public int maxEvents(Vector<Events> eventVector) {
		int app = 0;
		for(int i = 0; i<12;i++) {
			if(numEvents(eventVector)[i] >= app) {
				app = numEvents(eventVector)[i];
			}
		}
		return app;
	}
	
	/**
	 * Method that returns the total of events
	 */
	public int totEvents(Vector<Events> eventVector) {
		int app=0;
		for(int i=0; i<=eventVector.size();i++) {
			app++;
		}
		return app;
	}
	
	
	/**
	 * 
	 * @return respons
	 */
	public JSONObject getEvents() {
		JSONObject respons = new JSONObject();
		
		respons.put("event_name"," : event name");
		respons.put("event_id"," : event id");
		respons.put("local_date"," : date");
		respons.put("local_time"," : time(year/month/day)");
		respons.put("country_code"," : country code");
		respons.put("city"," : city name");
		respons.put("state"," : state name");
		respons.put("country_name"," : country name");
		respons.put("segment", " : segment");
		respons.put("genre", " : genre");
		respons.put("subgenre", " : subgenre");
		/*respons.put("monthEvents", " : ");
		respons.put("avgEvents", "");
		respons.put("minEvents", "");
		respons.put("maxEvents", "");
		respons.put("totEvents", "");*/
		
		return respons;
	}
	
	
	/**
	 * Given a genre it returns the statistics for it
	 */
	public JSONObject FromGenreToJson(String genre, Vector<Events> eventVector) {
		JSONObject respons = new JSONObject();
		Vector<Events> filteredEvents = null;

		GenreFilter filterVector = new GenreFilter();
		filteredEvents=filterVector.genFilter(genre, eventVector);
		
		respons.put("tot_event", totEvents(filteredEvents) -1);
		
		JSONArray events = new JSONArray();

		for(int i=0;i<filterVector.genFilter(genre, eventVector).size();i++) {
			JSONObject event = new JSONObject();
			
			event.put("event_name", filteredEvents.get(i).getName());
			event.put("event_id", filteredEvents.get(i).getEvent_id());
			event.put("local_date", filteredEvents.get(i).getLocal_date());
			event.put("local_time", filteredEvents.get(i).getLocal_time());
			event.put("country_code", filteredEvents.get(i).getCountry_code());
			event.put("city", filteredEvents.get(i).getCity());
			event.put("state", filteredEvents.get(i).getState());
			event.put("country_name", filteredEvents.get(i).getCountry_name());
			event.put("segment", filteredEvents.get(i).getSegment());
			event.put("genre", filteredEvents.get(i).getGenre() );
			event.put("subgenre", filteredEvents.get(i).getSubgenre() );
			
			events.add(event);
		}
		
		respons.put("Events", events);
		return respons;
	}
	
	
    /**
     * Given a country it returns the statistic for it
     * 
     * @param country
     * @param eventVector
     * @return respons
     */
	public JSONObject FromCountryToJson(String countryCode, Vector<Events> eventVector) {
		JSONObject respons = new JSONObject();;

		Vector<Events> filteredEvents = null;
		StatesFilter filterVector = new StatesFilter();
		filteredEvents = filterVector.stateFilter(countryCode, eventVector);
		
		respons.put("tot_event", totEvents(filteredEvents) -1);
		
		JSONArray events = new JSONArray();

		for(int i=0;i<filterVector.stateFilter(countryCode, eventVector).size();i++) {
			JSONObject event = new JSONObject();
			
			event.put("event_name", filteredEvents.get(i).getName());
			event.put("event_id", filteredEvents.get(i).getEvent_id());
			event.put("local_date", filteredEvents.get(i).getLocal_date());
			event.put("local_time", filteredEvents.get(i).getLocal_time());
			event.put("country_code", filteredEvents.get(i).getCountry_code());
			event.put("city", filteredEvents.get(i).getCity());
			event.put("state", filteredEvents.get(i).getState());
			event.put("country_name", filteredEvents.get(i).getCountry_name());
			event.put("segment", filteredEvents.get(i).getSegment());
			event.put("genre", filteredEvents.get(i).getGenre() );
			event.put("subgenre", filteredEvents.get(i).getSubgenre() );
			
			events.add(event);
		}
		
		respons.put("Events", events);
		return respons;
	}
	
	
	
	public JSONObject getJSONObject(String url) {
		JSONObject obj = null;

		try {
			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( inR );

				while ( ( line = buf.readLine() ) != null ) {
					data+= line;
				}
			} finally {
				in.close();
			}
			obj = (JSONObject) JSONValue.parseWithException(data);	 //parse JSON Object


		} catch (IOException | ParseException e) {
			//e.printStackTrace();
			System.out.println("ERROR: I/O error or parse error");
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("ERROR");
		}			

		return obj;
	}
	
	//private static String apiUrl="https://app.ticketmaster.com/discovery/v2/events.json?apikey=ojDlNPpgliPJgnuvATaFreLEiAEzHTcC";
	/*
	 * private static String apiUrl="https://app.ticketmaster.com/discovery/v2/events.json";
	 *
	  private static String apiKey="ojDlNPpgliPJgnuvATaFreLEiAEzHTcC";
	*/
	private static String apiUrl_country="https://app.ticketmaster.com/discovery/v2/events.json?apikey=ojDlNPpgliPJgnuvATaFreLEiAEzHTcC&countryCode=";
	private static String apiUrl_genre="https://app.ticketmaster.com/discovery/v2/events.json?apikey=ojDlNPpgliPJgnuvATaFreLEiAEzHTcC&segmentName=";
	 
	public Vector<Events> connection_country(String countryCode){
		
		Vector<Events> eventVector = new Vector<Events>();
		String url = apiUrl_country+countryCode;
		JSONObject json = getJSONObject(url);
		Parser pars = new Parser();		
		eventVector = pars.parse(json);
		
		return eventVector;
	}
	
	
	public Vector<Events> connection_genre(String genre){
		
		Vector<Events> eventVector = new Vector<Events>();
		String url = apiUrl_genre+genre;
		JSONObject json = getJSONObject(url);
		Parser pars = new Parser();
		eventVector = pars.parse(json);

		return eventVector;
	}

}
