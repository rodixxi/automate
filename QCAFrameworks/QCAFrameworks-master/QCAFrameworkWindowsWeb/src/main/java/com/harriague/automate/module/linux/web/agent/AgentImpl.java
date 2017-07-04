package com.harriague.automate.module.linux.web.agent;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.module.linux.web.conf.Constants;
import com.harriague.automate.module.web.agent.CommonAgentImpl;

public class AgentImpl extends CommonAgentImpl implements Agent {

    public AgentImpl() {
        super(Constants.DEFAULT_DRIVER_QUICK_SEARCH, Constants.ATTRIBUTE_VALUE, 
                Constants.BROWSER_FIREFOX_NAME, Constants.BROWSER_CHROME_NAME, 
                Constants.BROWSER_DEFAULT_NAME, Constants.BROWSER_OPERA_NAME,
                Constants.RESOURCES_FOLDER, Constants.PATH_WEB_DRIVERS_FOLDER, 
                Constants.DRIVER_CHROME_FILE_NAME, Constants.PROPERTY_CHROMEDRIVER, 
                Constants.DRIVER_OPERA_FILE_NAME, Constants.DRIVER_DEFAULT_BROWSER_FILE_NAME, 
                Constants.PROPERTY_DEFAULT_BROWSER_DRIVER);
    }
	
}
