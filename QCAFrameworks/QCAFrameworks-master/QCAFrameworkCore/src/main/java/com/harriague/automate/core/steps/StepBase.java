/**
 *
 */
package com.harriague.automate.core.steps;


import static org.springframework.util.Assert.isTrue;

import com.harriague.automate.core.device.Utils;
import com.harriague.automate.core.exceptions.DeviceException;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import com.harriague.automate.core.conf.Constants;
import com.harriague.automate.core.conf.PropertiesKeys;
import com.harriague.automate.core.device.Device;
import com.harriague.automate.core.device.DeviceManager;
import com.harriague.automate.core.utils.ReadProperty;


/**
 * Base step class for all steps
 */
public class StepBase {

    //@Autowired
    //protected DataStore dataStore;

    private String browserName;

    protected String platform;
    protected String platformId;
    protected String deviceKey;

    protected String applicationName;
    protected String applicationId;

    private boolean url = false;
    private boolean urlIsOpen = false;
    private boolean changeAppName = false;
    private String separator = "-";

    protected final Logger log = Logger.getLogger(StepBase.class.getName());
    protected static final Logger slog = Logger.getLogger(StepBase.class.getName());

    private void initialize() throws Exception {

        boolean appExist = false;
        String browserName = null;

        String appKey = (applicationName == null)
                ? ReadProperty.getProperty(PropertiesKeys.appName, null) : applicationName;

        deviceKey = (platform != null && platformId != null) ? platform + "-" + platformId : null;

        if (url) {
            browserName =
                    (this.browserName == null)
                            ? ReadProperty.getProperty(PropertiesKeys.DEFAULT_BROWSER,
                                    Constants.BROWSER_INTERNET_EXPLORER_NAME)
                            : this.browserName.toUpperCase();
            appKey += separator + browserName;

            if (!urlIsOpen) {
                browserName += (applicationId != null) ? separator + applicationId : "";
                Device device = DeviceManager.getDevice(deviceKey);
                if (device.containsAnApplication(browserName) != null) {
                    appExist = true;
                } else {
                    appExist = false;
                }
            }

        }
        appKey = (applicationId != null) ? appKey + separator + applicationId : appKey;

        // Conditions to change name
        if (changeAppName && DeviceManager.getCurrentDevice()
                .applicationExists(DeviceManager.getAppKeyActive())) {
            String keySession = (DeviceManager.getAppKeyActive().split("-").length == 3)
                    ? DeviceManager.getAppKeyActive().split("-")[2] : null;
            if (url && DeviceManager.getAppKeyActive().split("-")[1].equals(browserName)
                    && String.valueOf(keySession).equals(String.valueOf(applicationId))) {
                DeviceManager.getCurrentDevice().changeAppName(DeviceManager.getAppKeyActive(),
                        appKey);
            }
        }

        if (DeviceManager.getCurrentApp() == null || DeviceManager.getCurrentApp() != null
                && appKey != null && !DeviceManager.getCurrentApp().getAppName().equals(appKey)) {
            DeviceManager.setCurrentDevice(DeviceManager.getDevice(deviceKey));
            DeviceManager.setCurrentApp(
                    DeviceManager.getCurrentDevice().getApplication(appKey, browserName));
            DeviceManager.setAppKeyActive(appKey);
            this.changeAppName = false;
        }

        if (!urlIsOpen && appExist) {
            DeviceManager.getCurrentApp().navigateTo(applicationName);
        }
        urlIsOpen = true;

    }

    /**
     * Get page
     * 
     * @param type page
     * @return Page
     * @throws Exception
     */
    protected <T extends Object> T getPage(Class<T> type) throws Exception {
        initialize();
        return DeviceManager.getCurrentApp().getPage(type);
    }

    /**
     * Force page creation
     * 
     * @param type
     * @return
     * @throws Exception
     */
    protected <T extends Object> T getPageForce(Class<T> type) throws Exception {
        initialize();
        return DeviceManager.getCurrentApp().getPageForce(type);
    }

    /**
     * APPLICATION STEPS APPLICATION STEPS
     */

    /**
     * Switches to a given application, if it does not exist it's created.
     * 
     * @param appName
     * @throws DeviceException
     * @throws PropertyException
     */
    @Alias("abrir aplicacion $appName")
    @Given("open $appName application")
    public void openApp(String appName) throws Exception {
        applicationName = appName;
        initialize();
        Thread.sleep(7000);
    }

    /**
     *
     * @param appName
     * @throws Exception
     */
    @Alias("cambiar a la aplicacion $appName")
    @Given("swich to $appName application")
    public void swichApp(String appName) throws Exception {
        applicationName = appName;
        applicationId = null;
        initialize();
    }

    /**
     * Switches to a given application with an ID, if it does not exist it's created.
     * 
     * @param appName
     * @param appId
     * @throws DeviceException
     * @throws PropertyException
     */
    @Alias("abrir la aplicacion $appName en una nueva sesion con id $name")
    @Given("open $appName application in new session $name")
    public void openAppId(String appName, String name) throws Exception {
        applicationName = appName;
        applicationId = name;
        initialize();
    }

    /**
     * Switches to a given application in the specified platform, if it does not exist it's created.
     * 
     * @param appName
     * @param platform
     * @throws DeviceException
     * @throws PropertyException
     */
    @Alias("Cambiar a aplicacion $appName en $platform como plataforma")
    @Given(value = "Switch to $appName application in $platform platform", priority = 4)
    public void openAppInPlatform(String appName, String platform) throws Exception {
        applicationName = appName;
        this.platform = platform;
        initialize();
    }

    /**
     * Switches to a given application in the specified platform with an ID, if it does not exist
     * it's created.
     * 
     * @param appName
     * @param platform
     * @param platformId
     * @throws DeviceException
     * @throws PropertyException
     */
    @Alias("Cambiar a aplicacion $appName en $platform como plataforma (con $platformId como ID)")
    @Given(value = "Switch to $appName application in $platform platform (with $platformId ID)", priority = 3)
    public void openAppInPlatformId(String appName, String platform, String platformId)
            throws Exception {
        applicationName = appName;
        this.platform = platform;
        this.platformId = platformId;
        initialize();
    }

    /**
     * Switches to a given application with an ID in the specified platform, if it does not exist
     * it's created.
     * 
     * @param appName
     * @param appId
     * @param platform
     * @throws DeviceException
     * @throws PropertyException
     */
    @Alias("Cambiar a aplicacion $appName (con $appId como ID) en $platform como plataforma")
    @Given(value = "Switch to $appName application (with $appId ID) in $platform platform", priority = 2)
    public void openAppIdInPlatform(String appName, String appId, String platform)
            throws Exception {
        applicationName = appName;
        applicationId = appId;
        this.platform = platform;
        initialize();
    }

    /**
     * Switches to a given application with an ID in the specified platform with an ID, if it does
     * not exist it's created.
     * 
     * @param appName
     * @param appId
     * @param platform
     * @param platformId
     * @throws DeviceException
     * @throws PropertyException
     */
    @Alias("Cambiar a aplicacion $appName (con $appID como ID) en $platform como plataforma (con $platformId como ID)")
    @Given(value = "Switch to $appName application (with $appId ID) in $platform platform (with $platformId ID)",
            priority = 1)
    public void openAppIdInPlatformId(String appName, String appId, String platform,
            String platformId) throws Exception {
        this.platform = platform;
        this.platformId = platformId;
        applicationName = appName;
        applicationId = appId;
        initialize();
    }

    /**
     * URL NAVIGATION STEPS URL NAVIGATION STEPS
     */

    /**
     *
     * @param url
     * @throws PropertyException
     * @throws DeviceException
     */
    @Given("Navigate to the $url url")
    public void navigateUrl(String url) throws Exception {
        this.url = true;
        this.urlIsOpen = false;
        applicationName = url;
        browserName = null;
        changeAppName = true;
        initialize();
    }

    @Given("swich to browser ( $url )")
    public void swichToDefaultBrowser(@Named("url") String url) throws Exception {
        this.url = true;
        applicationName = url;
        browserName = null;
        applicationId = null;
        initialize();
    }

    @Given("swich to browser $browserName ( $url )")
    public void swichToBrowser(@Named("url") String url, @Named("browserName") String browserName)
            throws Exception {
        this.url = true;
        applicationName = url;
        this.browserName = browserName;
        applicationId = null;
        initialize();
    }

    @Given(value = "swich to browser $browserName session $session ( $url )", priority = 1)
    public void swichToBrowser(@Named("url") String url, @Named("browserName") String browserName,
            @Named("session") String session) throws Exception {
        this.url = true;
        applicationName = url;
        this.browserName = browserName;
        applicationId = session;
        initialize();
    }

    @Given("Navigate to the $url url in new session name $name")
    public void navigateUrl(String url, String name) throws Exception {
        this.url = true;
        this.urlIsOpen = false;
        applicationName = url;
        applicationId = name;
        browserName = null;
        initialize();
    }

    @Given("Navigate to the $url url in $browser browser by session name $name")
    public void navigateUrl(String url, String browser, String name) throws Exception {
        this.url = true;
        this.urlIsOpen = false;
        applicationName = url;
        applicationId = name;
        browserName = browser;
        initialize();
    }

    @Given("Navigate to the $url url in the $browser browser")
    public void navigateUrlBrowser(String url, String browser) throws Exception {
        this.url = true;
        applicationName = url;
        browserName = browser;
        changeAppName = true;
        this.urlIsOpen = false;
        initialize();
    }

    @Given("Navigate to the $url url in $platform platform")
    public void navigateUrlInPlatform(String url, String platform) throws Exception {
        this.url = true;
        applicationName = url;
        this.platform = platform;
        initialize();
    }

    @Given("Navigate to the $url url in $platform platform (with $platformId ID)")
    public void navigateUrlInPlatformId(String url, String platform, String platformId)
            throws Exception {
        this.url = true;
        applicationName = url;
        this.platform = platform;
        this.platformId = platformId;
        initialize();
    }

    @Given(value = "Navigate to the $url url in the $browser browser in $platform platform",
            priority = 2)
    public void navigateUrlBrowserInPlatform(String url, String browser, String platform)
            throws Exception {
        this.url = true;
        applicationName = url;
        applicationId = browser;
        this.platform = platform;
        initialize();
    }

    @Given(value = "Navigate to the $url url in the $browser browser in $platform platform (with $platformId ID)",
            priority = 1)
    public void navigateUrlBrowserInPlatformId(String url, String browser, String platform,
            String platformId) throws Exception {
        this.url = true;
        this.platform = platform;
        this.platformId = platformId;
        applicationName = url;
        applicationId = browser;
        initialize();
    }

    @When("close application")
    public void closeApplication() throws Exception {
        isTrue(DeviceManager.getCurrentApp().close(), "The application is not stopped");
    }

    @Then("the application key should be $appKeyExpected")
    public void checkAppKey(String appKeyExpected) {
        log.info("Checking application key is correct...");
        Assert.assertEquals("The key '" + appKeyExpected + "' was not found", appKeyExpected,
                DeviceManager.getAppKeyActive());
    }

    /**
     * This step installs an Android application
     * 
     * @param appName String referring the name of the app to be installed
     */
    @When("the application $appName is installed")
    public void installApp(String appName) throws Exception {
        log.info("Installing app " + appName);
        Utils utils = DeviceManager.getCurrentDevice().getUtil();
        try {
            utils.installApplication(appName);
        } catch (DeviceException e) {
            log.error("application " + appName + " was not installed");
            e.printStackTrace();
            Assert.fail();
        }
    }

    /**
     * This step launch an app
     * 
     * @param appName
     */
    @When("the application $appName is launched")
    public void launchApp(String appName) {
        log.info("Launching  app " + appName);
        Utils utils = DeviceManager.getCurrentDevice().getUtil();
        utils.launchApp(appName);
    }
}
