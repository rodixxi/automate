package com.harriague.automate.core.conf;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;

public class Constants {
    /**
     * Application types
     */
    public static enum AppType {
        WEB, HYBRID, OSNATIVE, ANDROID_OSNATIVE;

        public static AppType getApptype(String value) throws Exception {
            final String parsedValue = value.toUpperCase();
            for (int i = 0; i < AppType.values().length; i++) {
                if (AppType.values()[i].toString().equals(parsedValue)) {
                    return AppType.values()[i];
                }
            }
            throw new Exception("The value '" + parsedValue + "' is not an application type");
        }

        @Override
        public String toString() {
            return super.toString().replaceAll("_", ".");
        }
    }

    // Browsers name
    public static final String BROWSER_CHROME_NAME = "CHROME";
    public static final String BROWSER_OPERA_NAME = "OPERA";
    public static final String BROWSER_INTERNET_EXPLORER_NAME = "INTERNET EXPLORER";
    public static final String BROWSER_FIREFOX_NAME = "FIREFOX";

    // Log4j properties
    public static final String LOG4J_CONFIGURATION_FILE_PATH =
            "./src/main/resources/log4j.properties";
    public static final String LOG4J_LOG_FILE_PATH = "./target/logs/";
    public static final String LOG4J_LOG_FILE_EXTENSION = ".txt";

    // Spring configuration
    public static final String SPRING_CONTEXT_FILE = "common-context.xml";

    // Windows command
    public static final String WINDOWS_TASK_KILL = "taskkill /F /IM ";

    // Services messages
    public static final String DEFAULT_START_SERVICE_MESSAGE = "started successfully.";
    public static final String DEFAULT_STOP_SERVICE_MESSAGE = "stopped successfully.";

    // Reports folder
    public static final String SCREENSHOT_FOLDER_PATH = "target/screenshots";
    public static final String LOG_FOLDER_PATH = "target/logs";

    // Report
    public static final String LOG_GROUP_SYSLOGS = "id=\"sys\"";
    public static final String LOG_GROUP_LOGS = "id=\"logsSec\"";
    public static final String CHROME_DRIVE_PROCESS_NAME =
            SystemUtils.IS_OS_WINDOWS ? "chromedriver.exe" : "chromedriver";
    public static final String PROTOCOL_HTTP = " http://";

    /**
     * Get process name by browser name
     * 
     * @return Map browser name / process name
     */
    public static Map<String, String> getBrowserProcessName() {
        Map<String, String> browserProcessName = new HashMap<String, String>();
        browserProcessName.put(BROWSER_FIREFOX_NAME, "firefox.exe");
        browserProcessName.put(BROWSER_CHROME_NAME, CHROME_DRIVE_PROCESS_NAME + "-chrome.exe");
        browserProcessName.put(BROWSER_OPERA_NAME, CHROME_DRIVE_PROCESS_NAME + "-opera.exe");
        browserProcessName.put(BROWSER_INTERNET_EXPLORER_NAME, "IEDriverServer.exe-iexplorer.exe");
        return browserProcessName;
    }
}
