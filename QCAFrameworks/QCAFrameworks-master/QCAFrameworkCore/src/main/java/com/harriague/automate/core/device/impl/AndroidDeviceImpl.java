package com.harriague.automate.core.device.impl;

import java.util.ArrayList;
import java.util.List;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.app.Application;
import com.harriague.automate.core.conf.Constants;
import com.harriague.automate.core.conf.Parameters;
import com.harriague.automate.core.conf.Constants.AppType;
import com.harriague.automate.core.device.Device;
import com.harriague.automate.core.device.DeviceBase;
import com.harriague.automate.core.device.Utils;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.exceptions.DeviceException;

public class AndroidDeviceImpl extends DeviceBase implements Device {

    private Utils utils;

    private String name;

    private Agent agent = null;

    @Override
    public void close() throws DeviceException {
        if (!applications.values().isEmpty()) {
            List<String> appsProcessName = new ArrayList<String>();
            for (Application application : applications.values()) {
                application.close();
                if (!appsProcessName.contains(application.getProcessName())) {
                    appsProcessName.add(application.getProcessName());
                }
            }
        }
        Utils utils = getUtil();
        utils.killProcess(Constants.CHROME_DRIVE_PROCESS_NAME);

    }

    /**
     * Default constructor
     * 
     * @param utils of the SO
     */
    public AndroidDeviceImpl(Utils utils, String name) {
        this.utils = utils;
        this.name = name;
    }


    @Override
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

    @Override
    public boolean killApplication(String processName) throws DeviceException {
        try {
            Process p = Runtime.getRuntime().exec("adb shell am force-stop " + processName);
            p.waitFor();
            return p.exitValue() == 0;
        } catch (Exception e) {
            throw new DeviceException(e.getMessage());
        }
    }

    @Override
    public boolean processIsRunner(String processName) throws DeviceException {
        return getUtil().isProcessRunning(processName);
    }

    @Override
    public Utils getUtil() {
        if (utils == null) {
            utils = new AndroidUtilsImpl();
            utils.setAgent(agent);
        }
        return utils;
    }

    @Override
    public Agent createAgent(AppType type, String agentImplPackageTemplate) throws DeviceException {
        String platform = this.getClass().getSimpleName().replace("DeviceImpl", "").toLowerCase();
        agent = super.createAgent(type, platform, agentImplPackageTemplate);
        utils.setAgent(agent);
        return agent;
    }

    @Override
    public boolean applicationExists(String appKey) {
        return applications.containsKey(appKey);
    }

    @Override
    public void changeAppName(String oldName, String newName) {
        applications.put(newName, applications.get(oldName));
        applications.remove(oldName);
    }

    @Override
    public String getDeviceName() {
        return name;
    }

    @Override
    public Agent getAgent() {
        return agent;
    }
}
