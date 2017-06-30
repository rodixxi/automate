package com.harriague.automate.web.pages;

 
import com.harriague.automate.core.exceptions.AgentException;
//import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

public interface PlanningWeekPage extends Page {
	
	void SelectWeek( ) throws AgentException ;

	void SelectTeam(String team) throws AgentException ;

	void BotonPlanificar() throws AgentException ;

}
