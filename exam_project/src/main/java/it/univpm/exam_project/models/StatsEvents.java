package it.univpm.exam_project.models;

import java.util.Vector;

import it.univpm.exam_project.services.EventServiceImpl;

public class StatsEvents {
	private float avgEvent;
	private int[] numEvents;
	private int minEvents;
	private int maxEvents;
	
	public StatsEvents(Vector<Events> eventVector) {
		EventServiceImpl statistic = new EventServiceImpl();
		this.avgEvent=statistic.avgEvents(eventVector);
		this.numEvents=statistic.numEvents(eventVector);
		this.minEvents=statistic.minEvents(eventVector);
		this.maxEvents=statistic.maxEvents(eventVector);
	}
	public float getAvgEvent() {
		return avgEvent;
	}
	public void setAvgEvent(float avgEvent) {
		this.avgEvent = avgEvent;
	}
	public int[] getNumEvents() {
		return numEvents;
	}
	public void setNumEvents(int[] numEvents) {
		this.numEvents = numEvents;
	}
	public int getMinEvents() {
		return minEvents;
	}
	public void setMinEvents(int minEvents) {
		this.minEvents = minEvents;
	}
	public int getMaxEvents() {
		return maxEvents;
	}
	public void setMaxEvents(int maxEvents) {
		this.maxEvents = maxEvents;
	}
}
