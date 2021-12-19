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

	public float avgEvents(Vector<Events> eventVector) {
		float avgevents=0;
		for(int i = 0; i<12;i++) {
			avgevents += numEvents(eventVector)[i];			
		}
		avgevents/=12;
		return avgevents;
	}

	public int minEvents(Vector<Events> eventVector) {
		int app = 0;
		for(int i = 0; i<12;i++) {
			if(numEvents(eventVector)[i] <= app) {
				app = numEvents(eventVector)[i];
			}
		}
		return app;
	}
	
	public int maxEvents(Vector<Events> eventVector) {
		int app = 0;
		for(int i = 0; i<12;i++) {
			if(numEvents(eventVector)[i] >= app) {
				app = numEvents(eventVector)[i];
			}
		}
		return app;
	}
	
	public int totEvents(Vector<Events> eventVector) {
		int app=0;
		for(int i=0; i<=eventVector.size();i++) {
			app++;
		}
		return app;
	}
	
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
	//dato un genere restituisce le statistiche
	public JSONObject FromGenreToJson(String genre, Vector<Events> eventVector) {
		JSONObject respons = new JSONObject();
		Vector<Events> filteredEvents = null;

		GenreFilter filterVector = new GenreFilter();
		filteredEvents=filterVector.genFilter(genre, eventVector);
		
		respons.put("tot_event", totEvents(filterVector.genFilter(genre, eventVector)) -1);
		
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
	//dato un paese restituisce le statistiche
	public JSONObject FromCountryToJson(String country, Vector<Events> eventVector) {
		JSONObject respons = new JSONObject();;

		Vector<Events> filteredEvents = null;
		StatesFilter filterVector = new StatesFilter();
		filteredEvents = filterVector.stateFilter(country, eventVector);
		
		respons.put("tot_event", totEvents(filterVector.stateFilter(country, eventVector)) -1);
		
		JSONArray events = new JSONArray();

		for(int i=0;i<filterVector.stateFilter(country, eventVector).size();i++) {
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
	
	public JSONObject getJSONObject(String jsonObject) {
		JSONObject obj = null;

		try {
			URLConnection openConnection = new URL(jsonObject).openConnection();
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
	
	private static String apiUrl="https://app.ticketmaster.com/discovery/v2/events.json?apikey=ojDlNPpgliPJgnuvATaFreLEiAEzHTcC";
	/*
	public static Vector<Events> connection_country(String countryCode){
		EventServiceImpl service = new EventServiceImpl();
		Vector<Events> eventVector = new Vector<Events>();
		String url = apiUrl+"&countryCode="+countryCode;
		JSONObject json = service.getJSONObject(url);
		Parser pars = new Parser();		
		eventVector = pars.parse(json);
		return eventVector;
	}
	*/
	public Vector<Events> connection_api(){
		Vector<Events> eventVector = new Vector<Events>();
		
		JSONObject json = getJSONObject(apiUrl);
		Parser pars = new Parser();		
		eventVector = pars.parse(json);

		return eventVector;
	}

}
