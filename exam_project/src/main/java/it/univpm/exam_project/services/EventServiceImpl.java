package it.univpm.exam_project.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.univpm.exam_project.exception.InvalidInputException;
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


	//   **************************************************************************************************
	//                     CONNECTION TO GET METADATA FROM API
	//   **************************************************************************************************

   /**
    * 
    */
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


//  ***************************************************************************************************************************
//	                    ALL DIFFERENT CONNECTIONS TO OBTAIN SPECIFIC DATA
//  ***************************************************************************************************************************

	// apiurl + personal apikey
	private static String apiUrl="https://app.ticketmaster.com/discovery/v2/events.json?apikey=ojDlNPpgliPJgnuvATaFreLEiAEzHTcC";

	/**
	 * This method makes a call to the API, passing the countrycode along with the url. 
	 * Then a JSONObject is created through which the data is taken,
	 * then through the parser the events are inserted into an event vector which is finally returned
	 * 
	 * @param countryCode
	 * @return eventVector
	 */
	public Vector<Events> connection_country(String countryCode){
		Vector<Events> eventVector = new Vector<Events>();

		String url = apiUrl+"&countryCode="+countryCode;
		JSONObject json = getJSONObject(url);
		Parser pars = new Parser();		
		eventVector = pars.parse(json);
		return eventVector;
	}

	/**
	 * This method makes a call to the API, passing the segment along with the url. 
	 * Then a JSONObject is created through which the data is taken,
	 * then through the parser the events are inserted into an event vector which is finally returned
	 * 
	 * @param segment
	 * @return eventVector
	 */
	public Vector<Events> connection_segment(String segment){
		Vector<Events> eventVector = new Vector<Events>();
		String url = apiUrl+"&segmentName="+segment;
		JSONObject json = getJSONObject(url);
		Parser pars = new Parser();
		eventVector = pars.parse(json);
		
		return eventVector;
	}

	/**
	 * This method makes a call to the API, passing the genre along with the url. 
	 * Then a JSONObject is created through which the data is taken,
	 * then through the parser the events are inserted into an event vector which is finally returned
	 * 
	 * @param genre
	 * @return eventVector
	 */
	public Vector<Events> connection_genre(String genre){
		Vector<Events> eventVector = new Vector<Events>();

		String url = apiUrl+"&classificationName="+genre;
		JSONObject json = getJSONObject(url);
		Parser pars = new Parser();
		eventVector = pars.parse(json);
		
		return eventVector;
	}

	/**
	 * This method makes a call to the API, passing the statecode along with the url. 
	 * Then a JSONObject is created through which the data is taken,
	 * then through the parser the events are inserted into an event vector which is finally returned
	 * 
	 * @param state_code
	 * @return eventVector
	 */
	public Vector<Events> connection_state(String state_code){
		Vector<Events> eventVector = new Vector<Events>();

		String url = apiUrl+"&stateCode="+state_code;
		JSONObject json = getJSONObject(url);
		Parser pars = new Parser();
		eventVector = pars.parse(json);

		return eventVector;
	}


	public Vector<Events> concateneted(Vector<Events> vector1, Vector<Events> vector2){
		Vector<Events> vector = new Vector<Events>();

		vector.addAll(vector1);
		vector.addAll(vector2);

		return vector;
	}

	public LocalDate dateConverter(String localDate) {
		LocalDate locD = LocalDate.parse((CharSequence)localDate);
		return locD;
	}

	/**
	 * This method is used to convert months from an int to a String.
	 * 
	 * @param i
	 */
	public String convertMonth(int i) {
		if(i==1)
			return "January";
		if(i==2)
			return "February";
		if(i==3)
			return "March";
		if(i==4)
			return "April";
		if(i==5)
			return "May";
		if(i==6)
			return "June";
		if(i==7)
			return "July";
		if(i==8)
			return "August";
		if(i==9)
			return "September";
		if(i==10)
			return "October";
		if(i==11)
			return "November";
		if(i==12)
			return "December";
		return "Error month";

	}
	
	public boolean setCnd(String condition) throws InvalidInputException {
		boolean tf=false;
			if(condition.equals("no")||condition.equals("No")||condition.equals("NO")||condition.equals("n")||condition.equals("N")||condition.equals("false"))
				tf=false;
			else if(condition.equals("si")||condition.equals("Si")||condition.equals("SI")||condition.equals("yes")||condition.equals("s")||condition.equals("true"))
				tf=true;
			else 
				throw new InvalidInputException();
		return tf;
	}

	/**
	 * This method is used to create a vector containing all the genres that are read from the genres.txt file
	 * 
	 * @return generiVect
	 */
	public static Vector<String> readGen() {

		Vector<String> generiVect = new Vector<String>();

		try {

			Scanner fileGeneri = new Scanner(new BufferedReader(new FileReader("src/main/java/it/univpm/exam_project/services/genres.txt.txt")));
			while (fileGeneri.hasNext())
				generiVect.add(fileGeneri.nextLine());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return generiVect;
	}

	/**
	 * This method is used to create a vector containing all the states that are read from the genres.txt file
	 * 
	 * @return stateVect
	 */
	public static Vector<String> readState() {

		Vector<String> stateVect = new Vector<String>();

		try {

			Scanner fileState = new Scanner(new BufferedReader(new FileReader("src/main/java/it/univpm/exam_project/services/states.txt")));
			while (fileState.hasNext())
				stateVect.add(fileState.nextLine());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return stateVect;
	}

	
	//   **************************************************************************************************
	//                        METHODS USED TO MAKE STATISTICS      
	//   **************************************************************************************************

    /**
     * 
     * @param eventVector
     * @param monthEvents
     */
	public void numEvents(Vector<Events> eventVector, int[]monthEvents) {
		Events currentEvents;
		for(int i = 0; i<eventVector.size();i++) {
			currentEvents = eventVector.get(i);

			for(int j = 1; j <= 12; j++) {
				if(currentEvents.getMonth()==j) {
					monthEvents[j-1]++;
				}
			}
		}

	} 

	/**
	 * Method that return the average of events of all months.
	 * 
	 * @param eventVector
	 * @return
	 */
	public float avgEvents(Vector<Events> eventVector) {
		float avgevents=0;
		int[] ev = new int[12];
		numEvents(eventVector, ev);
		for(int i = 0; i<12;i++) {
			avgevents += ev[i];			
		}
		avgevents/=12;
		return avgevents;
	}


    /**
     * Method that returns the month with the fewest events
     * 
     * @param eventVector, Vector with all the events inside
     * @return month, month with the fewest events.
     */
	public String minEvents(Vector<Events> eventVector) {
		int app = 10000;
		String month="";
		int j=0;
		int[] ev = new int[12];
		numEvents(eventVector, ev);
		for(int i = 0; i<12;i++) {
			if(ev[i] < app) {
				app = ev[i];
				j=i;
			}
	
		}
		month+=convertMonth(j+1);
		
		for(int k = j+1; k<12; k++) {
			if(ev[k] == app) {
					month+=", "+convertMonth(k+1);
			}
	
		}
		
		return month;
	}



	/**
	 * Method that returns the month with the most events
	 * 
	 * @param eventVector
	 * @return month, month with the most events.
	 */
	public String maxEvents(Vector<Events> eventVector) {
		int tot = 0;
		int j=-1;
		String month;
		int[] ev = new int[12];
		numEvents(eventVector, ev);
		for(int i = 0; i<12;i++) {
			if(ev[i] > tot) {
				tot =  ev[i];
				j=i;
			}
		}
		if(j==-1)
			month="No month has more than 0 events";
		else
			month=convertMonth(j+1);
		return month;
	}

    /**
     * Method that returns the total of events
     * 
     * @param eventVector
     * @return tot, the total of events inside the Vector.
     */
	public int totEvents(Vector<Events> eventVector) {
		int tot=0;
		for(int i=0; i<=eventVector.size();i++) {
			tot++;
		}

		return tot;
	}
	
	public JSONObject genEvents(Vector<Events> eventVector, Vector<String> genre) {

		JSONObject respons = new JSONObject();
		Events currentEvents;
		String currentGenre;
		for(int j=0; j<genre.size();j++) {
			int k=0;
			currentGenre= genre.get(j);
			for(int i = 0; i<eventVector.size();i++) {
				currentEvents = eventVector.get(i);
				if(currentEvents.getGenre().equals(currentGenre)) {
					k++;
				}
			}
			if(k!=0)
				respons.put("Events number of "+ currentGenre +" ",k );
		}
		return respons;
	} 

	public JSONObject stateEvents(Vector<Events> eventVector, Vector<String> stateName) {

		JSONObject respons = new JSONObject();
		Events currentEvents;
		String currentState;
		for(int j=0; j<stateName.size();j++) {
			int k=0;
			currentState= stateName.get(j);
			for(int i = 0; i<eventVector.size();i++) {
				currentEvents = eventVector.get(i);
				if(currentEvents.getState().equals(currentState)) {
					k++;
				}
			}
			if(k!=0)
				respons.put("Events number in "+ currentState +" ",k );
		}
		return respons;
	} 
	
	//   **************************************************************************************************
	//                        ALL VARIOUS TYPES OF JSON USED      
	//   **************************************************************************************************
	
	/**
	 * This method return a JSONObject with with the explanation of the data
	 * that we will receive as a response in the various routes
	 * 
	 * @return respons
	 */
	public JSONObject getEvents() {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();

		respons.put("event_name"," : event name");
		respons.put("event_id"," : event id");
		respons.put("local_date"," : date");
		respons.put("local_time"," : time(year/month/day)");
		respons.put("country_code"," : country code");
		respons.put("city"," : city name");
		respons.put("state"," : state name");
		respons.put("state_code"," : state code");
		respons.put("country_name"," : country name");
		respons.put("segment", " : segment");
		respons.put("genre", " : genre");
		respons.put("subgenre", " : subgenre");

		return respons;
	}

	public void ToJson(Vector<Events> filteredEvents, JSONObject respons) {
		// TODO Auto-generated method stub

		JSONArray events = new JSONArray();

		for(int i=0;i<filteredEvents.size();i++) {
			JSONObject event = new JSONObject();

			event.put("event_name", filteredEvents.get(i).getName());
			event.put("event_id", filteredEvents.get(i).getEvent_id());
			event.put("local_date", filteredEvents.get(i).getLocal_date());
			event.put("local_time", filteredEvents.get(i).getLocal_time());
			event.put("country_code", filteredEvents.get(i).getCountry_code());
			event.put("city", filteredEvents.get(i).getCity());
			event.put("state", filteredEvents.get(i).getState());
			event.put("state_code", filteredEvents.get(i).getState_code());
			event.put("country_name", filteredEvents.get(i).getCountry_name());
			event.put("segment", filteredEvents.get(i).getSegment());
			event.put("genre", filteredEvents.get(i).getGenre() );
			event.put("subgenre", filteredEvents.get(i).getSubgenre() );

			events.add(event);
		}

		respons.put("Events", events);

	}

	/**
	 * This method 
	 * 
	 * @param filteredEvents
	 * @param condition
	 * @param respons
	 */
	public void GrouppedGenreToJson(Vector<Events> filteredEvents, boolean condition, JSONObject respons) {
		// TODO Auto-generated method stub
		JSONArray eventsForGenre = new JSONArray();
		Vector<String> vectorGen = null;
		vectorGen=EventServiceImpl.readGen();
		if(condition==true)
			ToJson(filteredEvents, respons);
		
		eventsForGenre.add(genEvents(filteredEvents, vectorGen));
		
		respons.put("Events grouped by genre", eventsForGenre);

	}
	
	public void GrouppedStateToJson(Vector<Events> filteredEvents, boolean condition, JSONObject respons) {
		// TODO Auto-generated method stub
		JSONArray eventsForGenre = new JSONArray();
		Vector<String> vectorGen = null;
		vectorGen=EventServiceImpl.readState();
		if(condition==true)
			ToJson(filteredEvents, respons);
		
		eventsForGenre.add(stateEvents(filteredEvents, vectorGen));
		
		respons.put("Events grouped by state", eventsForGenre);

	}


	public JSONObject CmpToJson(Vector<Events> filteredEventsUS, Vector<Events> filteredEventsCA) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		JSONObject US = new JSONObject();
		JSONObject CA = new JSONObject();
		JSONArray eventsUS = new JSONArray();
		JSONArray eventsCA = new JSONArray();

		US.put("The total of events in US is", totEvents(filteredEventsUS)-1);
		CA.put("The total of events in Canada is", totEvents(filteredEventsCA)-1);
		US.put("The month with the most events in US is", maxEvents(filteredEventsUS));
		CA.put("The month with the most events in Canada is", maxEvents(filteredEventsCA));
		US.put("The month with the fewest events in US is", minEvents(filteredEventsUS));
		CA.put("The month with the fewest events in Canada is", minEvents(filteredEventsCA));
		US.put("The average monthly events in the US", avgEvents(filteredEventsUS));
		CA.put("The average monthly events in the Canada", avgEvents(filteredEventsCA));
		
		GrouppedStateToJson(filteredEventsUS, false, US);
		
		GrouppedStateToJson(filteredEventsCA, false, CA);
		
		eventsUS.add(US);
		eventsCA.add(CA);

		respons.put("US", eventsUS);
		
		respons.put("CA", eventsCA);

		return respons;
	}

    /**
     * This method generates statistics for a particular genre and State
     * 
     * @param filteredEvents
     * @param state_code
     * @param genre
     * @param respons
     * @throws IOException
     */
	public void StatsToJson(Vector<Events> filteredEvents, String state_code, String genre, JSONObject respons) throws IOException {
		// TODO Auto-generated method stub

		respons.put("The total of events of "+ genre+" in "+state_code+" is", totEvents(filteredEvents)-1);

		respons.put("The month with the most events of "+ genre+" in "+state_code+" is", maxEvents(filteredEvents));

		respons.put("The month with the fewest events of "+ genre+" in "+state_code+" is", minEvents(filteredEvents));

		respons.put("The average monthly events of "+ genre+" in "+state_code+" is", avgEvents(filteredEvents));		

	}

	/**
	 * 
	 * @param filteredEvents
	 * @param state_code
	 * @param respons
	 * @return
	 * @throws IOException
	 */
	public JSONObject StatsToJson(Vector<Events> filteredEvents, String state_code, JSONObject respons ) throws IOException {
		// TODO Auto-generated method stub

		respons.put("The total of events in "+state_code+" is", totEvents(filteredEvents)-1);

		respons.put("The month with the most events in "+state_code+" is", maxEvents(filteredEvents));

		respons.put("The month with the fewest events in "+state_code+" is", minEvents(filteredEvents));

		respons.put("The average monthly events in "+state_code+" is", avgEvents(filteredEvents));		
		
		GrouppedGenreToJson(filteredEvents, false, respons);

		return respons;
	}

	public JSONObject StatsToJson(Vector<Events> filteredEvents, String state_code, boolean seeEvents) throws IOException {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();

		if(seeEvents==true)
			ToJson(filteredEvents, respons);

		StatsToJson(filteredEvents, state_code, respons);

		return respons;
	}
	

	public JSONObject StatsToJson(Vector<Events> filteredEvents, String state_code, String genre, boolean seeEvents) throws IOException {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();

		if(seeEvents==true)
			ToJson(filteredEvents, respons);

		StatsToJson(filteredEvents, state_code, genre, respons);

		return respons;
	}

	public JSONObject StatsToJson_state(Vector<Events> filteredEvents1,Vector<Events> filteredEvents2, String state_code, String genre,
			String state_code2, boolean seeEvents) throws IOException {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();

		if(seeEvents==true)
			ToJson(filteredEvents1, respons);
		if(seeEvents==true)
			ToJson(filteredEvents2, respons);


		StatsToJson(filteredEvents1, state_code, genre, respons);

		StatsToJson(filteredEvents2, state_code2, genre, respons);

		return respons;
	}


	public JSONObject StatsToJson_genre(Vector<Events> filteredEvents1,Vector<Events> filteredEvents2, String state_code, String genre, String genre2,
			boolean seeEvents) throws IOException {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();

		if(seeEvents==true)
			ToJson(filteredEvents1, respons);
		if(seeEvents==true)
			ToJson(filteredEvents2, respons);


		StatsToJson(filteredEvents1, state_code, genre, respons);

		StatsToJson(filteredEvents2, state_code, genre2, respons);


		return respons;
	}


	public JSONObject StatsToJson(Vector<Events> filteredEvents1, Vector<Events> filteredEvents2,Vector<Events> filteredEvents3,Vector<Events> filteredEvents4,String state_code, String genre, String state_code2,
			String genre2, boolean seeEvents) throws IOException {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();

		if(seeEvents==true)
			ToJson(filteredEvents1, respons);
		if(seeEvents==true)
			ToJson(filteredEvents2, respons);
		if(seeEvents==true)
			ToJson(filteredEvents3, respons);
		if(seeEvents==true)
			ToJson(filteredEvents4, respons);


		StatsToJson(filteredEvents1, state_code, genre, respons);

		StatsToJson(filteredEvents2, state_code, genre2, respons);

		StatsToJson(filteredEvents3, state_code2, genre, respons);

		StatsToJson(filteredEvents4, state_code2, genre2, respons);


		return respons;
	}

}
