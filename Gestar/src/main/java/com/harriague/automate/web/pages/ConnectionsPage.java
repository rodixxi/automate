package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;


public interface ConnectionsPage  extends Page {
	
	void CheckConnections () throws AgentException;

}