package it.univpm.exam_project.filters;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

public class StatesFilter {
 
	public Vector<Events> stateFilter(String state, Vector<Events> statetsoFilter){

		Vector<Events> filteredStates = new Vector<Events>();

		for(Events event : statetsoFilter) {

			if(state.equals(event.getState()))
				filteredStates.add(event);

		}
		return filteredStates;
	}

}
