package it.univpm.exam_project.parser;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.exam_project.models.Events;
import it.univpm.exam_project.models.EventsUE;

/**
 * Class that analyze metadata from TicketMaster API JSON file
 * 
 * @author DavideOlivieri
 * @author JacopoColoccioni
 * 
 */
public class Parser {

	/**
	 * Method that analyze the JSON returned by the API call
	 * and inserts the data into the eventList vector.
	 * 
	 * @param json, provides the JSON code which is analyzed in the method.
	 * @return eventsList, vector of events containing the events from the JSON code
	 * @see it.univpm.exam_project.models.Events
	 * @see it.univpm.exam_project.services.EventServiceImpl
	 */

	public Vector<Events> parse(JSONObject json){

		Vector<Events> eventsList = new Vector<Events>();

		for(int j=0; j<json.size(); j++) {

			JSONObject embedded1 = (JSONObject) json.get("_embedded");

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
				String stateCode = (String) state.get("stateCode");

				JSONObject country = (JSONObject) venuesTemp.get("country");
				String countryName = (String) country.get("name");
				String countryCode = (String) country.get("countryCode");

				Events e = new Events(name, id, url, localDate, localTime, countryCode, stateCode, cityName,
						stateName, countryName, nameGenre, nameSubGenre, nameSegment);

				eventsList.add(e);

			}
		}
		return eventsList;
	}
	
	public Vector<EventsUE> parseUE(JSONObject json){

		Vector<EventsUE> eventsList = new Vector<EventsUE>();

		for(int j=0; j<json.size(); j++) {

			JSONObject embedded1 = (JSONObject) json.get("_embedded");

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

				JSONObject country = (JSONObject) venuesTemp.get("country");
				String countryName = (String) country.get("name");
				String countryCode = (String) country.get("countryCode");

				EventsUE e = new EventsUE(name, id, url, localDate, localTime, countryCode, cityName,
									countryName, nameGenre, nameSubGenre, nameSegment);

				eventsList.add(e);

			}
		}
		return eventsList;
	}
}
