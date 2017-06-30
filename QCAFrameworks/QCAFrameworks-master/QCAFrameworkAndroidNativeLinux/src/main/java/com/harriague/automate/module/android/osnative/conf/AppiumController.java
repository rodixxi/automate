package com.harriague.automate.module.android.osnative.conf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.SessionNotCreatedException;

import com.harriague.automate.core.device.Utils;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.exceptions.DeviceException;
import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.utils.ReadProperty;

public class AppiumController {

	// private DataStore properties;
	private ArrayList<String> serversRunning = new ArrayList<String>();
	private final String START_APPIUM_FILE = SystemUtils.IS_OS_WINDOWS ? "startAppium.bat" : "startAppium.sh";
	private final String APPIUM_STARTED_LINE = "interface listener started";
	private final String NODE_PROCESS_NAME = SystemUtils.IS_OS_WINDOWS?"node.exe":"node";
	private final String APPIUM_PROCESS_NAME = "appium";
	private final int TIMEOUT_SECONDS = 60;
	private final String GET_PROCESSES_LIST = SystemUtils.IS_OS_WINDOWS?"tasklist":"ps -ef --no-headers";
	
	private Process p;
	private Logger log = Logger.getLogger(getClass().getName());
	private String nodeCommand;
	private int currentPort = 0;
	private Utils util = null;

	public AppiumController(Utils deviceUtil) {
		util = deviceUtil;
	}

	/*
	 * 
	 * PUBLIC METHODS:
	 */

	/**
	 * Starts the server in case it has not already started
	 * 
	 * @param deviceData
	 *            DeviceData object
	 * @return true if success, false if not
	 * @throws AgentException 
	 * @throws IOException
	 */
	public void startServer() throws AgentException {
		boolean success = false;
		try {
			success = isRunning(ReadProperty
					.getProperty(PropertiesKeys.APPIUM_PORT));
			if (!success) {
//				return success;
			
			currentPort = Integer.parseInt(ReadProperty
					.getProperty(PropertiesKeys.APPIUM_PORT));
	
			success = tryToConnect(currentPort);
	
			
			
			resetCMD();
			}
		} catch (Exception e) {
			throw new AgentException(e.getMessage(), e, null);
		} 

//		return success;
	}

	/**
	 * Kills the currently running Appium server
	 */
	public void killServer() {
		if (p != null) {
			log.info("Killing Appium server");
			p.destroy();
		}
	}

	/**
	 * Kill all android emulators running
	 */
	public void killEmulators() {
		Process p;
		try {
			p = Runtime.getRuntime().exec(GET_PROCESSES_LIST);

			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				if (line.contains("emulator")) {
					log.info("line: " + line);
					String[] processContent = line.split(",");
					String killingProcess = "pkill -f "
							+ processContent[0];
					log.info("Executing " + killingProcess);
					Process p2 = Runtime.getRuntime().exec(killingProcess);
					p2.waitFor();
				}
			}

		} catch (IOException | InterruptedException e) {
			log.warn("Error closing emulator");
			e.printStackTrace();
		}
	}

	// /**
	// * Starts an emulator
	// *
	// * @param emulatorName String The name of the emulator to start
	// */
	// public void startEmulator(String emulatorName)
	// {
	// nodeCommand+=" --avd " + emulatorName;
	// }

	/*
	 * 
	 * PRIVATE METHODS:
	 */

	/**
	 * Checks if a server is running in the remote URL passed trough parameter
	 * 
	 * @param remoteURL
	 *            String the URL where Appium Server is running
	 * @return true or false
	 * @throws IOException
	 */
	private boolean isRunning(String remoteURL) throws IOException {
		log.info("Checking if Appium Server is running in " + remoteURL);

		// First check if the process is already running
		p = Runtime.getRuntime().exec(GET_PROCESSES_LIST);
		//TODO: to implement: get from windows if it is running or not
		//Look for running appium with the windows command
//		p = Runtime.getRuntime().exec("tasklist /fo csv /nh /fi \"IMAGENAME eq node.exe\"");

		BufferedReader input = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = input.readLine()) != null) {
			if (line.contains(APPIUM_PROCESS_NAME) && line.contains(NODE_PROCESS_NAME) 
					&& (SystemUtils.IS_OS_WINDOWS?true:line.contains("--tmp /var/tmp/appium"))) {
				log.debug(line);
				log.info("There is an Appium application started");
				return true;
			}
		}

		// Then check if there's a previous instance launched
		for (String runningServer : serversRunning) {
			if (runningServer.equals(remoteURL)) {
				log.info("Appium already running on " + remoteURL);
				return true;
			}
		}

		log.info("Appium not running yet");

		return false;
	}

	/**
	 * Tries to connect to the Appium server until it times out
	 * 
	 * @param port
	 *            String the port to connect
	 * @param deviceData
	 *            DeviceData data of the device to connect
	 * @return true if connected, false if not
	 * @throws AgentException 
	 * @throws PropertyException 
	 */
	private boolean tryToConnect(int port) throws PropertyException {
		boolean success = false;
		success = connect(port);
		

		int attempts = 0;
		int maxAttempts = 10;

		while (!success && attempts < maxAttempts) {
			port -= 1;
			log.info("Changing appium port to " + port);
			attempts++;
			success = connect(port);
		}
		return success;
	}

	/**
	 * Resets the Appium command and cleans it
	 */
	private void resetCMD() {
		nodeCommand = "";
		// cmd "+APPIUM_PATH +"\\..\\..\\..\\node appium -p
	}

	/**
	 * Executes the command to connect Appium Server
	 * 
	 * @param port
	 *            String the port where to run the server
	 * @param deviceData
	 *            DeviceData object
	 * @return true if the connection succeed, false if not
	 * @throws PropertyException 
	 */
	private boolean connect(int port) throws PropertyException {
		try {

			nodeCommand = SystemUtils.IS_OS_WINDOWS?"appium.js\" -p ":"appium -p ";
			nodeCommand += port;
			nodeCommand += SystemUtils.IS_OS_WINDOWS?" --tmp C:\\temp\\appium":" --tmp /var/tmp/appium";

			setDriverPort();

			log.info("Starting Appium Server with command " + nodeCommand);

			if (runStartCommand()) {
				serversRunning.add(com.harriague.automate.core.conf.Constants.PROTOCOL_HTTP +
						 ReadProperty.getProperty(PropertiesKeys.APPIUM_HOST) + ":" +  port +
						 Constants.APPIUM_URL);
				return true;
			}
			return false;

		} catch (IOException e) {
			log.error("Error creating Appium session");
			e.printStackTrace();
			return false;
		} catch (SessionNotCreatedException snce) {
			log.error("Appium session could not be created.");
			snce.printStackTrace();
			return false;
		}
	}

	/**
	 * Creates the file with the full command to start the server
	 * 
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws PropertyException 
	 */
	private void createStarterFile() throws FileNotFoundException,
			UnsupportedEncodingException, PropertyException {
		PrintWriter writer = new PrintWriter(START_APPIUM_FILE, "UTF-8");
		if(SystemUtils.IS_OS_WINDOWS)
			writer.print("node ");
		writer.print(System.getProperty("user.home") + ReadProperty.getProperty(PropertiesKeys.APPIUM_BIN_FOLDER));
		writer.println(nodeCommand);
		writer.close();
	}

	private boolean runStartCommand() throws IOException, PropertyException {
		createStarterFile();
		
		p = Runtime.getRuntime().exec((SystemUtils.IS_OS_WINDOWS?"cmd.exe /K start ":"/bin/bash ") + START_APPIUM_FILE);
						
		boolean started = false;
		if(SystemUtils.IS_OS_WINDOWS){
			started = delaySecond();
		}
		else{
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream(),"UTF-8"));
			String line = null;

			long startTime = new Date().getTime();
			int timeout = TIMEOUT_SECONDS * 1000;
//			log.info("Started at " + startTime);
			// Waits until Appium Server starts
			while (!started) {
				if ((line = input.readLine()) != null) {
					log.info("Appium: " + line);
					if (line.contains(APPIUM_STARTED_LINE)) {
						log.info("Appium started");
						started = true;
						break;
					}
				} else {
//					log.info(new Date().getTime() - startTime);
					if (new Date().getTime() - startTime >= timeout) {
						log.info("elapsed time "
								+ (new Date().getTime() - startTime));
						return started;
					}
				}
			}
		}			

		return started;
	}
	
	private boolean delaySecond()
    {
        try {
        	util.waitForProcess(NODE_PROCESS_NAME);
            Thread.sleep(10000);
        }catch(InterruptedException | DeviceException e){
        	log.error("Appium session could not be created.");	
        	log.error(e.getMessage());
        	return false;
        }
        return true;
    }

	/**
	 * Sets the port of the chromedriver or selendroid
	 * 
	 * @param remotePort
	 *            String the current Server port value
	 * @throws PropertyException
	 * @throws NumberFormatException
	 */
	private void setDriverPort() throws NumberFormatException,
			PropertyException {
		log.info("Adding the driver port parameter to command");
		currentPort += 1;
		if (ReadProperty.getProperty(PropertiesKeys.APPIUM_AUTOMATOR)
				.toUpperCase().equals(Constants.APPIUM_AUTOMATOR_SELENDROID)) {
			nodeCommand += " --selendroid-port " + (currentPort + 1);
		} else {
			nodeCommand += " --chromedriver-port " + (currentPort + 1);
		}
	}
}
