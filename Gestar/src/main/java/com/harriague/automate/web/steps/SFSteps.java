
package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.When;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.SystemFolder;

public class SFSteps extends StepBase {
	
	@When("jump system folder and get subforders...")
	public void veoPronostico() throws AgentException, Exception{
		log.info("abro el folder de sistema");
		getPage(SystemFolder.class).OpenFolder();
		log.info("recorro los subfolders");
		getPage(SystemFolder.class).GetSubFolders();
	}
}
