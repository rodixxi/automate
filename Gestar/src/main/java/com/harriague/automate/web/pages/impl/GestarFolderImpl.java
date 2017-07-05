package com.harriague.automate.web.pages.impl;

import org.openqa.selenium.By; 
import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.GestarFolder;

import java.util.concurrent.TimeUnit;


public class GestarFolderImpl extends BasePage implements GestarFolder {

	public GestarFolderImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OpenFolder(String folder) throws AgentException, InterruptedException {
		// TODO Auto-generated method stub
		log.info("En Open Folder");
		String path = "//span[text()='"+ folder +"']/ancestor::div[1]";
		By element = By.xpath(path);
		log.info("intento abrir: " + path);
		if (agent.checkElementIsDisplayed(element)) {
            log.info("hago clic para abrir la carpeta de sistema ");
            agent.click(element);
            TimeUnit.SECONDS.sleep(1);
        }
		else {
			log.info("no encontro el elemento");
			throw new AgentException("no encontro el elemento", agent);
		}
	}
    /*
    @Override
	public void CancelPopUp() throws AgentException{
	    String parent = agent.switchToPopup();
	    By cancelBottom = By.xpath("//div[@id='1001']/span[2]");
	    agent.click(cancelBottom);
	    agent.switchToPopup(parent);
    }
    */

	

}
