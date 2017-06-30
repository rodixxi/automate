package com.harriague.automate.web.pages.impl;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.SystemFolder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class SystemFolderImpl extends BasePage implements SystemFolder {
	
	private static final By DIV_FOLDER = By.xpath("//div[@title='SystemFolders (1)']");
	private static final By DIV_USER_FOLDER= By.xpath("//div[@title='UserManager (3)']");
	private static final By DIV_LICENSE_FOLDER = By.xpath("//div[@title='LicenseManager (7)']");
	private static final By DIV_CHKPASS_FOLDER = By.xpath("//div[@title='ChangePassword (4)']");
	private static final By DIV_CHKCONN_FOLDER = By.xpath("//div[@title='Connections (6)']");
	private static final By DIV_CONFIG_FOLDER = By.xpath("//div[@title='SettingManager (15)']");
	private static final By ICONS = By.xpath("//div[@class='div_bar']");
	private static final By LICENCE_GRID = By.xpath("//td[contains(.,'Expira')]");
	 
	public SystemFolderImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public void OpenFolder() throws AgentException, InterruptedException {
		
		if (agent.checkElementIsDisplayed(DIV_FOLDER)) {
			log.info("hago clic para abrir la carpeta de sistema ");
			agent.click(DIV_FOLDER);
			TimeUnit.SECONDS.sleep(1);
		}
		else {
			log.info("no encontro el elemento");
		}
	}
	
	@Override
	public void OpenSubFolder(String folder ) throws AgentException, InterruptedException {
		log.info("tengo que abrir la sub carpeta " + folder);
		
		By subFolder = null;
		switch (folder){
		case "licencia":
			subFolder = DIV_LICENSE_FOLDER;
			break;
		case "usuario":
			subFolder = DIV_USER_FOLDER;
			break;
		case "chk_pass":
			subFolder = DIV_CHKPASS_FOLDER;
			break;
		case "chk_conn":
			subFolder = DIV_CHKCONN_FOLDER ;
			break;
		case "config":
			subFolder = DIV_CONFIG_FOLDER  ;
			break;
			
		}
		if (agent.checkElementIsDisplayed(subFolder)) {
			log.info("hago clic para abrir la carpeta de " + folder);
			agent.click(subFolder);
			TimeUnit.SECONDS.sleep(1);
		}
		else {
			log.info("no encontro el elemento");
		}
	}
	
	@Override
	public void GetSubFolders() throws AgentException {
	
		//veo cuantos iconos traigo
		log.info("ver los sub directorios");
		
		ArrayList<WebElement> bars =  (ArrayList<WebElement> ) agent.findElements(ICONS);
		
		//log.info("lectura: encuentro " + String.valueOf( bars.size()));
		
		
		if (bars.size() > 0) {
			//log.info("tengo elementos");
			WebElement b1 = bars.get(0);
			String texto = "//span[contains(.,'" + b1.getText().replace(".", "") + "')]";
			
			log.info("tengo el 1ro: " + b1.getTagName() + " " + b1.getText());
			b1.click();
			//String texto = "//span[contains(.,'" + b1.getText()  + "')]";
			//luego de hacer clic veo si abrio ese elemento
			//log.info(texto);
			ArrayList<WebElement> icono = (ArrayList<WebElement> ) agent.findElements(By.xpath( texto ));
			
			if (icono.size()> 0)
				log.info("abrió el primer icono y lo posiciono correctamente");
			
			//log.info("hice click en el 1ro");
		}
	}

	@Override
	public void CheckGridLicense()  throws AgentException {
		
		log.info("ver la grilla de licencia");
		
		ArrayList<WebElement> grids =  (ArrayList<WebElement> ) agent.findElements(LICENCE_GRID);
		
		if (grids.size() > 0) {
			log.info("Mostro la grilla correctamente");
		}
		else {
			log.info("No mostro la grilla");		
		}
	}
}
