package it.univpm.exam_project.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import it.univpm.exam_project.models.Events;
import it.univpm.exam_project.parser.Parser;

public class Api_connection {
	public static Vector<Events> connection_country(String countryCode){
		Vector<Events> eventVector = new Vector<Events>();
		String apiUrl="https://app.ticketmaster.com/discovery/v2/events.json?ojDlNPpgliPJgnuvATaFreLEiAEzHTcC";
		try {
			URL url = new URL(apiUrl+"&countryCode="+countryCode);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));		
			String json = input.readLine();		
			Parser pars = new Parser();		
			eventVector = pars.parse(json);
		}catch (IOException e) {
			e.getMessage();
		}
		return eventVector;
	}
	public static Vector<Events> connection_genre(String classificationName){
		Vector<Events> eventVector = new Vector<Events>();
		String apiUrl="https://app.ticketmaster.com/discovery/v2/events.json?ojDlNPpgliPJgnuvATaFreLEiAEzHTcC";
		try {
			URL url = new URL(apiUrl+"classificationName="+classificationName);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));		
			String json = input.readLine();		
			Parser pars = new Parser();		
			eventVector = pars.parse(json);
		}catch (IOException e) {
			e.getMessage();
		}
		return eventVector;
	}
}
