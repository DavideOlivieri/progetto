package it.univpm.exam_project.services;

import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.exam_project.models.Events;

public class EventToJsonImpl implements EventToJson{
	
	EventStatsImpl service = new EventStatsImpl();

	@Override
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

	@Override
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

	@Override
	public JSONObject CmpToJson(Vector<Events> filteredEventsUS, Vector<Events> filteredEventsCA) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
		respons.put("The total of events in US is", service.totEvents(filteredEventsUS)-1);
		respons.put("The total of events in Canada is", service.totEvents(filteredEventsCA)-1);
		respons.put("The month with the most events in US is", service.maxEvents(filteredEventsUS));
		respons.put("The month with the most events in Canada is", service.maxEvents(filteredEventsCA));
		respons.put("The month with the fewest events in US is", service.minEvents(filteredEventsUS));
		respons.put("The month with the fewest events in Canada is", service.minEvents(filteredEventsCA));
		respons.put("The average monthly events in the US", service.avgEvents(filteredEventsUS));
		respons.put("The average monthly events in the Canada", service.avgEvents(filteredEventsCA));

		return respons;
	}

	@Override
	public JSONObject StatsToJson(Vector<Events> filteredEvents, String state_code, String genre, boolean seeEvents) throws IOException {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
		if(seeEvents==true)
			respons=ToJson(filteredEvents);
		
		respons.put("The total of events of "+ genre+" in "+state_code+" is", service.totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code+" is", service.maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code+" is", service.minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code+" is", service.avgEvents(filteredEvents));
		
		respons=service.genEvents(filteredEvents, EventServiceImpl.readGen("generi.txt"));
		

		return respons;
	}

	@Override
	public JSONObject StatsToJson_state(Vector<Events> filteredEvents, String state_code, String genre,
			String state_code2, boolean seeEvents) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
		if(seeEvents==true)
			respons=ToJson(filteredEvents);
			
		respons.put("The total of events of "+ genre+" in "+state_code+" is", service.totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code+" is", service.maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code+" is", service.minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code+" is", service.avgEvents(filteredEvents));
		
		respons.put("The total of events of "+ genre+" in "+state_code2+" is", service.totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code2+" is", service.maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code2+" is", service.minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code2+" is", service.avgEvents(filteredEvents));
		

		return respons;
	}

	@Override
	public JSONObject StatsToJson_genre(Vector<Events> filteredEvents, String state_code, String genre, String genre2,
			boolean seeEvents) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
		if(seeEvents==true)
			respons=ToJson(filteredEvents);
			
		respons.put("The total of events of "+ genre+" in "+state_code+" is", service.totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code+" is", service.maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code+" is",service.minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code+" is", service.avgEvents(filteredEvents));
		
		respons.put("The total of events of "+ genre2+" in "+state_code+" is", service.totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre2+" in "+state_code+" is", service.maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre2+" in "+state_code+" is", service.minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre2+"in "+state_code+" is",service.avgEvents(filteredEvents));
		

		return respons;
	}

	@Override
	public JSONObject StatsToJson(Vector<Events> filteredEvents, String state_code, String genre, String state_code2,
			String genre2, boolean seeEvents) {
		// TODO Auto-generated method stub
		JSONObject respons = new JSONObject();
		
		if(seeEvents==true)
			respons=ToJson(filteredEvents);
			
		respons.put("The total of events of "+ genre+" in "+state_code+" is", service.totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code+" is",service.maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code+" is", service.minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code+" is", service.avgEvents(filteredEvents));
		
respons.put("The total of events of "+ genre2+" in "+state_code+" is", service.totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre2+" in "+state_code+" is", service.maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre2+" in "+state_code+" is", service.minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre2+"in "+state_code+" is", service.avgEvents(filteredEvents));
		
respons.put("The total of events of "+ genre+" in "+state_code2+" is", service.totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre+" in "+state_code2+" is", service.maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre+" in "+state_code2+" is", service.minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre+"in "+state_code2+" is", service.avgEvents(filteredEvents));
		
respons.put("The total of events of "+ genre2+" in "+state_code2+" is", service.totEvents(filteredEvents)-1);
		
		respons.put("The month with the most events of "+ genre2+" in "+state_code2+" is",service.maxEvents(filteredEvents));
		
		respons.put("The month with the fewest events of "+ genre2+" in "+state_code2+" is", service.minEvents(filteredEvents));
		
		respons.put("The average monthly events of "+ genre2+"in "+state_code2+" is", service.avgEvents(filteredEvents));
		

		return respons;
	}
	
}
