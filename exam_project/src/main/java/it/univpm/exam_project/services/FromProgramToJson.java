package it.univpm.exam_project.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.exam_project.models.Events;

public class FromProgramToJson {
	
		//dato un evento restituisce il suo Json
		public JSONObject FromEventToJson(Events event) {
			JSONObject respons = new JSONObject();
			respons.put("event_name", event.getName());
			respons.put("event_id", event.getEvent_id());
			respons.put("local_date", event.getLocal_date());
			respons.put("local_time", event.getLocal_time());
			respons.put("country_code", event.getCountry_code());
			respons.put("city", event.getCity());
			respons.put("state", event.getState());
			respons.put("country_name", event.getCountry_name());
			respons.put("segment", event.getSegment());
			respons.put("genre", event.getGenre());
			
			return respons;
		}
		//dato un genere restituisce le statiatiche
		public JSONObject FromGenreToJson(Events event) {
			JSONObject respons = new JSONObject();
			
			return respons;
		}
		//dato un paese restituisce le statistiche
		public JSONObject FromCountryToJson(Events event) {
			JSONObject respons = new JSONObject();
			
			return respons;
		}
}
