package it.univpm.exam_project.services;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

public interface EventStats {

	public int[] numEvents(Vector<Events> eventVector);
	
	public float avgEvents(Vector<Events> eventVector);
	
	public String minEvents(Vector<Events> eventVector);
	
	public String maxEvents(Vector<Events> eventVector);
	
	public int totEvents(Vector<Events> eventVector);
}
