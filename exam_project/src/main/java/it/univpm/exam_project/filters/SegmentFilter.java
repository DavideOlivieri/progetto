package it.univpm.exam_project.filters;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

/**
 * @author DavideOlivieri
 * 
 * Class that filters all events by segment
 */

public class SegmentFilter {

	/**
	 * Method that filters all events by segment
	 * 
	 * @param segment, the segment for which to filter events
	 * @param eventstoFilter, all events to be filtered
	 * @return filteredEvents, events filtered for the chosen segment
	 * @see it.univpm.exam_project.models.Events
	 */
	
	public Vector<Events> SegFilter(String segment, Vector<Events> eventstoFilter){

		Vector<Events> filteredEvents = new Vector<Events>();

		for(Events event : eventstoFilter) {

			if(segment.equals(event.getSegment()))
				filteredEvents.add(event);

		}
		return filteredEvents;
	}
}
