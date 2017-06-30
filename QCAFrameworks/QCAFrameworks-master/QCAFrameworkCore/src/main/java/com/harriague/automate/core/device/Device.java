package com.harriague.automate.core.device;

import java.util.List;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.app.Application;
import com.harriague.automate.core.conf.Constants.AppType;
import com.harriague.automate.core.exceptions.DeviceException;

public interface Device {

	/**
	 * Close device
	 * @exception DeviceException
	 */
	void close() throws DeviceException;
	
	/**
	 * Close device
	 * @exception DeviceException
	 */
	String containsAnApplication(String partKey) throws DeviceException;
	
	/**
	 * Return a default application
	 * @return Application 
	 * @exception DeviceException
	 */
	Application getApplication(String appKey, String isABrowser) throws DeviceException;
	
	/**
	 * Return all applications
	 * @return List<Application> List of the all applications
	 */
	List<Application> getApplications();
	
	/**
	 * Kill a application instance
	 * @param processName process name to kill
	 * @return <true> the application is killed
	 * @exception DeviceException
	 */
	boolean killApplication(String processName) throws DeviceException;
	
	/**
	 * check if the process is runner
	 * @param processName process name to kill
	 * @return <true> the application is killed
	 * @exception DeviceException
	 */
	boolean processIsRunner(String processName) throws DeviceException;
	
	/**
	 * Get utils for device
	 * @return Util
	 */
	Utils getUtil();
	
	/**
	 * Create a web agent
	 * @param device of the application 
	 * @param agentImplPackageTemplate package
	 * @return Agent 
	 * @throws DeviceException
	 */
	Agent createAgent(AppType type, String agentImplPackageTemplate) throws DeviceException;
	
	/**
	 * Check if the application exist
	 * @param appKey to find
	 * @return <true> the application exist
	 */
	boolean applicationExists(String appKey);

	/**
	 * Change application key name
	 * @param oldName old name
	 * @param newName new name
	 */
	void changeAppName(String oldName, String newName);
	
	/**
	 * Get device name
	 * @return String device name
	 */
	String getDeviceName();
	
	Agent getAgent();
}
