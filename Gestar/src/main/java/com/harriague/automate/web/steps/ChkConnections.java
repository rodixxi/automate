package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.ConnectionsPage;
import com.harriague.automate.web.pages.SystemFolder;


public class ChkConnections extends StepBase {
	
private static final String CHANGE_CONN = "chk_conn";
	
	@When("abro la carpeta conexiones...")
	public void abrirPaginaContrasenia() throws AgentException, Exception{
		log.info("me conecto a gestar...");
		getPage(SystemFolder.class).OpenSubFolder(CHANGE_CONN);
	} 
	
	@Then("chequeo conexiones...")
	public void CheckConexiones()  throws AgentException, Exception {
		log.info("chequeo conexiones...");
		getPage(ConnectionsPage.class).CheckConnections();
	}

}
