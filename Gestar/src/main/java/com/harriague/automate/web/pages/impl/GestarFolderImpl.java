package com.harriague.automate.web.pages.impl;

import org.openqa.selenium.By; 
import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.GestarFolder;
 

public class GestarFolderImpl extends BasePage implements GestarFolder {

	public GestarFolderImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OpenFolder(String folder) throws AgentException {
		// TODO Auto-generated method stub
		log.info("En Open Folder");
		String path = "//span[text()='" + folder + "']";
		log.info("intento abrir: " + path);
				
		By by = By.xpath(path);
		
		if (agent.checkElementIsDisplayed( by)) {
			agent.click( by); 
			if (agent.alertIspresent()) {
				agent.aceptAlert();
			}
			
		}
		else {
			log.info("no encontro el elemento");
		}		
	}
	

}
