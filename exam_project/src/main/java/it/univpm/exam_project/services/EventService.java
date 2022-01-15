package it.univpm.exam_project.services;

import java.time.LocalDate;
import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.exam_project.models.Events;

/**
 * 
 * @author DavideOlivieri
 * @author JacopoColoccioni
 *
 */
public interface EventService {
	
	public JSONObject getJSONObject(String url);
	
	public Vector<Events> connection_country(String countryCode);
	
	public Vector<Events> connection_segment(String segment);
	
	public Vector<Events> connection_genre(String genre);
	
	public Vector<Events> connection_state(String state_code);
	
	public Vector<Events> concateneted(Vector<Events> vector1, Vector<Events> vector2);
	
	public static LocalDate dateConverter(String localDate) {
		LocalDate locD = LocalDate.parse((CharSequence)localDate);
		return locD;
	}
	
	public static String convertMonth(int i) {
		switch(i) {
			case 1:
				return "January";
			case 2:
				return "February";
			case 3:
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6:
				return "June";
			case 7:
				return "July";
			case 8:
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11:
				return "November";
			case 12:
				return "December";
			default:
				return "Error month";
		}
	}
}
