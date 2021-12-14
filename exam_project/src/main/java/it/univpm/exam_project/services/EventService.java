package it.univpm.exam_project.services;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

public interface EventService {
	// number of events for month
	public int[] numEvents(Vector<Events> eventVector);
	//average month events
	public void avgEvents(int[] numEvents);
	//sort the months from the one with the least events to the one with the most events
	public int[] sortSelectedEvents(int[] numEvents);
	//min events for month
	public void minEvents(int[] numEvents);
	//max events for month
	public void maxEvents(int[] numEvents);
}
