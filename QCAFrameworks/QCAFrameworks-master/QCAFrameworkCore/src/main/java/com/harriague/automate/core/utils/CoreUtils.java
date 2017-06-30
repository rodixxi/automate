package com.harriague.automate.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.conf.PropertiesKeys;
import com.harriague.automate.core.device.DeviceManager;
import com.harriague.automate.core.device.Utils;

/**
 * Utils for core
 */
public class CoreUtils {

    /**
     * Return a implementation of the a interface
     * 
     * @param type of the interface
     * @param packageTemplate template of the implementation to use
     * @return a implementation of the interface
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static <T extends Object> T getInstance(Class<T> type, String... args) throws Exception {
        String className = (args.length > 1) ? String.format(args[0], args[1])
                : String.format(args[0], type.getSimpleName());
        Class cls = Class.forName(className);
        return type.cast(cls.newInstance());
    }

    /**
     * Return a implementation of the a interface
     * 
     * @param type of the interface
     * @param packageTemplate template of the implementation to use
     * @return a implementation of the interface
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static <T extends Object> T getInstance(Class<T> type, String deviceName, Utils util,
            String... args) throws Exception {
        String className = (args.length > 1) ? String.format(args[0], args[1])
                : String.format(args[0], type.getSimpleName());
        Class cls = Class.forName(className);
        @SuppressWarnings("unchecked")
        Constructor<T> constructor = cls.getConstructor(Utils.class, String.class);
        Object classImpl = constructor.newInstance(util, deviceName);
        return type.cast(classImpl);
    }

    /**
     * Return a implementation of the a interface
     * 
     * @param type of the interface
     * @param packageTemplate template of the implementation to use
     * @return a implementation of the interface
     * @throws Exception
     */
    public static <T extends Object> T getPageInstance(Class<T> type, String template, Agent agent)
            throws Exception {
        String className = String.format(template, type.getSimpleName());
        @SuppressWarnings("rawtypes")
        Class cls = Class.forName(className);
        @SuppressWarnings("unchecked")
        Constructor<T> constructor = cls.getConstructor(Agent.class);
        Object pageImpl = constructor.newInstance(agent);
        return type.cast(pageImpl);
    }

    public static boolean is64BitOS() {
        if (System.getProperty("os.name").contains("Windows")) {
            return System.getenv("ProgramFiles(x86)") != null;
        } else {
            Process p = null;
            try {
                p = Runtime.getRuntime().exec("/bin/uname -m");
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s = null;
            try {
                while ((s = stdInput.readLine()) != null) {
                    if (s.contains("x86_64"))
                        return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static Utils getDeviceUtil(String platform) throws Exception {
        return getInstance(Utils.class, DeviceManager.utilsImplPackageTemplate,
                platform.substring(0, 1).toUpperCase() + platform.substring(1));
    }

    public static Utils getOSUtil() throws Exception {
        String platform = ReadProperty.getProperty(PropertiesKeys.DEFAULT_PLATFORM);
        return getDeviceUtil(platform);
    }
}
