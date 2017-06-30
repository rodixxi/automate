
package com.harriague.automate.web.steps;

import org.jbehave.core.annotations.When;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.GestarFolder;

public class GestarFolders extends StepBase {
	
	@When("abrir la carpeta $folder")
	public void abrirCarpeta(String folder) throws AgentException,  Exception{
		log.info("intento abrir la carpeta: " + folder);
		getPage(GestarFolder.class).OpenFolder(folder);
	}

}
