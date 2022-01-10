package it.univpm.exam_project.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Vector;

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
	
	
	
	
}
