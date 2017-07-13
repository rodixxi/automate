package com.harriague.automate.module.linux.web.conf;

import com.harriague.automate.core.structures.FlawedTimeUnit;
import com.harriague.automate.core.utils.CoreUtils;

public class Constants {

	public static final String BROWSER_CHROME_NAME = "CHROME";
	public static final String BROWSER_FIREFOX_NAME = "FIREFOX";
	public static final String BROWSER_OPERA_NAME = "OPERA";
	public static final String BROWSER_DEFAULT_NAME = "FIREFOX";
	
	//public static final String PATH_WEB_DRIVERS_FOLDER = "/WebDrivers/";
	public static final String PATH_WEB_DRIVERS_FOLDER = "/WebDrivers/Linux/"+(CoreUtils.is64BitOS()?"64/":"32/");
	public static final String DRIVER_CHROME_FILE_NAME="chromedriver";
	public static final String DRIVER_FIREFOX_FILE_NAME = "geckodriver";
	public static final String DRIVER_OPERA_FILE_NAME="operadriver";
	public static final String PROPERTY_CHROMEDRIVER = "webdriver.chrome.driver";
	public static final String RESOURCES_FOLDER = "./resources/";
	public static final String DRIVER_DEFAULT_BROWSER_FILE_NAME = "";
    public static final String PROPERTY_FIREFOX_DRIVER = "webdriver.gecko.driver";
	public static final String PROPERTY_DEFAULT_BROWSER_DRIVER = "";
	
	public static final FlawedTimeUnit DEFAULT_DRIVER_QUICK_SEARCH = FlawedTimeUnit.seconds(5);
	public static final String ATTRIBUTE_VALUE = "value";
	
}
