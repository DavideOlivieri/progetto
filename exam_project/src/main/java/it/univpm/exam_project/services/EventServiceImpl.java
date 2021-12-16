package it.univpm.exam_project.services;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

public class EventServiceImpl implements EventService{

	public int[] numEvents(Vector<Events> eventVector) {

		int[] monthEvents = new int[12];
		for(int i = 0; i<eventVector.size();i++) {
			Events currentEvents = eventVector.get(i);

			for(int j = 1; j <= 12; j++) {
				String j_string = Integer.toString(j);
				if(currentEvents.getMonth().equals(j_string)) {
					monthEvents[j]++;
				}
			}
		}
		return monthEvents;
	}

	public float avgEvents(Vector<Events> eventVector) {
		float avgevents=0;
		for(int i = 0; i<12;i++) {
			avgevents += numEvents(eventVector)[i];			
		}
		avgevents/=12;
		return avgevents;
	}

	public int minEvents(Vector<Events> eventVector) {
		int app = 0;
		for(int i = 0; i<12;i++) {
			if(numEvents(eventVector)[i] <= app) {
				app = numEvents(eventVector)[i];
			}
		}
		return app;
	}
	
	public int maxEvents(Vector<Events> eventVector) {
		int app = 0;
		for(int i = 0; i<12;i++) {
			if(numEvents(eventVector)[i] >= app) {
				app = numEvents(eventVector)[i];
			}
		}
		return app;
	}
	
	public int totEvents(Vector<Events> eventVector) {
		int app=0;
		for(int i=0; i<eventVector.size();i++) {
			app++;
		}
		return app;
	}

}
