package com.harriague.automate.core.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.agent.AgentManager;
import com.harriague.automate.core.conf.Constants;
import com.harriague.automate.core.conf.Constants.AppType;
import com.harriague.automate.core.conf.PropertiesKeys;
import com.harriague.automate.core.device.Device;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.exceptions.DeviceException;
import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.utils.CoreUtils;
import com.harriague.automate.core.utils.ReadProperty;

/**
 * Application object
 */
public class Application {

    private static Logger log = Logger.getLogger(Application.class.getName());

    /**
     * Agent
     */
    public Agent agent;

    /**
     * App name
     */
    private String appName;

    /**
     * Application process name
     */
    private String processName;

    /**
     * Application type
     */
    private AppType appType;

    /**
     * Parent device
     */
    private Device device;

    /**
     * Map of the pages
     */
    public Map<String, Object> pages = new HashMap<String, Object>();

    private final String MIDDLE_DASH = "-";
    private final String DOT = ".";

    /**
     * Constructor
     * 
     * @param appName app name to create
     * @throws AgentException
     */
    public Application(String appName, String isABrowser, Device device) throws AgentException {
        this.appName = appName;
        this.device = device;
        if (appName == null) {
            log.warn("no app name");
            return;
        }
        try {
            // Get application type
            appType =
                    (isABrowser != null) ? AppType.WEB
                            : AppType.getApptype(ReadProperty
                                    .getProperty(String.format(PropertiesKeys.applicationType,
                                            appName.split(MIDDLE_DASH)[0])));
        } catch (Exception e) {
            throw new AgentException(e, null);
        }
        // Create agent
        this.agent = AgentManager.createAgent(device, appType);
        // Start Agent
        this.agent.start(appName);
        try {
            // Get process name
            this.processName = getProccessName(appType, appName);
        } catch (PropertyException e) {
            throw new AgentException(e, null);
        }
    }

    /**
     * Get process name
     * 
     * @param appType application type
     * @param appName application key
     * @return String process name
     * @throws PropertyException
     */
    private String getProccessName(AppType appType, String appName) throws PropertyException {
        if (appType == AppType.WEB) {
            // Get process name of the browser
            return Constants.getBrowserProcessName().get(appName.split(MIDDLE_DASH)[1]);
        } else {
            // Get process name the application from properties file
            return ReadProperty.getProperty(String.format(PropertiesKeys.applicationProcessName,
                    appName.split(MIDDLE_DASH)[0]));
        }
    }

    /**
     * Constructor
     * 
     * @param application Application
     * @param appName application name
     */
    public Application(Application application, String appName) {
        this.agent = application.getAgent();
        this.appName = appName;
        this.appType = application.getApplicationType();
        this.device = application.getDevice();
    }

    /**
     * Get a page and save in cache
     * 
     * @param type of the page to create
     * @return new instance of the page
     * @throws Exception
     */
    public <T extends Object> T getPage(Class<T> type) throws Exception {
        return getPage(type, false);
    }

    /**
     * Get a page and save in cache
     * 
     * @param type of the page to create
     * @param force force to create a new instance
     * @return new instance of the page
     * @throws Exception
     */
    public <T extends Object> T getPage(Class<T> type, boolean force) throws Exception {
        String template = ReadProperty.getProperty(PropertiesKeys.basePackage) + DOT
                + appType.toString().toLowerCase() + DOT + "pages.impl.%sImpl";
        if (!pages.containsKey(type.getSimpleName()) || force) {
            pages.put(type.getSimpleName(), CoreUtils.getPageInstance(type, template, agent));
        }
        return type.cast(pages.get(type.getSimpleName()));
    }

    /**
     * Force to create a page and save in cache
     * 
     * @param type of the page to create
     * @return new instance of the page
     * @throws Exception
     */
    public <T extends Object> T getPageForce(Class<T> type) throws Exception {
        return getPage(type, true);
    }


    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Close application
     * 
     * @throws DeviceException
     */
    public boolean close() throws DeviceException {
        agent.close();
        return true;
    }

    /**
     * Navigate to URL
     * 
     * @param url to navigate
     * @throws AgentException
     */
    public void navigateTo(String url) throws AgentException {
        agent.navigateTo(url);
    }

    /**
     * @return the agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * get application type
     * 
     * @return AppType
     */
    public AppType getApplicationType() {
        return this.appType;
    }

    /**
     * Get device
     * 
     * @return Device
     */
    public Device getDevice() {
        return this.device;
    }

    /**
     * Get process name
     * 
     * @return String process name
     */
    public String getProcessName() {
        return this.processName;
    }
}
