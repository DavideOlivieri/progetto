package it.univpm.exam_project.services;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.exam_project.models.Events;

public interface EventToJson {
	
	public JSONObject getEvents();
	
	public JSONObject ToJson(Vector<Events> filteredEvents);
	
	public JSONObject CmpToJson(Vector<Events> filteredEventsUS, Vector<Events> filteredEventsCA);
	
	public JSONObject StatsToJson(Vector<Events> filteredEvents, String state_code, String genre, boolean seeEvents);
	
	public JSONObject StatsToJson_state(Vector<Events> filteredEvents, String state_code, String genre, String state_code2, boolean seeEvents);
	
	public JSONObject StatsToJson_genre(Vector<Events> filteredEvents, String state_code, String genre, String genre2, boolean seeEvents);
	
	public JSONObject StatsToJson(Vector<Events> filteredEvents, String state_code, String genre, String state_code2, String genre2, boolean seeEvents);
}
