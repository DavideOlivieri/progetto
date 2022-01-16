package it.univpm.exam_project.services;

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
	
	public void numEvents(Vector<Events> eventVector, int[]monthEvents);
	
	public float avgEvents(Vector<Events> eventVector);
	
	public String minEvents(Vector<Events> eventVector);
	
	public String maxEvents(Vector<Events> eventVector);
	
	public int totEvents(Vector<Events> eventVector);
	
}
