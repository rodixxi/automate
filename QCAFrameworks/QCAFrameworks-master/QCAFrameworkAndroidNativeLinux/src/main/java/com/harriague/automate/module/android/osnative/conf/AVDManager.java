package com.harriague.automate.module.android.osnative.conf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.utils.ReadProperty;

public class AVDManager {

	private final static String SEPARATOR = java.io.File.separator;
	private static final String ANDROID_HOME = System.getenv("ANDROID_HOME");
	private static final String GET_DEVICES_COMMAND_LINE = ANDROID_HOME + SEPARATOR + "platform-tools" + SEPARATOR +"adb devices";
	private static final String DEVICE_READY = "device";
	private static final String START_EMULATOR_COMMAND_LINE = ANDROID_HOME+ SEPARATOR  + "tools"+ SEPARATOR +"emulator -avd <avd_name> -no-window";
	private static final String CHECK_ANIM_COMMAND_LINE = ANDROID_HOME+ SEPARATOR  + "platform-tools"+ SEPARATOR +"adb shell getprop init.svc.bootanim";
	private static final String PROXY_COMMAND_LINE = " -http-proxy ";
	private static final String KILL_EMULATOR_X86_COMMAND_LINE = SystemUtils.IS_OS_WINDOWS?"taskkill /F /IM emulator.exe":"pkill -f emulator-x86";
	private static final String KILL_EMULATOR_ARM_COMMAND_LINE = SystemUtils.IS_OS_WINDOWS?"taskkill /F /IM qemu-system-i386.exe":"pkill -f emulator-arm";

	private static Logger log = Logger.getLogger("AVDManager");

	public static void startEmulator(String deviceName, int deviceNumber) throws AgentException {
		int tries = 0;
		try {

			log.info("Device number " + deviceNumber);
			int emulatorsToStart = getConnectedEmulators();

			log.info("Emulators/Devices to be started : " + emulatorsToStart);
			while (deviceNumber > emulatorsToStart) {
				emulatorsToStart++;
				try{
					startAnEmulator(deviceName, deviceNumber);
				}catch(AgentException e){
					if(tries > 2)
						throw e;
					tries++;
				}
			}
			log.info("Emulators/Devices started : " + deviceNumber);

		} catch (Exception e) {
			throw new AgentException(e.getMessage(), e, null);
		}
	}

	public static void killEmulator() throws IOException, InterruptedException {
		log.info("About to kill emulators.");
		Process p = Runtime.getRuntime().exec(KILL_EMULATOR_X86_COMMAND_LINE);
		p.waitFor();
		p = Runtime.getRuntime().exec(KILL_EMULATOR_ARM_COMMAND_LINE);
		p.waitFor();
	}

	private static void startAnEmulator(String deviceName, int number)
			throws AgentException {
		log.info("Starting emulator");
		String command = null;
		try {
			command = START_EMULATOR_COMMAND_LINE.replace("<avd_name>", deviceName);

			if (ReadProperty
					.getProperty(PropertiesKeys.ANDROID_EMULATOR_PROXY_TEMPLATE
							+ ReadProperty.environment, null) != null) {
				log.info("Seting proxy '"
						+ ReadProperty
								.getProperty(PropertiesKeys.ANDROID_EMULATOR_PROXY_TEMPLATE
										+ ReadProperty.environment) + "' in "
						+ ReadProperty.getProperty(PropertiesKeys.DEVICE_NAME)
						+ " device.");
				command += PROXY_COMMAND_LINE
						+ ReadProperty
								.getProperty(PropertiesKeys.ANDROID_EMULATOR_PROXY_TEMPLATE
										+ ReadProperty.environment);
			}

		} catch (PropertyException pe) {
			throw new AgentException(pe.getMessage(), pe, null);
		}
		try {
			Runtime.getRuntime().exec(command);

			int timeout = 300;
			int time = 0;
			while (getConnectedEmulators() < number && time < timeout) {
				Thread.sleep(1000);
				time++;
			}

			if (getConnectedEmulators() < number) {
				throw new AgentException("Unable to start Android Emulator", null);
			} else {
				log.info("Emulator started. Waiting to be ready");
				waitForAnimationEnd();
			}
		} catch (Exception e) {
			throw new AgentException(e.getMessage(), e, null);
		}
	}

	private static void waitForAnimationEnd() throws IOException, InterruptedException, PropertyException {
		// Checks android properties for animation state
		Process p = Runtime.getRuntime().exec(CHECK_ANIM_COMMAND_LINE);
		int tries = 0;
		BufferedReader animation = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		
		Thread.sleep(Integer.valueOf(
				ReadProperty.getPropertyInt(
				PropertiesKeys.ANDROID_WAIT_FOR_READY, Constants.ANDROID_EMULATOR_WAIT_FOR_READY)) * 1000);
		
		while(!animation.readLine().contains("stopped")){
			Thread.sleep(5000);
			p = Runtime.getRuntime().exec(CHECK_ANIM_COMMAND_LINE);
			animation = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if(tries++ > 6)
				throw new InterruptedException("AVDManager.waitForAnimationEnd timeout");
		}
	}

	private static int getConnectedEmulators() throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec(GET_DEVICES_COMMAND_LINE);

		BufferedReader input = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line = null;

		int devices = 0;
		
		while ((line = input.readLine()) != null) {
			if (line.contains(Constants.EMULATOR_NAME) 
					&& line.contains(DEVICE_READY)) {
				devices++;
			} else if (line.contains(DEVICE_READY)) {
				devices++;
				log.info("Using physical device found: " + line.split("\t")[0]);
				killEmulator();
			}
		}

		return devices;
	}

	/**
	 * Gets the number of the port of a current emulator
	 * 
	 * @param deviceNumber
	 *            int the number id of the device running (1,2,etc)
	 * @param deviceName
	 *            String the name of the device to use
	 * @return String the port as a String
	 * @throws AgentException
	 */
	public static String getDevicePort(int deviceNumber, String deviceName)
			throws AgentException {

		startEmulator(deviceName, deviceNumber);

		String port = "";
		try {
			Process p = Runtime.getRuntime().exec(GET_DEVICES_COMMAND_LINE);

			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = null;

			int currentPortOrder = 1;
			
			while ((line = input.readLine()) != null) {
				if (line.contains(Constants.EMULATOR_NAME)) {
					if (deviceNumber == currentPortOrder) {
						port = line.substring(Constants.EMULATOR_NAME.length(),
								Constants.EMULATOR_NAME.length()
										+ Constants.PORT_LENGHT);
						log.info("Found port " + port);
						break;
					} else {
						currentPortOrder++;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return port;
	}
}
