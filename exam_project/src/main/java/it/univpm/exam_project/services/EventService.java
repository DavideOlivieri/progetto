package it.univpm.exam_project.services;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

/**
 * 
 * @author DavideOlivieri
 * @author JacopoColoccioni
 *
 */
public interface EventService {
	// number of events for month
	public int[] numEvents(Vector<Events> eventVector);
	//average month events
	public float avgEvents(Vector<Events> eventVector);
	//min events for month
	public String minEvents(Vector<Events> eventVector);
	//max events for month
	public String maxEvents(Vector<Events> eventVector);
	//numero di eventi totali 
	public int totEvents(Vector<Events> eventVector);
	
}
