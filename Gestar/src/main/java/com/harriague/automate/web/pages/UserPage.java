package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

public interface UserPage extends Page {

	void OpenMenu(String menu) throws AgentException;
}
 

 