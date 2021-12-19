package it.univpm.exam_project.filters;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

public class StatesFilter {
 
	public Vector<Events> stateFilter(String countryCode, Vector<Events> statetsoFilter){

		Vector<Events> filteredStates = new Vector<Events>();

		for(Events event : statetsoFilter) {

			if(countryCode.equals(event.getCountry_code()))
				filteredStates.add(event);

		}
		return filteredStates;
	}

}
