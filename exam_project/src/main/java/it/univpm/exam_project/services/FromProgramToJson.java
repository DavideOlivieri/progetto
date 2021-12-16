package it.univpm.exam_project.services;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.exam_project.filters.GenreFilter;
import it.univpm.exam_project.filters.StatesFilter;
import it.univpm.exam_project.models.Events;

public class FromProgramToJson {
		private EventServiceImpl services= new EventServiceImpl();
	
		//dato un genere restituisce le statistiche
		public JSONObject FromGenreToJson(String genre, Vector<Events> eventVector) {
			JSONObject respons = new JSONObject();
			int tot=0;
			Vector<Events> filteredEvents = new Vector<Events>();
			GenreFilter filterVector = new GenreFilter();
			filteredEvents = filterVector.genFilter(genre, eventVector);
			tot=services.totEvents(filteredEvents);
			for(int i=0;i<eventVector.size();i++) {
				respons.put("event_name", eventVector.get(i).getName());
				respons.put("event_id", eventVector.get(i).getEvent_id());
				respons.put("local_date", eventVector.get(i).getLocal_date());
				respons.put("local_time", eventVector.get(i).getLocal_time());
				respons.put("country_code", eventVector.get(i).getCountry_code());
				respons.put("city", eventVector.get(i).getCity());
				respons.put("state", eventVector.get(i).getState());
				respons.put("country_name", eventVector.get(i).getCountry_name());
			}
			respons.put("Eventi_Totali", tot);
			return respons;
		}
		//dato un paese restituisce le statistiche
		public JSONObject FromCountryToJson(String country, Vector<Events> eventVector) {
			JSONObject respons = new JSONObject();
			int tot=0;
			Vector<Events> filteredEvents = new Vector<Events>();
			StatesFilter filterVector = new StatesFilter();
			filteredEvents = filterVector.stateFilter(country, eventVector);
			tot=services.totEvents(filteredEvents);
			for(int i=0;i<eventVector.size();i++) {
				respons.put("event_name", eventVector.get(i).getName());
				respons.put("event_id", eventVector.get(i).getEvent_id());
				respons.put("local_date", eventVector.get(i).getLocal_date());
				respons.put("local_time", eventVector.get(i).getLocal_time());
				respons.put("country_code", eventVector.get(i).getCountry_code());
				respons.put("city", eventVector.get(i).getCity());
				respons.put("state", eventVector.get(i).getState());
				respons.put("country_name", eventVector.get(i).getCountry_name());
			}
			respons.put("Eventi_Totali", tot);
			return respons;
		}
}
