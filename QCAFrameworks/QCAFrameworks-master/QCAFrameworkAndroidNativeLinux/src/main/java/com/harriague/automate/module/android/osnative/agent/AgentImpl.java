package com.harriague.automate.module.android.osnative.agent;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;
import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.device.Utils;
import com.harriague.automate.core.device.impl.AndroidUtilsImpl;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.exceptions.DeviceException;
import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.structures.ScrollDirection;
import com.harriague.automate.core.structures.SwipeDirection;
import com.harriague.automate.core.structures.FlawedTimeUnit;
import com.harriague.automate.core.utils.ReadProperty;
import com.harriague.automate.module.android.osnative.conf.AVDManager;
import com.harriague.automate.module.android.osnative.conf.AppiumController;
import com.harriague.automate.module.android.osnative.conf.Constants;
import com.harriague.automate.module.android.osnative.conf.PropertiesKeys;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.testng.Assert;

public class AgentImpl implements Agent {

    private final static String SEPARATOR = java.io.File.separator;
    private static final String ADB_LOGCAT = "logcat -d";
    private static final String ADB_LOGCAT_FLUSH = "logcat -c";
    private static final String FILTER_OUTPUT = "|grep ";
    private static final String ADB_COMMAND = System.getenv("ANDROID_HOME") + SEPARATOR + "platform-tools" + SEPARATOR +"adb -s ";

    /**
     * This enum represents the different output formats that can be specified to the logcat
     * Log messages contain a number of metadata fields, in addition to the tag and priority.
     * You can modify the output format for messages so that they display a specific metadata field.
     */
    public static enum OutputFormat {
        BRIEF("-v brief"), PROCESS("-v process"), TAG("-v tag"), RAW("-v raw"), TIME("-t time"),
        THREADTIME("-v threadtime"), LONG("-v long");
        public final String format;

        OutputFormat(String format) {
            this.format = format;
        }
    }

    /**
     * Every Android log message has a tag and a priority associated with it.
     * The priority is one of the following character values, ordered from lowest to highest priority:
     * V — Verbose (lowest priority)
     * D — Debug
     * I — Info
     * W — Warning
     * E — Error
     * F — Fatal
     * S — Silent (highest priority, on which nothing is ever printed)
     */
    public static enum LogLevel {
        VERBOSE(":V"), DEBUG(":D"), INFO(":I"), WARNING(":W"), ERROR(":E"), FATAL(":F"), SILENT(":S");
        public final String logLevel;

        LogLevel(String logLevel) {
            this.logLevel = logLevel;
        }
    }

    /**
     * The Android logging system keeps multiple circular buffers for log messages,
     * and not all of the log messages are sent to the default circular buffer.
     * To see additional log messages, you can run the logcat command with the -b option,
     * to request viewing of an alternate circular buffer.
     * You can view any of these alternate buffers:
     * radio — View the buffer that contains radio/telephony related messages.
     * events — View the buffer containing events-related messages.
     * main — View the main log buffer (default)
     */
    public static enum AlternativeLogBuffer {
        RADIO("-b radio"), EVENTS("-b events"), MAIN("-b main");
        public final String alternativeLogBuffer;

        AlternativeLogBuffer(String alternativeLogBuffer) {
            this.alternativeLogBuffer = alternativeLogBuffer;
        }
    }

    /**
     * The tag of a log message is a short string indicating the system component from which the message originates
     * (for example, "View" for the view system).
     */
    public static enum Tag {
        ALL("*");
        public final String tag;

        Tag(String tag) {
            this.tag = tag;
        }
    }


    /**
     * Logger object
     */
    private static Logger log = Logger.getLogger(AgentImpl.class.getName());

    /**
     * Android driver
     */
    private AndroidDriver<MobileElement> driver;

    /**
     * Driver Wait
     */
    private WebDriverWait wait;

    /**
     * Touch interactions objects
     */
    private RemoteTouchScreen touch;
    private MultiTouchAction multiTouch;

    private static final String CAPABILITY_DEVICE_NAME = "deviceName";

    @Override
    public void start(String applicationName) throws AgentException {
        Utils utils;
        String[] data = applicationName.split("-");
        boolean isBrowser = false, thereWasAnError = false;
        String activityCapability = "";
        int retries = 0;
        int maxRetries = 5;
        try {
            utils = new AndroidUtilsImpl();
            utils.setAgent(this);
            utils.killProcess(com.harriague.automate.core.conf.Constants.CHROME_DRIVE_PROCESS_NAME);
        } catch (DeviceException e) {
            throw new AgentException(e.getMessage(), e, this);
        }

        AppiumController appium = new AppiumController(utils);
        while (thereWasAnError) {
            thereWasAnError = false;
            try {
                appium.startServer();
            } catch (Exception e) {
                thereWasAnError = true;
                retries++;
            }
            if (retries > maxRetries) {
                throw new AgentException("Could not start server, max try reach", this);
            }
        }

        try {
            AVDManager.startEmulator(ReadProperty.getProperty(PropertiesKeys.DEVICE_NAME), 1);
        } catch (PropertyException e1) {
            e1.printStackTrace();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
            if (data[0].contains("com.")) {
                activityCapability =
                        ReadProperty.getProperty("application." + applicationName + ".activity");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, data[0]);
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, activityCapability);
            } else {
                capabilities.setCapability(MobileCapabilityType.APP,
                        ReadProperty.getProperty(PropertiesKeys.ANDROID_APK_PATH) + data[0]
                                + ".apk");
            }
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
                    Constants.APPIUM_AUTOMATION_TYPE);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
                    ReadProperty.getProperty(PropertiesKeys.DEVICE_NAME));
            capabilities.setCapability(MobileCapabilityType.PLATFORM,
                    Constants.APPIUM_AUTOMATION_PLATFORM);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                    ReadProperty.getProperty(PropertiesKeys.DEVICE_VERSION));
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
            capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
        } catch (PropertyException e) {
            throw new AgentException(e.getMessage(), e, this);
        }
        try {
            driver = new AndroidDriver<MobileElement>(
                    new URL(com.harriague.automate.core.conf.Constants.PROTOCOL_HTTP
                            + ReadProperty.getProperty(PropertiesKeys.APPIUM_HOST) + ":"
                            + ReadProperty.getProperty(PropertiesKeys.APPIUM_PORT)
                            + Constants.APPIUM_URL),
                    capabilities);
        } catch (Exception e) {
            log.warn("Appium error, retrying");
            appium.killServer();
            appium.startServer();
            try {
                driver = new AndroidDriver<MobileElement>(
                        new URL(com.harriague.automate.core.conf.Constants.PROTOCOL_HTTP
                                + ReadProperty.getProperty(PropertiesKeys.APPIUM_HOST) + ":"
                                + ReadProperty.getProperty(PropertiesKeys.APPIUM_PORT)
                                + Constants.APPIUM_URL),
                        capabilities);
            } catch (Exception e2) {
                throw new AgentException(e.getMessage(), e2, this);
            }
        }

        touch = new RemoteTouchScreen(driver.getExecuteMethod());
        multiTouch = new MultiTouchAction(driver);
        wait = new WebDriverWait(driver, 15);

        if (isBrowser) {
            driver.navigate().to(data[0]);
        }
    }

    @Override
    public void waitForVanish(Object element, FlawedTimeUnit timeout) throws AgentException {
        WebDriverWait auxWait = new WebDriverWait(driver, timeout.toSeconds());
        try {
            auxWait.until(ExpectedConditions.invisibilityOfElementLocated((By) element));
        } catch (TimeoutException e) {
            // TODO: add log message
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.agent.Agent#writeInElement(java.lang.Object,
     * java.lang.String)
     */
    public void writeInElement(Object element, String text) throws AgentException {
        // TODO: add log message
        WebElement webElement;
        try {
            webElement = driver.findElement((By) element);
        }catch(Exception e) {
            throw new AgentException("Could not find "+element, this);
        }
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        webElement.sendKeys(text);
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            log.warn("Soft keyboard not found");
        }
    }

    @Override
    public void takeScreenshot() throws AgentException {
        File srcFiler = getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("MM_dd_HH_mm_ss");
        String folder = com.harriague.automate.core.conf.Constants.SCREENSHOT_FOLDER_PATH;
        String fileName = "agentScreenshot_" + dateFormat.format(new Date().getTime());
        try {
            FileUtils.copyFile(srcFiler, new File(folder + "/" + fileName + ".png"));
        } catch (Exception e) {
        }
    }

    @Override
    public void highlightElement(Object element) throws AgentException {
        // in Android can't be implemented
    }

    @Override
    public boolean checkElementIsDisplayed(Object element) throws AgentException {
        // TODO: add log message
        WebElement aux = null;
        try {
            aux = wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } catch (TimeoutException e) {
        }
        return aux != null;
    }

    @Override
    public boolean checkElementIsDisplayed(Object element, FlawedTimeUnit timeout) {
        // TODO: add log message
        WebElement aux = null;
        WebDriverWait auxWait = new WebDriverWait(driver, timeout.toSeconds());
        try {
            aux = auxWait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } catch (TimeoutException e) {
        }
        return aux != null;
    }

    @Override
    public void pressKey(Object key) throws AgentException {
        // To see available key codes refer to:
        // "http://developer.android.com/reference/android/view/KeyEvent.html"
        // TODO: add log message
        driver.pressKeyCode((int) key);
    }

    protected static ImmutableMap<String, Object> getCommandImmutableMap(String param,
                                                                         Object value) {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        builder.put(param, value);
        return builder.build();
    }

    @Override
    public void longPressKey(Object key) {
        driver.longPressKeyCode((int) key);
    }
    

    public void longPressWithTime(Object by, FlawedTimeUnit duration) throws AgentException, InterruptedException {
        WebElement elem = findElement(by);
        int miliseconds = (int)duration.toMiliseconds();
        new TouchAction(driver).longPress(elem, miliseconds).waitAction(miliseconds).release().perform();
    }

    @Override
    public void click(Object element) throws AgentException {
        // TODO: add log message
        findElement((By) element).click();
    }

    @Override
    public void back() throws AgentException {
        driver.navigate().back();
    }

    @Override
    public void rightClick(Object element) throws AgentException {
        // TODO Auto-generated method stub
    }

    @Override
    public void scroll(ScrollDirection direction, int amount) throws AgentException {
        // TODO: add log message
        if (direction.equals(ScrollDirection.DOWN)) {
            amount *= -1;
        }
        touch.scroll(0, amount);
    }

    /**
     * Get object to start building a custom gesture.
     *
     * @return TouchAction instance
     */
    public TouchAction getCustomGestureBuilder() {
        return new TouchAction(driver);
    }

    /**
     * Builds and performs a customized gesture
     *
     * @param customActions List<TouchAction>
     * @throws AgentException
     */
    public void executeCustomGesture(List<Object> customActions) throws AgentException {
        for (Object action : customActions) {
            try {
                multiTouch.add((TouchAction) action);
            } catch (ClassCastException e) {
                log.info("A '" + TouchAction.class + "' object was expected" + " and '"
                        + action.getClass() + "' was provided");
                throw new AgentException(e.getMessage(), e, this);
            }
        }
        multiTouch.perform();
    }

    @Override
    public void scrollIntoView(Object elem) throws AgentException {
        int numberOfScolls = 0;
        WebElement element;
        final FlawedTimeUnit timeout = FlawedTimeUnit.seconds(3);
        while (numberOfScolls < 20) {
            try {
                element = quickFindElement((By) elem, timeout);
            } catch (Exception e) {
                element = null;
            }
            if (element != null) {
                break;
            }
            this.swipe(SwipeDirection.DOWN);
            numberOfScolls++;
        }
    }


    /**
     * Search an element for the timeout specified
     *
     * @param by to element
     * @param timeout search time out
     * @return WebElement
     * @throws AgentException
     */
    public WebElement quickFindElement(By by, FlawedTimeUnit timeout) throws AgentException {
        driver.manage().timeouts().implicitlyWait(timeout.toSeconds(), TimeUnit.SECONDS);
        WebElement element;
        try {
            element = driver.findElement(by);
        } catch (Exception e) {
            element = null;
        }
        driver.manage().timeouts().implicitlyWait(Constants.DEFAULT_DRIVER_QUICK_SEARCH.toSeconds(),
                TimeUnit.SECONDS);
        return element;
    }


    /**
     * Find a element in page
     *
     * @param by to element
     * @return WebElement
     * @throws AgentException
     */
    public WebElement findElement(Object by) throws AgentException {
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated((By) by));
        } catch (Exception e) {
            log.error("Xpath not found: " + by);
            throw new AgentException(e.getMessage(), this);
        }
        return element;
    }

    /*
     * (non-Javadoc)80
     * 
     * @see com.harriague.automate.core.agent.Agent#close()
     */
    @Override
    public void close() {
        driver.quit();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.agent.Agent#navegateTo(java.lang.String)
     */
    public void navigateTo(String url) throws AgentException {
        if (url.equals("back")) {
            driver.navigate().back();
        }
    }

    @Override
    public String getTextValue(Object element) throws AgentException {
        String aux = null;
        aux = findElement((By) element).getText();
        log.info("Text Value: '" + aux + "'");
        return aux;
    }

    @Override
    public String getValue(Object element, String attribute) throws AgentException {
        String aux = null;
        aux = findElement((By) element).getAttribute(attribute);
        return aux;
    }

    @Override
    public List<Map<String, Object>> getGrid(Object id) throws AgentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void selectInCombobox(Object xpath, Object value) throws AgentException {
        boolean isSelected = false;
        Select select =
                new Select(quickFindElement((By) xpath, Constants.DEFAULT_DRIVER_QUICK_SEARCH));
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().contains((String) value)) {
                select.selectByIndex(i);
                isSelected = true;
                break;
            }
        }
        if (isSelected) {
            log.info("The option '" + ((String) value) + "' is selected.");
        } else {
            throw new AgentException("The option '" + ((String) value) + "' not exist", this);
        }
    }

    @Override
    public void maximizeWindows() throws AgentException {
        // TODO Auto-generated method stub

    }

    @Override
    public Object hover(Object xpath) throws AgentException {
        WebElement we = findElement((By) xpath);
        TouchAction touchAction =  new TouchAction(driver);
        // we.click();
        touchAction.press(we).perform();
        touchAction.press(we).perform();
        return we;
    }

    @Override
    public void waitForVanish(Object element) throws AgentException {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated((By) element));
        } catch (TimeoutException e) {
            // TODO: add log message
            e.printStackTrace();
        }
    }

    public void swipe(SwipeDirection direction) {
        int xstart = 250, ystart = 250;
        int xOffSet = 250, yOffSet = 250;
        log.info("going " + direction);
        switch (direction) {
            case LEFT:
                xstart = 200;
                xOffSet = 950;
                break;
            case UP:
                ystart = 200;
                yOffSet = 600;
                break;
            case DOWN:
                ystart = 600;
                yOffSet = 200;
                break;
            case RIGHT:
                xstart = 950;
                xOffSet = 200;
                break;
            case DIAGONAL_RIGHT_TOP:
                xstart = 350;
                xOffSet = 200;
                ystart = 200;
                yOffSet = 600;
                break;
            case DIAGONAL_RIGHT_BOTTOM:
                xstart = 350;
                xOffSet = 200;
                ystart = 600;
                yOffSet = 200;
                break;
            case DIAGONAL_LEFT_TOP:
                xstart = 200;
                xOffSet = 950;
                ystart = 200;
                yOffSet = 600;
                break;
            case DIAGONAL_LEFT_BOTTOM:
                xstart = 200;
                xOffSet = 950;
                ystart = 600;
                yOffSet = 200;
                break;
            default:
                throw new InvalidParameterException("Direction " + direction.toString() + " s not valid");

        }
        driver.swipe(xstart, ystart, xOffSet, yOffSet, 150);
    }

    @Override
    public void swipe(int startx, int starty, int endx, int endy, FlawedTimeUnit duration) {
        driver.swipe(startx, starty, endx, endy, (int) duration.toMiliseconds());
    }

    public void zoomOut(int x, int y) {
        driver.zoom(x, y);
    }

    public void zoomIn(int x, int y) {
        driver.pinch(x, y);
    }

    @Override
    public List<MobileElement> findElements(Object by) {
        return driver.findElements((By) by);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return driver.getScreenshotAs(outputType);
    }

    public void leavePressTouch(int x, int y) {
        new TouchAction(driver).press(x, y).perform();
    }

    /**
     * Convenience method that takes a screenshot of the device and returns a BufferedImage for
     * further processing.
     *
     * @return screenshot from the device as BufferedImage
     */
    public BufferedImage takeScreenshotAsBuffer() {
        log.info("taking screenShot");
        File scrFile = getScreenshotAs(OutputType.FILE);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(scrFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    /**
     * Gets the window height
     */
    public int getHeight() {
        return driver.manage().window().getSize().getHeight();
    }

    /**
     * Gets the window width
     */
    public int getWidth() {
        return driver.manage().window().getSize().getWidth();
    }

    /*
     * Convenience method for tapping a position on the screen
     * 
     * @param fingers number of finger to perform this action
     * 
     * @param positionX the location in X where tap
     * 
     * @param positionY the location in Y where tap
     * 
     * @param duration time in mills for the duration of the tap
     *
     */
    @Override
    public void clickInTheScreen(int fingers, int positionX, int positionY, FlawedTimeUnit duration) {
        driver.tap(fingers, positionX, positionY, (int) duration.toMiliseconds());
    }

    /**
     * This method starts an activity
     * @param appPackage
     * @param appActivity
     * @param appWaitPackage
     * @param appWaitActivity
     * @param stopApp
     */
    public void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity, boolean stopApp) {
        this.driver.startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity, stopApp);
    }

    /**
     * This method starts an activity
     * @param appPackage
     * @param appActivity
     * @param appWaitPackage
     * @param appWaitActivity
     */
    public void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity) {
        this.driver.startActivity(appPackage, appActivity, appWaitPackage, appWaitActivity);
    }

    /**
     * This method resets the current app.
     */
    public void resetApp() {
        this.driver.resetApp();
    }

    /**
     * This method gets the current activity
     * @return
     */
    public String getCurrentActivity(){
        return this.driver.currentActivity();
    }

    /**
     * This method returns true or false either an app is installed or not
     * @param bundleId -  bundleId of the app
     * @return
     */
    public boolean isAppInstalled(String bundleId) {
        return this.driver.isAppInstalled(bundleId);
    }

    /**
     * This method installs an android application
     * @param bundleId -  bundleId of the app
     * @param apk
     */
    public void installApp(String apk, String bundleId){
        if(this.driver.isAppInstalled(bundleId)){
            this.driver.removeApp(bundleId);
        }
        this.driver.installApp(AndroidUtilsImpl.apkFolder + apk);
    }

    /**
     * This method removes an app
     * @param bundleId - the bundle identifier (or app id) of the app to remove
     */
    public void removeApp(String bundleId) {
        this.driver.removeApp(bundleId);
    }

    /**
     * This method returns the device Name or IP
     * @return String Device Name or IP
     */
    public String getDeviceNameOrIP() {
        return this.driver.getCapabilities().getCapability(CAPABILITY_DEVICE_NAME).toString();
    }

    @Override
    public Process sendCommand(String command) throws AgentException {
        Process p;
        try {
            p = Runtime.getRuntime().exec(ADB_COMMAND + getDeviceNameOrIP()+" "+ command);
        }catch(Exception e) {
            throw new AgentException(e, this);
        }
        return p;
    }

    @Override
    public Process waitForCommand(String command) throws AgentException {
        Process p = sendCommand(command);
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            throw new AgentException(e, this);
        }
        return p;
    }

    public void longClick(Object by) throws AgentException {
        WebElement elem = findElement(by);
        new TouchAction(driver).longPress(elem).release().perform();
    }

    /**
     * This method retrieves the logcat output of an specific device
     * @return String - The logcat output
     */
    public String getLogcatOutput() {
        return getOutput(ADB_LOGCAT);
    }

    /**
     * This method retrieves the logcat output of an specific device
     * @param outputFormat - The output format specification
     * @param logLevel - The log level wanted eg: "INFO, DEBUG, etc"
     * @param tag - The specific log tag wanted
     * @return String - The logcat output
     */
    public String getLogcatOutput(OutputFormat outputFormat, LogLevel logLevel, Tag tag) {
        return getOutput(ADB_LOGCAT + " " + tag + ":" + logLevel);
    }


    /**
     * This method retrieves the logcat output of an specific device
     * @param outputFormat - The output format specification
     * @param logLevel - The log level wanted eg: "INFO, DEBUG, etc"
     * @param tag - The specific log tag wanted
     * @param wordFilter - A word or a String that the log line should contain use as filter
     * @return String - The logcat output
     */
    public String getLogcatOutput(OutputFormat outputFormat, LogLevel logLevel, Tag tag, String wordFilter) {
        return getOutput(ADB_LOGCAT + " " + tag + ":" + logLevel + " " + FILTER_OUTPUT + " " + wordFilter);
    }

    /**
     * This method flushes and clear the log cat, very useful when a clean log of specific
     * action is need, this method should be called before the important actions get executed
     */
    public void flushLogcat() {
        getOutput(ADB_LOGCAT_FLUSH);
    }

    /**
     * This method will be useful to reuse the code when we perform logcat console actions
     * @param command - the command to be executed
     * @return String - the String obtained as result of executing the command
     */
    private String getOutput(String command) {
        Process p = null;
        StringBuffer logCatOutput = new StringBuffer("");
        log.info("Executing the command " + command);
        try {
            p = sendCommand(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                logCatOutput.append(line);
            }
            return logCatOutput.toString();
        } catch (IOException e) {
            log.error("IOException executing command " + command);
            Assert.fail("IOException executing command " + command);
            return logCatOutput.toString();
        } catch (AgentException ae) {
            log.error("Agent exception executing command " + command);
            Assert.fail("Agent exception executing command " + command);
            return logCatOutput.toString();
        }
    }
}
