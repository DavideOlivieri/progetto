package it.univpm.exam_project.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

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
	
    /**
     * Given a country it returns the statistic for it
     * 
     * @param country
     * @param eventVector
     * @return respons
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
	
	
	private static String apiUrl="https://app.ticketmaster.com/discovery/v2/events.json?apikey=ojDlNPpgliPJgnuvATaFreLEiAEzHTcC";
	
	 
	public Vector<Events> connection_country(String countryCode){
		Vector<Events> eventVector = new Vector<Events>();
		
		String url = apiUrl+"&countryCode="+countryCode;
		JSONObject json = getJSONObject(url);
		Parser pars = new Parser();		
		eventVector = pars.parse(json);
		
		return eventVector;
	}
	
	
	public Vector<Events> connection_segment(String segment){
		Vector<Events> eventVector = new Vector<Events>();
		
		String url = apiUrl+"&segmentName="+segment;
		JSONObject json = getJSONObject(url);
		Parser pars = new Parser();
		eventVector = pars.parse(json);

		return eventVector;
	}

	
	public Vector<Events> connection_genre(String genre){
		Vector<Events> eventVector = new Vector<Events>();
		
		String url = apiUrl+"&classificationName="+genre;
		JSONObject json = getJSONObject(url);
		Parser pars = new Parser();
		eventVector = pars.parse(json);

		return eventVector;
	}
	
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
	
	
	public static String[] readGen(String txt) throws IOException {
		String[] gen=null;
		FileReader f=new FileReader(txt);

	    BufferedReader b=new BufferedReader(f);

	    String s;
	    int i=0;
	    while(true) {
	      s=b.readLine();
	    
	      if(s==null)
	        break;
	      gen[i]=s;
	      i++;
	    }
		return gen;
	}

	public int[] numEvents(Vector<Events> eventVector) {

		int[] monthEvents = new int[12];
		Events currentEvents;
		for(int i = 0; i<eventVector.size();i++) {
			currentEvents = eventVector.get(i);

			for(int j = 1; j <= 12; j++) {
				if(currentEvents.getMonth()==j) {
					monthEvents[j-1]++;
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
		int[] ev=numEvents(eventVector);
		for(int i = 0; i<12;i++) {
			avgevents += ev[i];			
		}
		avgevents/=12;
		return avgevents;
	}
    
	
	/**
	 * Method that return the minimum of events for each month
	 */
	public String minEvents(Vector<Events> eventVector) {
		int app = 10000;
		int j=0;
		String month;
		int[] ev=numEvents(eventVector);
		for(int i = 0; i<12;i++) {
			if(ev[i] < app) {
				app = ev[i];
				j=i;
			}
		}
		month=convertMonth(j+1);
		return month;
	}


	
	/**
	 * Method that return the maximum of events for each month
	 */
	public String maxEvents(Vector<Events> eventVector) {
		int tot = 0;
		int j=0;
		String month;
		int[] ev=numEvents(eventVector);
		for(int i = 0; i<12;i++) {
			if(ev[i] > tot) {
				tot =  ev[i];
				j=i;
			}
		}
		month=convertMonth(j+1);
		return month;
	}
	
	/**
	 * Method that returns the total of events
	 */
	public int totEvents(Vector<Events> eventVector) {
		int tot=0;
		for(int i=0; i<=eventVector.size();i++) {
			tot++;
		}
		
		return tot;
	}
	
	public JSONObject genEvents(Vector<Events> eventVector, String[] genre) {

		JSONObject respons = new JSONObject();
		int k=0;
		Events currentEvents;
		for(int j = 0; j < genre.length; j++) {
			
			for(int i = 0; i<eventVector.size();i++) {
				
				currentEvents = eventVector.get(i);
				if(currentEvents.getGenre().equals(genre[j])) {
					k++;
				}
			}
			respons.put("Events number of "+ genre[j]+" ",k );
		}
		return respons;
	} 

	
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

	
	public JSONObject ToJson(Vector<Events> filteredEvents) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
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
		return respons;
	}

	
	public JSONObject CmpToJson(Vector<Events> filteredEventsUS, Vector<Events> filteredEventsCA) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
		respons.put("The total of events in US is", totEvents(filteredEventsUS)-1);
		respons.put("The total of events in Canada is", totEvents(filteredEventsCA)-1);
		respons.put("The month with the most events in US is", maxEvents(filteredEventsUS));
		respons.put("The month with the most events in Canada is", maxEvents(filteredEventsCA));
		respons.put("The month with the fewest events in US is", minEvents(filteredEventsUS));
		respons.put("The month with the fewest events in Canada is", minEvents(filteredEventsCA));
		respons.put("The average monthly events in the US", avgEvents(filteredEventsUS));
		respons.put("The average monthly events in the Canada", avgEvents(filteredEventsCA));

		return respons;
	}

	
	public JSONObject StatsToJson(Vector<Events> filteredEvents, String state_code, String genre, boolean seeEvents) throws IOException {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
		if(seeEvents==true)
			respons=ToJson(filteredEvents);
		
		respons.put("The total of events of "+ genre+" in "+state_code+" is", totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code+" is", maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code+" is", minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code+" is", avgEvents(filteredEvents));
		
		respons=genEvents(filteredEvents, EventServiceImpl.readGen("C:/Users/davio/OneDrive/Documenti/GitHub/progetto/exam_project/src/main/java/it/univpm/exam_project/services/generi.txt"));
		

		return respons;
	}

	
	public JSONObject StatsToJson_state(Vector<Events> filteredEvents, String state_code, String genre,
			String state_code2, boolean seeEvents) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
		if(seeEvents==true)
			respons=ToJson(filteredEvents);
			
		respons.put("The total of events of "+ genre+" in "+state_code+" is", totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code+" is", maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code+" is", minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code+" is", avgEvents(filteredEvents));
		
		respons.put("The total of events of "+ genre+" in "+state_code2+" is", totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code2+" is", maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code2+" is", minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code2+" is", avgEvents(filteredEvents));
		

		return respons;
	}

	
	public JSONObject StatsToJson_genre(Vector<Events> filteredEvents, String state_code, String genre, String genre2,
			boolean seeEvents) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
		if(seeEvents==true)
			respons=ToJson(filteredEvents);
			
		respons.put("The total of events of "+ genre+" in "+state_code+" is", totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code+" is", maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code+" is", minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code+" is", avgEvents(filteredEvents));
		
		respons.put("The total of events of "+ genre2+" in "+state_code+" is", totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre2+" in "+state_code+" is", maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre2+" in "+state_code+" is", minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre2+"in "+state_code+" is", avgEvents(filteredEvents));
		

		return respons;
	}


	public JSONObject StatsToJson(Vector<Events> filteredEvents, String state_code, String genre, String state_code2,
			String genre2, boolean seeEvents) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();

		if(seeEvents==true)
			respons=ToJson(filteredEvents);

		respons.put("The total of events of "+ genre+" in "+state_code+" is", totEvents(filteredEvents)-1);

		respons.put("The month with the most events of "+ genre+" in "+state_code+" is", maxEvents(filteredEvents));

		respons.put("The month with the fewest events of "+ genre+" in "+state_code+" is", minEvents(filteredEvents));

		respons.put("The average monthly events of "+ genre+"in "+state_code+" is", avgEvents(filteredEvents));

		respons.put("The total of events of "+ genre2+" in "+state_code+" is", totEvents(filteredEvents)-1);

		respons.put("The month with the most events of "+ genre2+" in "+state_code+" is", maxEvents(filteredEvents));

		respons.put("The month with the fewest events of "+ genre2+" in "+state_code+" is", minEvents(filteredEvents));

		respons.put("The average monthly events of "+ genre2+"in "+state_code+" is", avgEvents(filteredEvents));

		respons.put("The total of events of "+ genre+" in "+state_code2+" is", totEvents(filteredEvents)-1);

		respons.put("The month with the most events of "+ genre+" in "+state_code2+" is", maxEvents(filteredEvents));

		respons.put("The month with the fewest events of "+ genre+" in "+state_code2+" is", minEvents(filteredEvents));

		respons.put("The average monthly events of "+ genre+"in "+state_code2+" is", avgEvents(filteredEvents));

		respons.put("The total of events of "+ genre2+" in "+state_code2+" is", totEvents(filteredEvents)-1);

		respons.put("The month with the most events of "+ genre2+" in "+state_code2+" is", maxEvents(filteredEvents));

		respons.put("The month with the fewest events of "+ genre2+" in "+state_code2+" is", minEvents(filteredEvents));

		respons.put("The average monthly events of "+ genre2+"in "+state_code2+" is", avgEvents(filteredEvents));


		return respons;
	}

}
