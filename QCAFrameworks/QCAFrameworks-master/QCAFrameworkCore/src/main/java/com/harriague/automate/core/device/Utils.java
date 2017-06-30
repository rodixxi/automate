package com.harriague.automate.core.device;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.DeviceException;

public interface Utils {

    /**
     * Install an application
     * 
     * @param nameKey application name
     * @return <true> instalation success
     * @throws DeviceException
     */
    boolean installApplication(String nameKey) throws DeviceException;

    /**
     * Uninstall an application
     * 
     * @param nameKey application name
     * @return <true> uninstalation success
     * @throws DeviceException
     */
    boolean uninstallApplication(String nameKey) throws DeviceException;

    /**
     * Read a file
     * 
     * @param path file path
     * @return StringBuffer file content
     * @throws DeviceException
     */
    StringBuffer readFile(String path) throws DeviceException;

    /**
     * Read a windows registry
     * 
     * @param parentFolde parent folder
     * @param path
     * @param key
     * @return String key value
     * @throws DeviceException
     */
    String readRegistry(int parentFolder, String path, String key) throws DeviceException;

    /**
     * Copy a file
     * 
     * @param originPath origin file path
     * @param destinationPath destination file path
     * @throws DeviceException
     */
    void copyFile(String originPath, String destinationPath) throws DeviceException;

    /**
     * Delete a file
     * 
     * @param path file path to delete
     */
    void deleteFile(String path);

    /**
     * Stop a service
     * 
     * @param serviceName service to stop
     * @return <true> service is stopped
     * @throws DeviceException
     */
    boolean stopService(String serviceName) throws DeviceException;

    /**
     * Start a service
     * 
     * @param serviceName service to start
     * @return <true> service is started
     * @throws DeviceException
     */
    boolean startService(String serviceName) throws DeviceException;

    /**
     * Kills a process
     * 
     * @param processName
     * @throws DeviceException
     */
    void killProcess(String processName) throws DeviceException;

    /**
     * Check if a process is running
     * 
     * @param processName
     * @return true if the process was found
     * @throws DeviceException
     */
    boolean isProcessRunning(String processName) throws DeviceException;

    /**
     * Wait until process appear
     * 
     * @param processName
     * @throws DeviceException
     */
    void waitForProcess(String processName) throws DeviceException;

    /**
     * This method reboots the current device used in the test script
     * 
     * @throws DeviceException
     */
    void rebootDevice() throws DeviceException;

    /**
     * This method reboots the current device specified in the device Id parameter
     * 
     * @param deviceId String - The ip:port
     * @throws DeviceException
     */
    void rebootDevice(String deviceId) throws DeviceException;

    /**
     * This method launch an application
     * 
     * @param appName
     */
    void launchApp(String appName);

    /**
     * This method sets the util agent, this is necessary for many methods
     * @param agent Agent - the agent from the device
     */
    void setAgent(Agent agent);
}
