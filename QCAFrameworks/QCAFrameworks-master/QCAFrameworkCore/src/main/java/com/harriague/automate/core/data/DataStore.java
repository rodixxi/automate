package com.harriague.automate.core.data;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Contains commons data for all test case
 */
public class DataStore {

	/*
	 * Properties of all configuration files
	 */
	private Properties properties;

	/**
	 * Constructor
	 * 
	 * @param properties
	 *            all properties
	 */
	public DataStore(Properties properties) {
		this.properties = properties;
	}

	/**
	 * Get a property
	 * 
	 * @param key
	 *            value
	 * @return Object with value of the property
	 */
	public String getProperty(String key) {
		log.info("The value of the property '" + key + "' is "
				+ this.properties.getProperty(key));
		return this.properties.getProperty(key);
	}

	/**
	 * Set a property
	 * 
	 * @param key
	 *            vaue
	 * @param value
	 */
	public void setProperty(String key, String value) {
		this.properties.setProperty(key, value);
	}

	/*
	 * Logger object
	 */
	private Logger log = Logger.getLogger(getClass().getName());

}
