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
	
}
