package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.Then;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.LoginPage;

public class LogoutSteps extends StepBase {
	
	@Then("me desconecto de gestar...")
	public void veoPronostico() throws AgentException, Exception{
		log.info(" dentro de veo pronostico me desconecto de gestar...");
		getPage(LoginPage.class).hacerLogout();
	}
}
