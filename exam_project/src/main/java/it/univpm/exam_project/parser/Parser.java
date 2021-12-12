package it.univpm.exam_project.parser;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.exam_project.models.Events;

public class Parser {
	private Vector<Events> eventsList;
   
	public Vector<Events> parse(String file){
		eventsList = new Vector<Events>();
		
		try {
			JSONParser parser = new JSONParser();
			
			JSONObject object = (JSONObject) parser.parse(file);
			
			JSONObject embedded1 = (JSONObject) object.get("_embedded");

			JSONArray events = (JSONArray) embedded1.get("events");

for (int i = 0; i < events.size(); i++) {
				
				JSONObject currentEvent = (JSONObject) events.get(i);
				String name = (String) currentEvent.get("name");
				String id = (String) currentEvent.get("id");
				String url = (String) currentEvent.get("url");
				
				JSONObject dates = (JSONObject) currentEvent.get("dates");
				
				JSONObject start = (JSONObject) dates.get("start");
				String localDate = (String) start.get("localDate");
				String localTime = (String) start.get("localTime");
				
				JSONArray classifications = (JSONArray) currentEvent.get("classifications");
				
				JSONObject classificationsTemp = (JSONObject) classifications.get(0);
				
				JSONObject segment = (JSONObject) classificationsTemp.get("segment");
				String nameSegment = (String) segment.get("name");
				
				JSONObject genre = (JSONObject) classificationsTemp.get("genre");
				String nameGenre = (String) genre.get("name");
				
				JSONObject subGenre = (JSONObject) classificationsTemp.get("subGenre");
				String nameSubGenre = (String) subGenre.get("name");
				
				JSONObject embedded2 = (JSONObject) currentEvent.get("_embedded");
				
				JSONArray venues = (JSONArray) embedded2.get("venues");
				
				JSONObject venuesTemp = (JSONObject) venues.get(0);
				
				JSONObject city = (JSONObject) venuesTemp.get("city");
				String cityName = (String) city.get("name");
				
				JSONObject state = (JSONObject) venuesTemp.get("state");
				String stateName = (String) state.get("name");
				
				JSONObject country = (JSONObject) venuesTemp.get("country");
				String countryName = (String) country.get("name");
				String countryCode = (String) country.get("stateCode");
				
				Events e = new Events(name, id, url, localDate, localTime, countryCode, cityName, stateName, countryName, nameGenre, nameSubGenre, nameSegment);

				eventsList.add(e);
			
        }
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return eventsList;
	}
}
