package com.harriague.automate.core.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.harriague.automate.core.app.Application;
import com.harriague.automate.core.conf.Parameters;
import com.harriague.automate.core.exceptions.DeviceException;
import com.harriague.automate.core.utils.CoreUtils;

public class DeviceManager {

    private static String s_appKeyActive = "";

    private static Application s_currentApp = null;

    private static Device s_currentDevice = null;

    /**
     * Logger object
     */
    private static Logger s_log = Logger.getLogger(DeviceManager.class.getName());

    /**
     * Devices map
     */
    private static Map<String, Device> s_devices = new HashMap<String, Device>();

    /**
     * Default name
     */
    private final static String DEFAULT_DEVICE_NAME = "default";

    /**
     * Template of the devices implement package
     */
    private static final String deviceImplPackageTemplate =
            "com.harriague.automate.core.device.impl.%sDeviceImpl";

    /**
     * Template of the utils implement package
     */
    public static final String utilsImplPackageTemplate =
            "com.harriague.automate.core.device.impl.%sUtilsImpl";

    /**
     * Get a specific
     * 
     * @param deviceKey
     * @return
     * @throws DeviceException
     */
    public static Device getDevice(String deviceKey) throws DeviceException {

        deviceKey =
                (deviceKey == null) ? System.getProperty(Parameters.RUNNER_PARAMETER_PLATFORM_NAME)
                        + "-" + DEFAULT_DEVICE_NAME : deviceKey;

        if (!s_devices.containsKey(deviceKey)) {
            createDevice(deviceKey);
        }
        return s_devices.get(deviceKey);
    }

    /**
     * Return all devices
     * 
     * @return List<Device> devices list
     */
    public static List<Device> getDevices() {
        List<Device> devices = new ArrayList<Device>();
        devices.addAll(s_devices.values());
        return devices;
    }

    /**
     * Create a specific device
     * 
     * @param deviceName device Key
     * @return Device
     * @throws DeviceException
     */
    public static Device createDevice(String deviceKey) throws DeviceException {
        String platform = deviceKey.split("-")[0].toLowerCase();
        s_log.info("Creating a device " + platform + " instance...");
        try {
            s_log.debug("Creating device utils intance");

            Utils utils = CoreUtils.getInstance(Utils.class, utilsImplPackageTemplate,
                    platform.substring(0, 1).toUpperCase() + platform.substring(1));
            s_log.debug("The device utils object is created.");
            s_log.debug("Creating device intance");
            Device device =
                    CoreUtils.getInstance(Device.class, deviceKey, utils, deviceImplPackageTemplate,
                            platform.substring(0, 1).toUpperCase() + platform.substring(1));
            s_log.debug("The device object is created.");
            s_devices.put(deviceKey, device);
            s_currentDevice = device;
            s_log.info("Create device success and save in devices list");
            return device;
        } catch (Exception e) {
            throw new DeviceException(e);
        }
    }

    /**
     * Close all devices
     * 
     * @throws DeviceException
     */
    public static void closeDevices() throws DeviceException {
        s_log.warn("Closing all devices and applications");
        s_currentApp = null;
        s_currentDevice = null;
        if (getDevices() != null) {
            for (Device device : getDevices()) {
                device.close();
                s_log.info("The device '" + device.getDeviceName() + "' was closed");
            }
        }
        s_log.info("All devices and application were closed");
        s_devices = new HashMap<String, Device>();
    }

    /**
     * Check device exist
     * 
     * @param deviceKey device key
     * @return <true> the device exist
     */
    public static boolean deviceExists(String deviceKey) {
        return s_devices.containsKey(deviceKey);
    }

    public static String getAppKeyActive() {
        return s_appKeyActive;
    }

    public static void setAppKeyActive(String appKeyActive) {
        s_appKeyActive = appKeyActive;
    }

    /**
     * @return the currentApp
     */
    public static Application getCurrentApp() {
        return s_currentApp;
    }

    /**
     * @param currentApp the currentApp to set
     */
    public static void setCurrentApp(Application currentApp) {
        s_currentApp = currentApp;
    }

    /**
     * @return the currentDevice
     */
    public static Device getCurrentDevice() {
        return s_currentDevice;
    }

    /**
     * @param currentDevice the currentDevice to set
     */
    public static void setCurrentDevice(Device currentDevice) {
        s_currentDevice = currentDevice;
    }


}
