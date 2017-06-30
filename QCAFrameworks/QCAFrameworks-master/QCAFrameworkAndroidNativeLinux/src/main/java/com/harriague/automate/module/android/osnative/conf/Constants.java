package com.harriague.automate.module.android.osnative.conf;

import com.harriague.automate.core.structures.FlawedTimeUnit;

public class Constants {

	public enum Browsers {
		DEFAULT("Browser");

		private String browserName;

		Browsers(String browserName) {
			this.browserName = browserName;
		}

		public String getBrowserName() {
			return this.browserName;
		}

		public static boolean isDefaultBrowser(String name) {
			if (Browsers.DEFAULT.getBrowserName().equals(name)) {
				return true;
			}
			return false;
		}

	}

	public static final String APPIUM_AUTOMATION_TYPE = "Appium";
	
	public static final String APPIUM_AUTOMATION_PLATFORM = "Android";
	
	public static final String APPIUM_URL = "/wd/hub";
	
	public static final FlawedTimeUnit DEFAULT_DRIVER_QUICK_SEARCH = FlawedTimeUnit.seconds(5);
	
	public static final String ATTRIBUTE_VALUE = "value";
	
	public static final String APPIUM_AUTOMATOR_SELENDROID = "SELENDROID";
	
	public static final String EMULATOR_NAME = "emulator-";
	
	public static final int PORT_LENGHT = 4;
	
	public static final int ANDROID_EMULATOR_WAIT_FOR_READY = 30;

}
