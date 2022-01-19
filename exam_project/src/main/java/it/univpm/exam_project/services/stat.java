package it.univpm.exam_project.services;

import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.exam_project.models.Events;
import it.univpm.exam_project.models.EventsUE;

/**
 * 
 * @author Jacopo Coloccioni
 * @author Davide Olivieri
 *
 */
public class stat {
	
	//  **************************************************************************************************
	//                        METHODS USED TO MAKE STATISTICS      
	//   **************************************************************************************************

   /**
    * This method shows the number of events for each month
    * 
    * @param eventVector
    * @param monthEvents
    */
	public static void numEvents(Vector<Events> eventVector, int[]monthEvents) {
		Events currentEvents;
		for(int i = 0; i<eventVector.size();i++) {
			currentEvents = eventVector.get(i);

			for(int j = 1; j <= 12; j++) {
				if(currentEvents.getMonth()==j) {
					monthEvents[j-1]++;
				}
			}
		}

	} 

	/**
	 * Method that return the average of events of all months.
	 * 
	 * @param eventVector
	 * @return average events
	 */
	public static float avgEvents(Vector<Events> eventVector) {
		float avgevents=0;
		int[] ev = new int[12];
		numEvents(eventVector, ev);
		for(int i = 0; i<12;i++) {
			avgevents += ev[i];			
		}
		avgevents/=12;
		return avgevents;
	}


   /**
    * Method that returns the month with the fewest events
    * 
    * @param eventVector, Vector with all the events inside
    * @return month, month with the fewest events.
    */
	public static String minEvents(Vector<Events> eventVector) {
		int app = 10000;
		String month="";
		int j=0;
		int[] ev = new int[12];
		numEvents(eventVector, ev);
		for(int i = 0; i<12;i++) {
			if(ev[i] < app) {
				app = ev[i];
				j=i;
			}
	
		}
		month+=EventServiceImpl.convertMonth(j+1);
		
		for(int k = j+1; k<12; k++) {
			if(ev[k] == app) {
					month+=", "+EventServiceImpl.convertMonth(k+1);
			}
	
		}
		
		return month;
	}



	/**
	 * Method that returns the month with the most events
	 * 
	 * @param eventVector
	 * @return month, month with the most events.
	 */
	public static String maxEvents(Vector<Events> eventVector) {
		int tot = 0;
		int j=-1;
		String month;
		int[] ev = new int[12];
		numEvents(eventVector, ev);
		for(int i = 0; i<12;i++) {
			if(ev[i] > tot) {
				tot =  ev[i];
				j=i;
			}
		}
		if(j==-1)
			month="No month has more than 0 events";
		else
			month=EventServiceImpl.convertMonth(j+1);
		return month;
	}

   /**
    * Method that returns the total of events
    * 
    * @param eventVector
    * @return total, the total of events inside the Vector.
    */
	public static int totEvents(Vector<Events> eventVector) {
		int tot=0;
		for(int i=0; i<=eventVector.size();i++) {
			tot++;
		}

		return tot;
	}
	
	/**
	 * This method displays the events grouped by genre.
	 * 
	 * @param eventVector
	 * @param genre
	 * @return response
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject genEventsUE(Vector<EventsUE> eventVector, Vector<String> genre) {

		JSONObject response = new JSONObject();
		EventsUE currentEvents;
		String currentGenre;
		for(int j=0; j<genre.size();j++) {
			int k=0;
			currentGenre= genre.get(j);
			for(int i = 0; i<eventVector.size();i++) {
				currentEvents = eventVector.get(i);
				if(currentEvents.getGenre().equals(currentGenre)) {
					k++;
				}
			}
			if(k!=0)
				response.put("Events number of "+ currentGenre +" ",k );
		}
		return response;
	} 
	
	@SuppressWarnings("unchecked")
	public static JSONObject genEvents(Vector<Events> eventVector, Vector<String> genre) {

		JSONObject response = new JSONObject();
		Events currentEvents;
		String currentGenre;
		for(int j=0; j<genre.size();j++) {
			int k=0;
			currentGenre= genre.get(j);
			for(int i = 0; i<eventVector.size();i++) {
				currentEvents = eventVector.get(i);
				if(currentEvents.getGenre().equals(currentGenre)) {
					k++;
				}
			}
			if(k!=0)
				response.put("Events number of "+ currentGenre +" ",k );
		}
		return response;
	} 

	/**
	 * This method displays the events grouped by State.
	 * 
	 * @param eventVector
	 * @param stateName
	 * @return response
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject stateEvents(Vector<Events> eventVector, Vector<String> stateName) {

		JSONObject response = new JSONObject();
		Events currentEvents;
		String currentState;
		for(int j=0; j<stateName.size();j++) {
			int k=0;
			currentState= stateName.get(j);
			for(int i = 0; i<eventVector.size();i++) {
				currentEvents = eventVector.get(i);
				if(currentEvents.getState().equals(currentState)) {
					k++;
				}
			}
			if(k!=0)
				response.put("Events number in "+ currentState +" ",k );
		}
		return response;
	} 
	

}
