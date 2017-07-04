package com.harriague.automate.module.windows.web.conf;

import com.harriague.automate.core.structures.FlawedTimeUnit;
import com.harriague.automate.core.utils.CoreUtils;

public class Constants {

	public static final String BROWSER_CHROME_NAME = "CHROME";
	public static final String BROWSER_DEFAULT_NAME = "INTERNET EXPLORER";
	public static final String BROWSER_FIREFOX_NAME = "FIREFOX";
	public static final String BROWSER_OPERA_NAME = "OPERA";

	//public static final String PATH_WEB_DRIVERS_FOLDER = "/WebDrivers/";
	public static final String PATH_WEB_DRIVERS_FOLDER = "/WebDrivers/"+(CoreUtils.is64BitOS()?"64/":"32/");
	public static final String DRIVER_CHROME_FILE_NAME="chromedriver.exe";
	public static final String DRIVER_DEFAULT_BROWSER_FILE_NAME="IEDriverServer.exe";
	public static final String DRIVER_OPERA_FILE_NAME="operadriver.exe";
	public static final String PROPERTY_CHROMEDRIVER = "webdriver.chrome.driver";
	public static final String PROPERTY_DEFAULT_BROWSER_DRIVER = "webdriver.ie.driver";
	public static final String RESOURCES_FOLDER = "resources/";
	
	public static final FlawedTimeUnit DEFAULT_DRIVER_QUICK_SEARCH = FlawedTimeUnit.seconds(5);
	public static final String ATTRIBUTE_VALUE = "value";
	
}
