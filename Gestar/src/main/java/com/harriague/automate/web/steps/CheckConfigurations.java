package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.By;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.ConfigurationsPage;
import com.harriague.automate.web.pages.SystemFolder;

public class CheckConfigurations  extends StepBase {

	private static final String CONFIG = "config";
	
	@When("abro la carpeta configuraciones...")
	public void OpenConfigFolder() throws AgentException, Exception{
		getPage(SystemFolder.class).OpenSubFolder(CONFIG);		
	}
	@Then("abro el elemento $element")
	public void OpenElement(String element)throws AgentException, Exception{
		getPage(ConfigurationsPage.class).OpenElement(element);
	}
	
	@Then ("vuelvo a la pantalla anterior")
	public void GoBack() throws AgentException, Exception {
		getPage(ConfigurationsPage.class).GoBack();
	}

	@Then("selecciono parametro $element, modificar con $valor y guardar")
	public void ChangeElement(String element, String value)throws AgentException, Exception{
		getPage(ConfigurationsPage.class).ChangeElement(element, value);
	}
	
	
}
