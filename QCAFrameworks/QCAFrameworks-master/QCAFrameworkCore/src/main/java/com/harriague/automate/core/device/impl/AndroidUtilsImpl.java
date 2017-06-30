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
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.exceptions.DeviceException;

public class AndroidUtilsImpl implements Utils {

    private static Logger log = Logger.getLogger(AndroidUtilsImpl.class.getName());
    private final int WAIT_FOR_PROCESS_TIME_OUT;
    private Agent driver;
    public static String apkFolder;

    static {
        try {
            apkFolder = System.getProperty("user.home")
                    + ReadProperty.getProperty(PropertiesKeys.ANDROID_APK_PATH);
        } catch (PropertyException e) {
            e.printStackTrace();
            log.error("could not find apk folder path " + System.getProperty("user.home")
                    + PropertiesKeys.ANDROID_APK_PATH);
        }
    }

    public AndroidUtilsImpl() {
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
        Process p = null;
        try {
            String command = "install -r " + apkFolder + nameKey;
            p = driver.sendCommand(command);
            log.info(nameKey + " installed ");
        } catch (AgentException e1) {
            throw new DeviceException(e1.getMessage());
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;

        try {
            while ((line = input.readLine()) != null) {
                if (line.contains("Missing APK file")) {
                    throw new DeviceException("'" + apkFolder + nameKey + "' file not found");
                } else if (line.contains("INSTALL_FAILED_INVALID_APK")) {
                    throw new DeviceException(
                            "'" + apkFolder + nameKey + "' is not valid or mightbe corrupted");
                } else if (line.contains("Success")) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new DeviceException(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean uninstallApplication(String nameKey) throws DeviceException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public StringBuffer readFile(String path) throws DeviceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String readRegistry(int parentFolder, String path, String key) throws DeviceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void copyFile(String originPath, String destinationPath) throws DeviceException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteFile(String path) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean stopService(String serviceName) throws DeviceException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean startService(String serviceName) throws DeviceException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void killProcess(String processName) throws DeviceException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isProcessRunning(String processName) throws DeviceException {
        Process p;
        try {
            p = driver.sendCommand("shell ps");
        } catch (AgentException e1) {
            e1.printStackTrace();
            throw new DeviceException(e1.getMessage());
        }

        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;

        try {
            while ((line = input.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new DeviceException(e.getMessage());
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
    public void rebootDevice() throws DeviceException {
        log.info("Rebooting the current Android device");
        try {
            Runtime.getRuntime().exec("adb shell reboot");
        } catch (IOException e1) {
            log.error("Reboot failed");
            throw new DeviceException(e1.getMessage());
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.error("Interrupted exception while waiting " + e.getStackTrace());
        }
        log.info("Device correctly rebooted");
    }

    @Override
    public void rebootDevice(String deviceId) throws DeviceException {
        log.info("Rebooting the current Android device...");
        Process p;
        try {
            Runtime.getRuntime().exec("adb -s " + deviceId + " shell reboot");
            Thread.sleep(30000);
            Runtime.getRuntime().exec("adb disconnect " + deviceId);
            log.info("Killing appium server");
            Runtime.getRuntime().exec("killall -9 node");
        } catch (IOException e) {
            log.error("Reboot failed");
            throw new DeviceException(e.getMessage());
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        try {
            for (int i = 1; i < 10; i++) {
                log.info("Attempting to reconnect the device intent " + i);
                p = Runtime.getRuntime().exec("adb connect " + deviceId);
                BufferedReader input =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;
                StringBuffer consoleOutput = new StringBuffer("");
                while ((line = input.readLine()) != null) {
                    consoleOutput.append(line);
                }
                if (consoleOutput.toString().contains("connected")) {
                    log.info(consoleOutput.toString());
                    log.info("Reconnection successful");
                    break;
                }
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (IOException e) {
            log.error("Exception while waiting " + e.getStackTrace());

        }
    }

    /**
     * This method launch an android app
     * 
     * @param appName String - eg. com.example.android.advancedimmersivemode/.MainActivity
     */
    @Override
    public void launchApp(String appName) {
        Process p;
        String command = "adb shell am start -n " + appName;
        try {
            log.info("Launching the app " + appName + " with command " + command);
            p = Runtime.getRuntime().exec(command);
            String consoleOut = readConsoleOutput(p);
            if (consoleOut.contains("Starting")) {
                log.info("App " + appName + " found starting");
            } else {
                log.error("App " + appName + " not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error launching the app " + appName + " executing command " + command);
        }
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
        this.driver = agent;
    }


}
