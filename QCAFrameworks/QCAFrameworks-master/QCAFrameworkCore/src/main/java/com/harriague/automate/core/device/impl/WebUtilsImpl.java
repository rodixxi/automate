package com.harriague.automate.core.device.impl;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.conf.PropertiesKeys;
import com.harriague.automate.core.device.Device;
import com.harriague.automate.core.device.DeviceManager;
import com.harriague.automate.core.device.Utils;
import com.harriague.automate.core.exceptions.DeviceException;
import com.harriague.automate.core.utils.CoreUtils;
import com.harriague.automate.core.utils.ReadProperty;

public class WebUtilsImpl implements Utils {

    /**
     * Logger object
     */
    private static Logger log = Logger.getLogger(WebUtilsImpl.class.getName());
    private Utils OSUtil;
    private Agent m_agent;


    public WebUtilsImpl() {
        try {
            ReadProperty.getPropertyInt(PropertiesKeys.DEFAULT_WAIT_FOR_PROCESS_TIME_OUT);
            OSUtil = CoreUtils.getOSUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#installAplication(java.lang. String)
     */
    @Override
    public boolean installApplication(String nameKey) throws DeviceException {
        log.error("WebUtilsImpl.installAplication: Not yet implemented");
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#uninstallAplication(java.lang .String)
     */
    @Override
    public boolean uninstallApplication(String nameKey) throws DeviceException {
        log.error("WebUtilsImpl.uninstallAplication: Not yet implemented");
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#readFile(java.lang.String)
     */
    @Override
    public StringBuffer readFile(String path) throws DeviceException {
        return OSUtil.readFile(path);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#readRegistry(int, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String readRegistry(int parentFolder, String path, String key) throws DeviceException {
        return OSUtil.readRegistry(parentFolder, path, key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#copyFile(java.lang.String, java.lang.String)
     */
    @Override
    public void copyFile(String originPath, String destinationPath) throws DeviceException {
        OSUtil.copyFile(originPath, destinationPath);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#deleteFile(java.lang.String)
     */
    @Override
    public void deleteFile(String path) {
        OSUtil.deleteFile(path);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#stopService(java.lang.String)
     */
    @Override
    public boolean stopService(String serviceName) throws DeviceException {
        return OSUtil.stopService(serviceName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#startService(java.lang.String)
     */
    @Override
    public boolean startService(String serviceName) throws DeviceException {
        return OSUtil.startService(serviceName);
    }

    /**
     * Command for services
     */
    public static enum ServiceCommand {
        START, STOP;
    }

    /**
     * Kill a process
     *
     * @param processName process name to kill
     * @throws IOException
     * @throws InterruptedException
     */
    public void killProcess(String processName) throws DeviceException {
        OSUtil.killProcess(processName);
    }

    @Override
    public boolean isProcessRunning(String processName) throws DeviceException {
        return OSUtil.isProcessRunning(processName);
    }

    @Override
    public void waitForProcess(String processName) throws DeviceException {
        OSUtil.waitForProcess(processName);
    }

    @Override
    public void rebootDevice() {
        Device currentDevice = DeviceManager.getCurrentDevice();
        String deviceKey = currentDevice.getDeviceName();
        try {
            currentDevice.close();
            DeviceManager.createDevice(deviceKey);
        } catch (DeviceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rebootDevice(String deviceId) {
        try {
            DeviceManager.getDevice(deviceId).close();
            DeviceManager.createDevice(deviceId);
        } catch (DeviceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void launchApp(String appName) {
        OSUtil.launchApp(appName);
    }

    @Override
    public void setAgent(Agent agent) {
        m_agent = agent;
    }
}
