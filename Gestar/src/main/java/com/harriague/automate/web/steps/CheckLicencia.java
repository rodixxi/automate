
package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.By;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.SystemFolder;
import com.harriague.automate.web.pages.LicensePage;

public class CheckLicencia extends StepBase {
	
	private static final String LICENCIA = "licencia";

	@When("abrir carpeta de Sistemas...")
	public void OpenSystemFolder() throws AgentException, Exception{
		log.info("abro el folder de sistema");
		getPage(SystemFolder.class).OpenFolder();			
	}
	
	@When("abrir carpeta licencia...")
	public void OpenLicenceFolder() throws AgentException, Exception{
		log.info("abro el folder de licencias");
		getPage(SystemFolder.class).OpenSubFolder(LICENCIA);
			
	}
	
	@Then ("revisar grilla licenca")
	public void CheckLicenceGrid()throws AgentException, Exception{
		log.info("reviso la grilla de licencias");
		getPage(LicensePage.class).CheckGridLicense();
	}
	

}
