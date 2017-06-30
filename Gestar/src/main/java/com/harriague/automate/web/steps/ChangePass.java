package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.ChangePassPage;
import com.harriague.automate.web.pages.SystemFolder;

public class ChangePass extends StepBase {
	
	private static final String CHANGE_PASS = "chk_pass";
	
	@When("abro la carpeta cambiar contra...")
	public void abrirPaginaContrasenia() throws AgentException, Exception{
		log.info("me conecto a gestar...");
		getPage(SystemFolder.class).OpenSubFolder(CHANGE_PASS);
	} 
	
	@Then("completar los campos contras...")
	public void completoCampos() throws AgentException, Exception{
		log.info("me conecto a gestar...");
		getPage(ChangePassPage.class).ChangeInfo();
	}
}
