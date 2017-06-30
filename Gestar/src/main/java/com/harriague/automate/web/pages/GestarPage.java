
package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;
//import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

public interface GestarPage extends Page {

	void OpenFolder() throws AgentException, InterruptedException;
	
	void OpenSubFolder(String folder ) throws AgentException, InterruptedException;
	
	void GetSubFolders() throws AgentException;
}


