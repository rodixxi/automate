package com.harriague.automate.core.device.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.conf.Constants;
import com.harriague.automate.core.conf.PropertiesKeys;
import com.harriague.automate.core.device.Utils;
import com.harriague.automate.core.exceptions.DeviceException;
import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.utils.ReadProperty;

public class LinuxUtilsImpl implements Utils {

    /**
     * Logger object
     */
    private static Logger log = Logger.getLogger(LinuxUtilsImpl.class.getName());
    private int WAIT_FOR_PROCESS_TIME_OUT;
    private Agent m_agent;


    public LinuxUtilsImpl() {
        try {
            WAIT_FOR_PROCESS_TIME_OUT =
                    ReadProperty.getPropertyInt(PropertiesKeys.DEFAULT_WAIT_FOR_PROCESS_TIME_OUT);
        } catch (PropertyException e) {
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
        String appInstallCommandLine =
                String.format(PropertiesKeys.applicationCommandLineForInstall, nameKey);
        if (!ReadProperty.isPropertyExist(appInstallCommandLine)) {
            throw new DeviceException(
                    "Not exist property with command line for install application '" + nameKey
                            + "'.");
        }
        executeCommandLine(appInstallCommandLine);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#uninstallAplication(java.lang .String)
     */
    @Override
    public boolean uninstallApplication(String nameKey) throws DeviceException {
        String appUninstallCommandLine =
                String.format(PropertiesKeys.applicationCommandLineForInstall, nameKey);
        if (!ReadProperty.isPropertyExist(appUninstallCommandLine)) {
            throw new DeviceException(
                    "Not exist property with command line for uninstall application '" + nameKey
                            + "'.");
        }
        executeCommandLine(appUninstallCommandLine);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#readFile(java.lang.String)
     */
    @Override
    public StringBuffer readFile(String path) throws DeviceException {
        StringBuffer text = new StringBuffer();
        BufferedReader file = null;
        try {
            log.info("Search file '" + path + "'...");
            file = new BufferedReader(new FileReader(path));
            String line = "";
            log.info("File exist. Reading...");
            while ((line = file.readLine()) != null) {
                text.append(line);
            }
            file.close();
        } catch (Exception e) {
            throw new DeviceException(e);
        }
        return text;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#readRegistry(int, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String readRegistry(int parentFolder, String path, String key) throws DeviceException {
        log.error("There is no registrymon linux");
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#copyFile(java.lang.String, java.lang.String)
     */
    @Override
    public void copyFile(String originPath, String destinationPath) throws DeviceException {
        log.info("Copy file, from '" + originPath + "' to '" + destinationPath + "'...");
        File originFile = new File(originPath);
        if (originFile.exists()) {
            File destinationFile = new File(destinationPath);
            try {
                FileUtils.copyFile(originFile, destinationFile);
                log.info("Copy file success.");
            } catch (IOException e) {
                throw new DeviceException(e);
            }
        } else {
            throw new DeviceException("The file " + originPath + " not exist.");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#deleteFile(java.lang.String)
     */
    @Override
    public void deleteFile(String path) {
        log.info("Delete file '" + path + "'..");
        new File(path).delete();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#stopService(java.lang.String)
     */
    @Override
    public boolean stopService(String serviceName) throws DeviceException {
        String message = Constants.DEFAULT_STOP_SERVICE_MESSAGE;
        if (ReadProperty.isPropertyExist(PropertiesKeys.serviceStartMessaje)) {
            try {
                message = ReadProperty.getProperty(PropertiesKeys.serviceStopMessaje);
            } catch (PropertyException e) {
                throw new DeviceException(e);
            }
        }
        return runServiceCommand(serviceName, ServiceCommand.START, message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.device.Utils#startService(java.lang.String)
     */
    @Override
    public boolean startService(String serviceName) throws DeviceException {
        String message = Constants.DEFAULT_START_SERVICE_MESSAGE;
        if (ReadProperty.isPropertyExist(PropertiesKeys.serviceStartMessaje)) {
            try {
                message = ReadProperty.getProperty(PropertiesKeys.serviceStartMessaje);
            } catch (PropertyException e) {
                throw new DeviceException(e);
            }
        }
        return runServiceCommand(serviceName, ServiceCommand.START, message);
    }

    /**
     * Command for services
     */
    public static enum ServiceCommand {
        START, STOP;
    }

    /**
     * Run a command in net service
     *
     * @param serviceName service name
     * @param command command
     * @param message expected message
     * @throws DeviceException
     * @throws Exception exception
     */
    private boolean runServiceCommand(String serviceName, ServiceCommand command, String message)
            throws DeviceException {
        Process p;
        boolean messageIsDiplayed = false;
        int retry = 0;

        try {
            while (!messageIsDiplayed && retry < 10) {
                retry++;

                p = Runtime.getRuntime().exec("cmd /c net " + command + " " + serviceName);

                p.waitFor();
                Thread.sleep(2000);
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(message)) {
                        messageIsDiplayed = true;
                        break;
                    }
                }

            }
        } catch (Exception e) {
            throw new DeviceException(e);
        }
        return messageIsDiplayed;
    }

    /**
     * Execute a command line
     *
     * @param appInstallCommandLine command line to execute
     * @throws DeviceException
     */
    private void executeCommandLine(String appInstallCommandLine) throws DeviceException {
        try {
            Process process =
                    Runtime.getRuntime().exec(ReadProperty.getProperty(appInstallCommandLine));
            process.waitFor();
        } catch (Exception e) {
            throw new DeviceException(e);
        }
    }

    /**
     * Kill a process
     *
     * @param processName process name to kill
     * @throws IOException
     * @throws InterruptedException
     */
    public void killProcess(String processName) throws DeviceException {
        if (processName.split("-").length > 1) {
            for (int i = 0; i < processName.split("-").length; i++) {
                killProcess(processName.split("-")[i]);
            }
        } else {
            log.info("Killing process '" + processName + "'.");
            try {
                Process p = Runtime.getRuntime().exec("pkill " + processName);
                p.waitFor();
                Thread.sleep(5000);
            } catch (Exception e) {
                throw new DeviceException(e.getMessage(), e);
            }
        }
    }

    @Override
    public boolean isProcessRunning(String processName) throws DeviceException {
        try {
            String line;
            Process p = Runtime.getRuntime().exec("ps -e");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    @Override
    public void waitForProcess(String processName) throws DeviceException {
        log.info("Waiting for process " + processName);
        final long startTime = new Date().getTime();
        int timeout = WAIT_FOR_PROCESS_TIME_OUT * 1000;
        long currentTime;
        while (!isProcessRunning(processName)) {
            currentTime = new Date().getTime() - startTime;
            if (currentTime >= timeout) {
                log.info("Waiting timed out");
                throw new DeviceException("Waiting for process \"" + processName
                        + "\" timed out after " + (currentTime / 1000) + " seconds");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
        log.info("Process found");
    }

    @Override
    public void rebootDevice() {
        // TODO generate <code></code>
    }

    @Override
    public void rebootDevice(String deviceId) {
        // TODO generate <code></code>
    }

    @Override
    public void launchApp(String appName) {
        // TODO generate <code></code>
    }

    @Override
    public void setAgent(Agent agent) {
        m_agent = agent;
    }
}
