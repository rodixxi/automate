package com.harriague.automate.web.pages;


import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

public interface ConfigurationsPage extends Page {
	
	void OpenElement(String element)  throws AgentException;

	void ChangeElement(String element, String Value) throws AgentException;

	void GoBack() throws AgentException;;
	

}
