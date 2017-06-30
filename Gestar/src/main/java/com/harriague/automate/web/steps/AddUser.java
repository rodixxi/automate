
package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.By;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.SystemFolder;
import com.harriague.automate.web.pages.UserPage;

public class AddUser extends StepBase {
	
	private static final String USUARIO = "usuario";
	private static final String MENU = "cuentas";
	

	@When("abrir carpeta Administrador de usuarios...")
	public void OpenUserFolder() throws AgentException, Exception{
		log.info("abro el folder de usuarios");
		getPage(SystemFolder.class).OpenSubFolder(USUARIO);
			
	}
	
	@When("abrir menu cuentas...")
	public void OpenMenuCtas() throws AgentException, Exception {
		log.info("abro el menu de cuentas");
		getPage(UserPage.class).OpenMenu(MENU);
		
	}
	
	

}
