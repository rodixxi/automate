package com.harriague.automate.core.device.impl;

import java.util.ArrayList;
import java.util.List;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.app.Application;
import com.harriague.automate.core.conf.Constants.AppType;
import com.harriague.automate.core.conf.Parameters;
import com.harriague.automate.core.device.Device;
import com.harriague.automate.core.device.DeviceBase;
import com.harriague.automate.core.device.Utils;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.exceptions.DeviceException;

public class WindowsDeviceImpl extends DeviceBase implements Device {

    /*
     * Util for windows
     */
    private Utils utils = null;

    /*
     * Device name
     */
    private String name;

    private Agent agent;

    /**
     * Default constructor
     * 
     * @param utils of the SO
     */
    public WindowsDeviceImpl(Utils utils, String name) {
        this.utils = utils;
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#close()
     */
    public void close() throws DeviceException {
        if (!applications.values().isEmpty()) {
            List<String> appsProcessName = new ArrayList<String>();
            for (Application application : applications.values()) {
                application.close();
                if (!appsProcessName.contains(application.getProcessName())) {
                    appsProcessName.add(application.getProcessName());
                }
            }
            try {
                killProcess(appsProcessName);
            } catch (Exception e) {
                // throw new DeviceException(e);
            }
            applications.clear();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#getApplication(java.lang.String )
     */
    public Application getApplication(String appKey, String isABrowser) throws DeviceException {

        appKey = (appKey == null) ? System.getProperty(Parameters.RUNNER_PARAMETER_APPLICATION_NAME)
                : appKey;

        try {
            if (!applications.containsKey(appKey)) {
                applications.put(appKey, new Application(appKey, isABrowser, this));
            }
        } catch (AgentException e) {
            throw new DeviceException(e);
        }
        return applications.get(appKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#killApplication(java.lang.String )
     */
    public boolean killApplication(String processName) throws DeviceException {
        try {
            utils.killProcess(processName);
        } catch (Exception e) {
            throw new DeviceException(e);
        }
        return !processIsRunner(processName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#getUtil()
     */
    public Utils getUtil() {
        if (utils == null) {
            utils = super.getUtil();
        }
        return utils;
    }

    private static void killProcess(List<String> allProcessName) throws DeviceException {
        Utils utils = new WindowsUtilsImpl();
        for (String processName : allProcessName) {
            utils.killProcess(processName);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#applicationExists(java.lang.String)
     */
    @Override
    public boolean applicationExists(String appKey) {
        return applications.containsKey(appKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#changeAppName(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void changeAppName(String oldName, String newName) {
        applications.put(newName, applications.get(oldName));
        applications.remove(oldName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#getDeviceName()
     */
    @Override
    public String getDeviceName() {
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#processIsRunner(java.lang.String)
     */
    @Override
    public boolean processIsRunner(String processName) throws DeviceException {
        return getUtil().isProcessRunning(processName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Device#createAgent(com.harriague.automate.core.conf.
     * Constants.AppType, java.lang.String)
     */
    @Override
    public Agent createAgent(AppType type, String agentImplPackageTemplate) throws DeviceException {
        String platform = this.getClass().getSimpleName().replace("DeviceImpl", "").toLowerCase();
        agent = super.createAgent(type, platform, agentImplPackageTemplate);
        return agent;
    }

    @Override
    public Agent getAgent() {
        return agent;
    }
}
