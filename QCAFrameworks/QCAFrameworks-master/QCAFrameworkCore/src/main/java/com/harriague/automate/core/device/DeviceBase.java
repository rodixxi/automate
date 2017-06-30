/**
 * 
 */
package com.harriague.automate.core.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.app.Application;
import com.harriague.automate.core.conf.Constants.AppType;
import com.harriague.automate.core.exceptions.DeviceException;
import com.harriague.automate.core.utils.CoreUtils;

/**
 */
public abstract class DeviceBase {

    /**
     * Logger object
     */
    private static Logger log = Logger.getLogger(DeviceBase.class.getName());

    /**
     * Application list
     */
    protected Map<String, Application> applications = new HashMap<String, Application>();

    /**
     * Create a agent
     * 
     * @param appType agent type
     * @param platform agent platform
     * @param agentImplPackageTemplate
     * @return Agent
     * @throws DeviceException
     */
    public Agent createAgent(AppType appType, String platform, String agentImplPackageTemplate)
            throws DeviceException {
        try {
            String module = appType.toString().toLowerCase();
            String[] modules = module.split("\\.");
            if (modules.length > 1) {
                module = modules[1];
            }
            log.info("Creating a " + platform + " agent '" + module + "' type...");
            Agent agent = CoreUtils.getInstance(Agent.class, agentImplPackageTemplate,
                    platform + "." + module);
            log.info("New agent created.");
            return agent;
        } catch (Exception e) {
            throw new DeviceException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#containsAnApplication(java.lang.String)
     */
    public String containsAnApplication(String partKey) throws DeviceException {
        for (String application : applications.keySet()) {
            if (application.contains(partKey)) {
                return application;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#getApplications()
     */
    public List<Application> getApplications() {
        List<Application> applications = new ArrayList<Application>();
        applications.addAll(this.applications.values());
        return applications;
    }

    public Utils getUtil() {
        Utils util = null;
        try {
            String className =
                    getClass().getSimpleName().replace("DeviceImpl", "").concat("UtilsImpl");
            Class<?> c = Class.forName(className);
            util = (Utils) c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return util;
    }
}
