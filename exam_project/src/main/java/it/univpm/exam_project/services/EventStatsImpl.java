package it.univpm.exam_project.services;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

public class EventStatsImpl implements EventStats {
	
	EventServiceImpl service = new EventServiceImpl();
	
	public int[] numEvents(Vector<Events> eventVector) {

		int[] monthEvents = new int[12];
		Events currentEvents;
		for(int i = 0; i<eventVector.size();i++) {
			currentEvents = eventVector.get(i);

			for(int j = 1; j <= 12; j++) {
				if(currentEvents.getMonth()==j) {
					monthEvents[j-1]++;
				}
			}
		}
		return monthEvents;
	} 
	
	/**
	 * Method that return the average of events of all months
	 */
	public float avgEvents(Vector<Events> eventVector) {
		float avgevents=0;
		int[] ev=numEvents(eventVector);
		for(int i = 0; i<12;i++) {
			avgevents += ev[i];			
		}
		avgevents/=12;
		return avgevents;
	}
    
	
	/**
	 * Method that return the minimum of events for each month
	 */
	public String minEvents(Vector<Events> eventVector) {
		int app = 10000;
		int j=0;
		String month;
		int[] ev=numEvents(eventVector);
		for(int i = 0; i<12;i++) {
			if(ev[i] < app) {
				app = ev[i];
				j=i;
			}
		}
		month=service.convertMonth(j+1);
		return month;
	}


	
	/**
	 * Method that return the maximum of events for each month
	 */
	public String maxEvents(Vector<Events> eventVector) {
		int tot = 0;
		int j=0;
		String month;
		int[] ev=numEvents(eventVector);
		for(int i = 0; i<12;i++) {
			if(ev[i] > tot) {
				tot =  ev[i];
				j=i;
			}
		}
		month=service.convertMonth(j+1);
		return month;
	}
	
	/**
	 * Method that returns the total of events
	 */
	public int totEvents(Vector<Events> eventVector) {
		int tot=0;
		for(int i=0; i<=eventVector.size();i++) {
			tot++;
		}
		
		return tot;
	}

}
