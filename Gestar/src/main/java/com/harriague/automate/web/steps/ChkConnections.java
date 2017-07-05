package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.ConnectionsPage;
import com.harriague.automate.web.pages.SystemFolder;
import org.testng.annotations.AfterClass;


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

	//@AfterScenario
    public void closeBrowser(){
        log.info("cerrando broser ...");
        try {
            closeApplication();
            log.info("... browser cerrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
