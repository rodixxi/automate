package com.harriague.automate.core.conf;

public interface PropertiesKeys {

	/*
	 * Default module used
	 */
	public static final String module = "module.used";
	
	/*
	 * Default applicationName
	 */
	public static final String appName = "application.name";
	
	/*
	 * Base package type
	 */
	public static final String basePackage = "project.base.package";
	
	/*
	 * Service start message
	 */
	public static final String serviceStartMessaje = "device.service.start.messaje";
	
	/*
	 * Service stop message
	 */
	public static final String serviceStopMessaje = "device.service.stop.messaje";
	
	/*
	 * Command line for install application 
	 */
	public static final String applicationCommandLineForInstall = "application.%s.command.install";
	
	/*
	 * Command line for uninstall application
	 */
	public static final String applicationCommandLineForUninstall = "application.%s.command.uninstall";
	
	/*
	 * Application process name
	 */
	public static final String applicationProcessName = "application.%s.process.name";
	
	/*
	 * Application type
	 */
	public static final String applicationType = "application.%s.type";
	
	/*
	 * Default browser
	 */
	public static final String DEFAULT_BROWSER = "default.browser";
	
	/*
	 * Default platform 
	 */
	public static final String DEFAULT_PLATFORM = "default.platform";
	
	public static final String DEFAULT_WAIT_FOR_PROCESS_TIME_OUT = "default.wait.for.process_timeout";
	
	public static final String EXCEL_REPORT_HEADERS = "report.headers";//Need to add "story,status,comment"

	public static final String ANDROID_APK_PATH = "android.apk.path";

    public static final String IOS_IPA_PATH = "ios.ipa.path";
}
