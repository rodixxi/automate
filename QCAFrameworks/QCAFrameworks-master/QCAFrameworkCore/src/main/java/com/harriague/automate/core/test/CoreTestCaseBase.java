package com.harriague.automate.core.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

import com.harriague.automate.core.conf.Constants;

public class CoreTestCaseBase {

	/**
	 * Configure log4j
	 */
	public void configureLog4j() {
		StackTraceElement runnerClass = new Throwable().getStackTrace()[1];
		String className = runnerClass.getClassName().split("\\.")[runnerClass.getClassName().split("\\.").length-1];
		System.setProperty("logfile.name", Constants.LOG4J_LOG_FILE_PATH + className + Constants.LOG4J_LOG_FILE_EXTENSION);
		LogManager.resetConfiguration();
		LogManager.shutdown();
		PropertyConfigurator.configure(Constants.LOG4J_CONFIGURATION_FILE_PATH);
	}
}
