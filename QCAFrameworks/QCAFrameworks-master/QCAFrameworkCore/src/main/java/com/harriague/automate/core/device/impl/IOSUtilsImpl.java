package com.harriague.automate.core.device.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.conf.PropertiesKeys;
import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.utils.ReadProperty;
import org.apache.log4j.Logger;

import com.harriague.automate.core.device.Utils;
import com.harriague.automate.core.exceptions.DeviceException;

public class IOSUtilsImpl implements Utils {

    private static Logger log = Logger.getLogger(IOSUtilsImpl.class.getName());
    private final int WAIT_FOR_PROCESS_TIME_OUT;
    public static String ipaFolder;

    static {
        try {
            ipaFolder = System.getProperty("user.home")
                    + ReadProperty.getProperty(PropertiesKeys.IOS_IPA_PATH);
        } catch (PropertyException e) {
            e.printStackTrace();
            log.error("could not find ipa folder path " + System.getProperty("user.home")
                    + PropertiesKeys.IOS_IPA_PATH);
        }
    }

    public IOSUtilsImpl() {
        int propertyValue;
        try {
            propertyValue = ReadProperty.getPropertyInt(PropertiesKeys.DEFAULT_WAIT_FOR_PROCESS_TIME_OUT);
        } catch (PropertyException e) {
            propertyValue = 25;
            e.printStackTrace();
        }
        WAIT_FOR_PROCESS_TIME_OUT = propertyValue;
    }

    @Override
    public boolean installApplication(String nameKey) throws DeviceException {
        // TODO: implement iOS version
        return false;
    }

    @Override
    public boolean uninstallApplication(String nameKey) throws DeviceException {
        // TODO: implement iOS version
        return false;
    }

    @Override
    public StringBuffer readFile(String path) throws DeviceException {
        // TODO: implement iOS version
        return null;
    }

    @Override
    public String readRegistry(int parentFolder, String path, String key) throws DeviceException {
        // TODO: implement iOS version
        return null;
    }

    @Override
    public void copyFile(String originPath, String destinationPath) throws DeviceException {
        // TODO: implement iOS version
    }

    @Override
    public void deleteFile(String path) {
        // TODO: implement iOS version
    }

    @Override
    public boolean stopService(String serviceName) throws DeviceException {
        // TODO: implement iOS version
        return false;
    }

    @Override
    public boolean startService(String serviceName) throws DeviceException {
        // TODO: implement iOS version
        return false;
    }

    @Override
    public void killProcess(String processName) throws DeviceException {
        // TODO: implement iOS version
    }

    @Override
    public boolean isProcessRunning(String processName) throws DeviceException {
        // TODO: implement iOS version
        return false;
    }

    @Override
    public void waitForProcess(String processName) throws DeviceException {
        // TODO: implement iOS version
    }

    @Override
    public void rebootDevice() throws DeviceException {
        // TODO: implement iOS version
    }

    @Override
    public void rebootDevice(String deviceId) throws DeviceException {
        // TODO: implement iOS version
    }

    @Override
    public void launchApp(String appName) {
        // TODO: implement iOS version
    }

    /**
     * This method reads the console output of a process
     * 
     * @param p- process
     * @return String - The entire console output
     */
    private String readConsoleOutput(Process p) {
        BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        StringBuffer consoleOutput = new StringBuffer("");
        try {
            while ((line = output.readLine()) != null) {
                consoleOutput.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error trying to read console output");
        }
        return consoleOutput.toString();
    }

    @Override
    public void setAgent(Agent agent) {
        // TODO Auto-generated method stub
    }


}
