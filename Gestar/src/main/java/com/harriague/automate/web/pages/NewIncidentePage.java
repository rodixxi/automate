package com.harriague.automate.web.pages;

 
import com.harriague.automate.core.exceptions.AgentException;
//import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

public interface NewIncidentePage extends Page {
	
	void OpenFolder(String folder) throws AgentException ;

}
