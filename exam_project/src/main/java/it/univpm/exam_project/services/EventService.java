package it.univpm.exam_project.services;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

public interface EventService {
	// number of events for month
	public int[] numEvents(Vector<Events> eventVector);
	//average month events
	public float avgEvents(Vector<Events> eventVector);
	//min events for month
	public int minEvents(Vector<Events> eventVector);
	//max events for month
}
