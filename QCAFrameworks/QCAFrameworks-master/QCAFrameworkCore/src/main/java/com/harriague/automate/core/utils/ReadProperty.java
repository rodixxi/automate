package com.harriague.automate.core.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.harriague.automate.core.conf.Parameters;
import com.harriague.automate.core.exceptions.PropertyException;

/**
 * Read language property
 */
public class ReadProperty {

    /**
     * Template of the application property file path
     */
    private static final String DEVICE_APP_PROPERTIES_FILE_PATH = "conf/application/app.properties";

    /**
     * Template of the device property file path
     */
    private static final String DEVICE_PROPERTIES_FILE_PATH = "conf/device/%s/info.properties";

    /**
     * Template of the environment property file path
     */
    private static final String ENVIRONMENT_PROPERTIES_FILE_PATH = "conf/env/%s/app.properties";

    /**
     * Template of the general property file path
     */
    private static final String GENERAL_PROPERTIES_FILE_PATH = "conf/info.properties";

    /*
     * Constant
     */
    public static final String GENERAL_PROPERTY = "general";

    /**
     * Device property full path
     */
    private static String devicePropertyPath;

    /**
     * Environment property full path
     */
    private static String environmentPropertyPath;

    /**
     * Property object
     */
    private static Properties applicationProp = null;

    /**
     * Property object
     */
    private static Properties deviceProp = null;

    /**
     * Property object
     */
    private static Properties environmentProp = null;

    /**
     * Property object
     */
    private static Properties generalProp = null;

    /**
     * Device property
     */
    public static String device;

    /**
     * Environment property
     */
    public static String environment;

    /**
     * Logger object
     */
    protected static Logger log = Logger.getLogger(ReadProperty.class.getName());

    /**
     * Create all paths of the properties
     */
    private static void createPropertyPaths() {
        String device = "";
        String env = "";
        // find device in execution params
        if (System.getProperty(Parameters.RUNNER_PARAMETER_PLATFORM_NAME) == null) {
            // the param 'platform' is empty, get platform from info.properties
            device = generalProp.getProperty("module.used");
        } else {
            device = System.getProperty(Parameters.RUNNER_PARAMETER_PLATFORM_NAME);
        }

        // find device in execution params
        if (System.getProperty(Parameters.RUNNER_PARAMETER_ENVIRONMENT_NAME) == null) {
            // the param 'platform' is empty, get platform from info.properties
            try {
                env = generalProp.getProperty("default.env");
            } catch (Exception exception) {
                log.warn("The param 'env' and property 'default.env' is empty");
            }
        } else {
            env = System.getProperty(Parameters.RUNNER_PARAMETER_ENVIRONMENT_NAME);
        }
        ReadProperty.device = device;
        ReadProperty.environment = env;
        devicePropertyPath = String.format(DEVICE_PROPERTIES_FILE_PATH, device);
        environmentPropertyPath = String.format(ENVIRONMENT_PROPERTIES_FILE_PATH, env);
    }

    /**
     * Initialize method
     */
    private static void initilize() {
        if (deviceProp == null || environmentProp == null) {
            applicationProp = new Properties();
            deviceProp = new Properties();
            environmentProp = new Properties();
            generalProp = new Properties();
            try {
            	InputStream buff = ReadProperty.class.getClassLoader().getResourceAsStream(DEVICE_APP_PROPERTIES_FILE_PATH);
                applicationProp.load(ReadProperty.class.getClassLoader()
                        .getResourceAsStream(DEVICE_APP_PROPERTIES_FILE_PATH));
                generalProp.load(ReadProperty.class.getClassLoader()
                        .getResourceAsStream(GENERAL_PROPERTIES_FILE_PATH));
            } catch (Exception e) {
                log.error("Can not find all properties file.");
            }
            try {
                createPropertyPaths();
                deviceProp.load(ReadProperty.class.getClassLoader()
                        .getResourceAsStream(devicePropertyPath));
                environmentProp.load(ReadProperty.class.getClassLoader()
                        .getResourceAsStream(environmentPropertyPath));
            } catch (Exception e) {
            }
        }
    }

    /**
     * Get property value
     *
     * @param key to find
     * @return String key value
     * @throws Exception
     */
    public static String getProperty(String key) throws PropertyException {

        initilize();

        String existInProp = findPropertyInAllsPropertiesObject(key);

        if (existInProp == null) {
            log.error("The key " + key + " not exist...");
            throw new PropertyException("The key " + key + " not exist.");
        }

        switch (existInProp) {
            case Parameters.RUNNER_PARAMETER_APPLICATION_NAME:
                return applicationProp.getProperty(key);

            case Parameters.RUNNER_PARAMETER_PLATFORM_NAME:
                return deviceProp.getProperty(key);

            case Parameters.RUNNER_PARAMETER_ENVIRONMENT_NAME:
                return environmentProp.getProperty(key);

            case GENERAL_PROPERTY:
                return generalProp.getProperty(key);

            default:
                log.error("The key " + key + " not exist.");
                throw new PropertyException("The key " + key + " not exist.");
        }

    }

    /**
     * Find key in properties
     * 
     * @param key key name
     * @return String name of property file
     */
    private static String findPropertyInAllsPropertiesObject(String key) {
        String existInProp = (applicationProp.containsKey(key))
                ? Parameters.RUNNER_PARAMETER_APPLICATION_NAME : null;
        existInProp = (existInProp == null && deviceProp.containsKey(key))
                ? Parameters.RUNNER_PARAMETER_PLATFORM_NAME : existInProp;
        existInProp = (existInProp == null && environmentProp.containsKey(key))
                ? Parameters.RUNNER_PARAMETER_ENVIRONMENT_NAME : existInProp;
        existInProp = (existInProp == null && generalProp.containsKey(key)) ? GENERAL_PROPERTY
                : existInProp;
        return existInProp;
    }

    /**
     * Check if exist key
     * 
     * @param key to find
     * @return <true> the key exist
     */
    public static boolean isPropertyExist(String key) {
        initilize();
        return findPropertyInAllsPropertiesObject(key) != null;
    }

    /**
     * Get int value from property file
     * 
     * @param key to find
     * @return int value
     * @throws PropertyException
     */
    public static int getPropertyInt(String key) throws PropertyException {
        int value = 0;
        try {
            value = Integer.valueOf(getProperty(key)).intValue();
        } catch (NumberFormatException e) {
            throw new PropertyException("The key '" + key
                    + "' is not a int value. The actual value is '" + getProperty(key) + "'.");
        }
        return value;
    }

    /**
     * Get int value from property file
     * 
     * @param key to find
     * @param defaultValue
     * @return int value
     * @throws PropertyException
     */
    public static int getPropertyInt(String key, int defaultValue) throws PropertyException {
        if (isPropertyExist(key)) {
            return getPropertyInt(key);
        } else {
            return defaultValue;
        }
    }

    /**
     * Get a key
     * 
     * @param key to find
     * @param defaultValue
     * @return String property value
     * @throws PropertyException
     */
    public static final String getProperty(String key, String defaultValue)
            throws PropertyException {
        if (isPropertyExist(key)) {
            return getProperty(key);
        } else {
            return defaultValue;
        }
    }
}
