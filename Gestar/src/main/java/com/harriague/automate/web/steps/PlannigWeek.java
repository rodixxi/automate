package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase; 
import com.harriague.automate.web.pages.PlanningWeekPage;


public class PlannigWeek extends StepBase  {
 
	@Then ("seleccionar lunes de la semana en curso")
	public void SeleccionoDia() throws AgentException,  Exception{
		getPage(PlanningWeekPage.class).SelectWeek();
	}

	@Then ("seleccionar equipo $team")
	public void SeleccionoEquipo(String team) throws AgentException,  Exception{
		getPage(PlanningWeekPage.class).SelectTeam(team);
	}

	@Then("apreto planificar")
	public void PlanificarSemana() throws AgentException,  Exception{
		getPage(PlanningWeekPage.class).BotonPlanificar();
	}

}
