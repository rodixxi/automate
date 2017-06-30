package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.When;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.LoginPage;

public class LoginSteps extends StepBase {
	
	@When("me conecto a gestar...")
	public void veoPronostico() throws AgentException, Exception{
		log.info("me conecto a gestar...");
		getPage(LoginPage.class).hacerLogin();
	}
	@When("me conecto a gestar con el usuario: $usuario la pass $pwd a la instancia $instance")
	public void loginGestar(String usuario, String pwd, String instance) throws InterruptedException, Exception {
		getPage(LoginPage.class).hacerLogin(usuario, pwd, instance);
	}
}
