package com.harriague.automate.module.web.agent;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.conf.PropertiesKeys;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.structures.FlawedTimeUnit;
import com.harriague.automate.core.structures.ScrollDirection;
import com.harriague.automate.core.structures.SwipeDirection;
import com.harriague.automate.core.utils.ReadProperty;
import org.testng.Assert;

public class CommonAgentImpl implements Agent {


    /**
     * Logger object
     */
    private static Logger log = Logger.getLogger(CommonAgentImpl.class.getName());

    /**
     * Browser Driver
     */
    private WebDriver driver;

    /**
     * is quick silver
     */
    private boolean searchingQuick = false;

    private WebElement lastElementUsed;

    private final FlawedTimeUnit DEFAULT_DRIVER_QUICK_SEARCH;

    private final String ATTRIBUTE_VALUE;

    private final String BROWSER_FIREFOX_NAME;

    private final String BROWSER_CHROME_NAME;

    private final String BROWSER_DEFAULT_NAME;

    private final String BROWSER_OPERA_NAME;

    private final String RESOURCES_FOLDER;

    private final String PATH_WEB_DRIVERS_FOLDER;

    private final String DRIVER_CHROME_FILE_NAME;

    private final String PROPERTY_CHROMEDRIVER;

    private final String DRIVER_OPERA_FILE_NAME;

    private final String DRIVER_DEFAULT_BROWSER_FILE_NAME;

    private final String PROPERTY_DEFAULT_BROWSER_DRIVER;

    private final String DRIVER_FIREFOX_FILE_NAME;

    private final String PROPERTY_FIREFOX_DRIVER;

    protected CommonAgentImpl(FlawedTimeUnit default_driver_quick_search, String attribute_value,
                              String fire_fox_name, String chrome_name, String defaul_browser_name, String opera_name,
                              String resource_folder, String path_web_driver_folder, String driver_chrome_file_name,
                              String property_chromeDriver, String driver_opera_file_name,
                              String driver_default_browser_file_name, String property_default, String driver_firefox_file_name, String property_firefox_driver) {
        DEFAULT_DRIVER_QUICK_SEARCH = default_driver_quick_search;
        ATTRIBUTE_VALUE = attribute_value;
        BROWSER_FIREFOX_NAME = fire_fox_name;
        BROWSER_CHROME_NAME = chrome_name;
        BROWSER_DEFAULT_NAME = defaul_browser_name;
        BROWSER_OPERA_NAME = opera_name;
        RESOURCES_FOLDER = resource_folder;
        PATH_WEB_DRIVERS_FOLDER = path_web_driver_folder;
        DRIVER_CHROME_FILE_NAME = driver_chrome_file_name;
        DRIVER_FIREFOX_FILE_NAME = driver_firefox_file_name;
        PROPERTY_CHROMEDRIVER = property_chromeDriver;
        DRIVER_OPERA_FILE_NAME = driver_opera_file_name;
        DRIVER_DEFAULT_BROWSER_FILE_NAME = driver_default_browser_file_name;
        PROPERTY_DEFAULT_BROWSER_DRIVER = property_default;
        PROPERTY_FIREFOX_DRIVER = property_firefox_driver;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.agent.Agent#start(java.lang.String)
     */
    public void start(String applicationName) throws AgentException {
        String[] parameters = applicationName.split("-");
        if (parameters.length > 1) {
            openBrowser(parameters[1]);
        } else {
            openDefaultBrowser();
        }
        try {
            driver.navigate().to(parameters[0]);
        } catch (TimeoutException e) {
            driver.close();
            throw new AgentException("\nTHE BROWSER " + parameters[1] + " WAS BLOCKED:\n", e,this);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.agent.Agent#waitForVanish(java.lang.Object)
     */
    public void waitForVanish(Object element) throws AgentException {
        vanish(element, DEFAULT_DRIVER_QUICK_SEARCH);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.agent.Agent#waitForVanish(java.lang.Object, int)
     */
    public void waitForVanish(Object element, FlawedTimeUnit timeout) throws AgentException {
        vanish(element, timeout);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.harriague.automate.core.agent.Agent#writeInElement(java.lang.Object,
     * java.lang.String)
     */
    public void writeInElement(Object element, String text) throws AgentException {

        WebElement webElement = quickFindElement((By) element, DEFAULT_DRIVER_QUICK_SEARCH);
        // webElement.click();
        webElement.sendKeys(text);
        if (!webElement.getAttribute(ATTRIBUTE_VALUE).equals(text)) {
            webElement.clear();
            webElement.sendKeys(text);
        }
    }


    @Override
    public void selectDateFromDatePicker(Object anElement, Date aDate) throws AgentException{

        click(anElement);

        String day = new SimpleDateFormat("d").format(aDate);
        String year =  new SimpleDateFormat("yyyy").format(aDate);
        String month = new SimpleDateFormat("M").format(aDate);
        int  i_month = Integer.parseInt(month);
        i_month -= 1;
        month = Integer.toString(i_month);

        By next = By.className("ui-datepicker-next");
        By prev = By.className("ui-datepicker-prev");

        String selector_dateInDatePickerMonth = "//a[@class='ui-state-default' and text()='" + day + "']/parent::*[@data-month='"+ month +"']";
        String selector_dateInDatePickerYear = "//a[@class='ui-state-default' and text()='" + day + "']/parent::*[@data-year='"+ year +"']";
        String selector_dateInDatePickerYearAndMonth = "//a[@class='ui-state-default' and text()='" + day + "']/parent::*[@data-year='"+ year + "' and @data-month='"+ month +"']";

        By dateInDatePicker = By.xpath("//a[@class='ui-state-default' and text()='" + day + "']");
        By dateInDatePickerMonth = By.xpath(selector_dateInDatePickerMonth);
        By dateInDatePickerYear = By.xpath(selector_dateInDatePickerYear);
        By dateInDatePickerYearAndMonth = By.xpath(selector_dateInDatePickerYearAndMonth);
        while (!(checkElementIsDisplayed(dateInDatePickerYearAndMonth))){
            while(!(checkElementIsDisplayed(dateInDatePickerYear))) {
                By dateInDatePickerYearBigger = By.xpath("//a[@class='ui-state-default' and text()='" + day + "']/parent::*[@data-year > '"+ year  +"']");
                By dateInDatePickerYearLittle = By.xpath("//a[@class='ui-state-default' and text()='" + day + "']/parent::*[@data-year < '"+ year  +"']");
                if (checkElementIsDisplayed(dateInDatePickerYearBigger)) {
                    click(prev);
                }
                else if (checkElementIsDisplayed(dateInDatePickerYearLittle)){
                    click(next);
                }
            }
            while(!(checkElementIsDisplayed(dateInDatePickerMonth))) {
                By dateInDatePickerMonthBigger = By.xpath("//a[@class='ui-state-default' and text()='" + day + "']/parent::*[@data-month > '"+ month  +"']");
                By dateInDatePickerMonthLittle = By.xpath("//a[@class='ui-state-default' and text()='" + day + "']/parent::*[@data-month < '"+ month  +"']");
                if (checkElementIsDisplayed(dateInDatePickerMonthBigger)) {
                    click(prev);
                }
                else if (checkElementIsDisplayed(dateInDatePickerMonthLittle)){
                    click(next);
                }
            }
        }
        click(dateInDatePicker);
    }

    @Override
    public void selectSelectorOption(String selector, String option) throws AgentException{
        Select dropdown = new Select(driver.findElement(By.cssSelector(selector)));
        dropdown.selectByVisibleText(option);
    }

    @Override
    public void selectSelectorOption(Object element, String option) throws AgentException{
        Select dropdown = new Select(driver.findElement((By)element));
        dropdown.selectByVisibleText(option);
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
        WebElement x = (WebElement) findElement((By) element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", x);
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            //wait.until(ExpectedConditions.invisibilityOfElementLocated((By) element));
        } catch (TimeoutException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0'", x);
        }
    }

    @Override
    public boolean checkElementIsDisplayed(Object element) throws AgentException {
        return quickFindElement((By) element, DEFAULT_DRIVER_QUICK_SEARCH) != null;
    }

    @Override
    public boolean checkElementIsDisplayed(Object element, FlawedTimeUnit timeout) {
        try {
            return quickFindElement((By) element, timeout) != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void pressKey(Object key) throws AgentException {
        log.info(lastElementUsed);
        /*
         * Actions builder = new Actions(driver); try{ builder.keyDown((Keys) key).perform();
         * }catch(Exception e){
         * 
         * // lastElementUsed.sendKeys((CharSequence[]) key); }
         */
    }

    @Override
    public void click(Object element) throws AgentException {
        WebElement el = quickFindElement((By) element, DEFAULT_DRIVER_QUICK_SEARCH);
        if (el == null)
            throw new AgentException("Element \"" + element + "\" was not found", this);
        else
            el.click();
    }

    @Override
    public void rightClick(Object by) throws AgentException {
        WebElement context = find((By) by);
        Actions action = new Actions(driver);
        action.contextClick(context).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN).build().perform();
    }

    @Deprecated
    public void doubleClick(Object element) throws AgentException {
        WebElement el = quickFindElement((By) element, DEFAULT_DRIVER_QUICK_SEARCH);
        if (el == null) {
            throw new AgentException("Element \"" + element + "\" was not found", this);
        }
        else {
        	Actions action = new Actions(driver);
            action.doubleClick(el).build().perform();
        }
    }

    @Override
    public void doubleClick(WebElement opcion) throws AgentException {
        Actions action = new Actions(driver);
        action.doubleClick(opcion).perform();
        log.info("Double Click in option");
    }

    @Override
    public void ctrlClick(WebElement opcion) throws AgentException {
        //TODO: MAKE COMPATIBILITY WITH IE
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).click(opcion).keyUp(Keys.CONTROL).perform();
        log.info("Ctrl + Click: "+ opcion.getText());
    }



    @Override
    public void markOptionSelectorMultiple(String optionToSelect, Object selectorMultipleLeftOptionBox) throws AgentException {
        ArrayList<WebElement> options = (ArrayList<WebElement>) driver.findElements((By) selectorMultipleLeftOptionBox);
        log.info("Busca las opciones en el control");
        WebElement optionToSelectObject = findOptionInBox(optionToSelect, options);
        if (optionToSelectObject != null){
            ctrlClick(optionToSelectObject);
        }
    }

    @Override
    public void selectOneOption(String optionToSelect,Object selectorMultipleOptionBox) throws AgentException {
        ArrayList<WebElement> options = (ArrayList<WebElement>) driver.findElements((By) selectorMultipleOptionBox);
        log.info("Busca las opciones en el control");
        WebElement optionToSelectObject = findOptionInBox(optionToSelect, options);
        if (optionToSelectObject != null){
            optionToSelectObject.click();
        }
    }

    private WebElement findOptionInBox(String optionToSelect, ArrayList<WebElement> options) {
        for (WebElement option_aux : options){
            String aux = option_aux.getText();
            if (aux.equals(optionToSelect)){
                log.info("Encontro la opcion: "+ aux);
                return option_aux;
            } else  log.info("No encontro la opcion: " + aux);
        }
        return null;
    }

    public void selectOptionsDoubleClick(String option, Object element) throws AgentException{
        ArrayList<WebElement> options = (ArrayList<WebElement>) driver.findElements((By)element);
        log.info("Busca las opciones en el control");
        for (WebElement option_aux : options){
            String aux = option_aux.getText();
            if (aux.equals(option)){
                log.info("Encontro la opcion: "+ option);
                doubleClick(option_aux);
                break;
            }
        }
        log.info("No encontro la opcion: " + option);
    }


    @Override
    public String switchToPopup() throws AgentException{
        String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window
        log.info("Cambio a popup");
        // Now you are in the popup window, perform necessary actions here
        return parentWindowHandler;
    }


    @Override
    public void switchToPopup(String parentWindowHandler) throws AgentException{
        driver.switchTo().window(parentWindowHandler);
        log.info("Cambio al control padre");
    }
    
    @Override
    public void scroll(ScrollDirection direction, int amount) throws AgentException {
        if (direction.equals(ScrollDirection.UP))
            amount *= -1;
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + amount + ")", "");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
    }


    @Override
    public boolean alertIspresent() throws AgentException{
    	
    	boolean foundAlert = false;
        WebDriverWait wait = new WebDriverWait(driver, 4 /*timeout in seconds*/);
        try {
            //wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException eTO) {
            foundAlert = false;
        }
        return foundAlert;
        
    }
    
    @Override
    public void aceptAlert() throws AgentException {
    	driver.switchTo().alert().accept();
    }


    @Override
    public void cancelAlert() throws AgentException {
    	
    	driver.switchTo().alert().dismiss();
    }
 
    
    /**
     * Get default browser name
     * 
     * @throws AgentException
     */
    private void openDefaultBrowser() throws AgentException {
        try {
            openBrowser(
                    ReadProperty.getProperty(PropertiesKeys.DEFAULT_BROWSER, BROWSER_FIREFOX_NAME));
        } catch (PropertyException e) {
            throw new AgentException(e, this);
        }

    }

    /**
     * Open a browser by name
     * 
     * @param browserName browser name
     */
    private void openBrowser(String browserName) throws AgentException {
        final String BROWSER_NAME = browserName.toUpperCase();
        if (BROWSER_NAME.equals(BROWSER_CHROME_NAME)) {
            log.info("Open Chrome browser.");
            startChrome();
        } else if (BROWSER_NAME.equals(BROWSER_FIREFOX_NAME)) {
            log.info("Open FireFox browser.");
            startFireFox();
        } else if (BROWSER_NAME.equals(BROWSER_DEFAULT_NAME)) {
            log.info("Open Internet Explorer browser.");
            startInternetExplorer();
        } else if (BROWSER_NAME.equals(BROWSER_OPERA_NAME)) {
            log.info("Open Opera browser.");
            startOpera();
        } else {
            log.info("The browser '" + browserName + "' is not supported.");
        }
    }

    /**
     * Start Chrome browser
     */
    public String getWebFolder(){
        String webFolder = null;
        StringBuilder strB = new StringBuilder();
        try {
            strB.append(ReadProperty.getProperty("core-web_path"));
            strB.append(File.separator);
            strB.append("resources");
            strB.append(File.separator);
            webFolder = strB.toString();
        } catch (PropertyException e) {
            webFolder = RESOURCES_FOLDER;
        }
        return webFolder;
    }

    public void startChrome() {
        String webFolder = getWebFolder();
        //File srcFile =
        //        new File(webFolder + PATH_WEB_DRIVERS_FOLDER + DRIVER_CHROME_FILE_NAME);
        String srcFile =
                new File(webFolder + PATH_WEB_DRIVERS_FOLDER + DRIVER_CHROME_FILE_NAME).getAbsolutePath();
        System.setProperty(PROPERTY_CHROMEDRIVER, srcFile);
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.addArguments("maximized");
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
    }

    public void startInternetExplorer() {
        String webFolder = getWebFolder();
        File srcFile = new File(
                webFolder + PATH_WEB_DRIVERS_FOLDER + DRIVER_DEFAULT_BROWSER_FILE_NAME);
        System.setProperty(PROPERTY_DEFAULT_BROWSER_DRIVER, srcFile.getAbsolutePath());

        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
        caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        //caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        // HKLM_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\Main path should contain key
        // TabProcGrowth with 0 value.
        InternetExplorerOptions options = new InternetExplorerOptions((Capabilities) caps);
        driver = new InternetExplorerDriver(caps);
        driver.manage().window().maximize();
    }

    /**
     * Rodrigo Crespillo
     * 14/07/2017
     * Se debe usar Firefox ver.49 las versiones mas actules de Firefox no son compatibles con xpath en el es usado
     * en algunas librerias
     *
     */
    public void startFireFox() {
        String webFolder = getWebFolder();
        String srcFile =
                new File(webFolder + PATH_WEB_DRIVERS_FOLDER + DRIVER_FIREFOX_FILE_NAME).getAbsolutePath();
        System.setProperty(PROPERTY_FIREFOX_DRIVER, srcFile);

        //DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        //capabilities.setCapability("marionette", true);

        driver = new FirefoxDriver();
        //driver.manage().window().maximize();
    }

    public void startOpera() {
        String webFolder = getWebFolder();
        File srcFile =
                new File(webFolder + PATH_WEB_DRIVERS_FOLDER + DRIVER_OPERA_FILE_NAME);
        System.setProperty(PROPERTY_CHROMEDRIVER, srcFile.getAbsolutePath());
        driver = new ChromeDriver();
    }

    private WebElement findInAllIframe(By by) {
        WebElement element = null;
        /*
        Set<String> windows = driver.getWindowHandles();
        for (int i = (windows.size() - 1); i >= 0; i++) {
            driver.switchTo().window((String) windows.toArray()[i]);
            driver.switchTo().defaultContent();
            element = find(by);
            if (element != null) {
                break;
            }
        }
        */
        Object e = findG(by, null, false);
        if (e != null)
        	element = (WebElement) e;
        
        
        return element;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<WebElement> findAllInAllIframe(By by) {
        ArrayList<WebElement> element = null;
       
        /*Set<String> windows = driver.getWindowHandles();
        for (int i = (windows.size() - 1); i >= 0; i++) {
        	log.info("driver: " + String.valueOf(i) + " - " + (String) windows.toArray()[i] );
        	
            driver.switchTo().window((String) windows.toArray()[i]);
            driver.switchTo().defaultContent();
            element = (ArrayList<WebElement>) find(by, null, true);
             if (element != null) {
            	if (element.size() > 0)
            	log.info("sale por el break");
                break;
            }
        }
        
        return element;
        */
        //element = (ArrayList<WebElement>) find(by, null, true);
        element = (ArrayList<WebElement>) findAllG(by, null);
        
        return element;
    }

    private WebElement find(By by) {
        //return (WebElement) find(by, null, false);
    	return (WebElement) findG(by, null, false);
    }

    private Object findG(By mby, WebElement iframe, boolean isIframe ) {
    	//function to find element, if have iframe or frame, call findInIframe
    	Object element = null;
    	if(isIframe) {
    		driver.switchTo().defaultContent();
    	}
    	
    	boolean tipo = (iframe == null);
    	
    	if (tipo) {
    		driver.switchTo().defaultContent();
    	}
    	else {	
    		//if(isIframe)
    		driver.switchTo().frame(iframe);
    	}
    	
    	try {
    		element = driver.findElement(mby);	
    		
    	}
    	catch (Exception exception) {
        	//element = null;
        	
        }
    	
    	if (element != null) {
    		return element;
    	}
    	else {
    		
    	}
    			    	
		List<WebElement> iframes = new ArrayList<WebElement>();
		List<WebElement> frames = new ArrayList<WebElement>();
		iframes.addAll(driver.findElements(By.tagName("iframe")));
		frames.addAll(driver.findElements(By.tagName("frame")));
		
		//for (final WebElement frm : iframes) {
		for (int j=0;j<iframes.size(); j++){
			WebElement frm = iframes.get(j);
			
			element = findG(mby, frm, true);
			
			if (element != null) {
				 break;
			}
			else {
				driver.switchTo().defaultContent();
			}		
			
		}
		
		//for (final WebElement frm : iframes) {
		for (int j=0;j<frames.size(); j++){
			WebElement frm = frames.get(j);
			
			element = findG(mby, frm, false);
			
			if (element != null) {
			 
				//log.info("encontró iframe: "+ frm.getAttribute("name") );
				 break;
			}
			else {
				driver.switchTo().defaultContent();
				 
			}						
		}
		 
		return element;
    	 
    }
    
    private List<WebElement> findAllG(By by, WebElement iframe) {
    	//function to find element, if have iframe or frame, call findInIframe
    	List<WebElement> element = new ArrayList<WebElement>();
    	
    	if (iframe == null) {
    		driver.switchTo().defaultContent();
    	}
    	else {
    		driver.switchTo().frame(iframe);
    	}
    	
    	try {
    		element = driver.findElements(by);	
    	}
    	catch (Exception exception) {
        	//element = null;
        }
    	
    	List<WebElement> iframes = new ArrayList<WebElement>();
		List<WebElement> frames = new ArrayList<WebElement>();
		iframes.addAll(driver.findElements(By.tagName("iframe")));
		frames.addAll(driver.findElements(By.tagName("frame")));
		
		for (WebElement frm : iframes) {
			element.addAll(findAllG(by, frm));
		}
		
		for (WebElement frm : frames) {
			element.addAll( findAllG(by, frm));
		}
		return element;    	 
    }
    
   
/*
    private Object find(By by, List<WebElement> iframe, boolean all) {
        Object element = null;
        if (iframe == null) {
        	//log.info("sin iframe declarado");
            driver.switchTo().defaultContent();
            try {
                if (all) {
                	//log.info("busca en all");
                    element = driver.findElements(by);
                    if (((List <WebElement>)element).size() == 0 )
                    	element = null;
                }
                else {
                	//log.info("busca en else");
                    element = driver.findElement(by);
                }
            } catch (Exception exception) {
            	//log.info("sale por excepcion");
            }
            
            if (element == null) {
            	//log.info("elemento es null");
                List<WebElement> frameRoot = new ArrayList<WebElement>();
                List<WebElement> iframes = new ArrayList<WebElement>();
                iframes.addAll(driver.findElements(By.tagName("iframe")));
                iframes.addAll(driver.findElements(By.tagName("frame")));
                
                if (iframes != null && !iframes.isEmpty()) {
                	int i = 0;
                    for (WebElement iframeId : iframes) {
                    	//log.info("en el for de iframes: " + String.valueOf(i)  + ", Buscando: " + by.toString() );
                    	i+=1;
                        frameRoot.add(iframeId);
                        element = find(by, frameRoot, all);
                        frameRoot.remove(iframeId);
                        if (element != null) {
                        	if (element instanceof List <?> ) {
                        		if (((List <WebElement>)element).size() > 0 ) {
                        		break;
                        		}
                        	}
                        	else {
                        		break;
                        	}
                        }
                    }
                }
            }
        } else {
            driver.switchTo().defaultContent();
            for (WebElement i : iframe) {
            	log.info("estoy en el frame: " + i.getAttribute("name"));
                driver.switchTo().frame(i);
            }

            try {
                if (all) {
                    element = driver.findElements(by);
                    log.info("busco el dato" + by.toString());
                    if (element != null) {
	                    if (((List <WebElement>)element).size() == 0 ) {
	                    	element = null;
	                    }
	                    else {
	                    	log.info("encontró el dato" + by.toString());
	                        
	                    }
                    }                    	
                }
                else {
                    element = driver.findElement(by);
                }
            } catch (Exception exception) {
            	//log.error("catch cuando busco elemento");
            }            
            
            if (element == null) {
            	//log.info("el elemento es nulo");
                List<WebElement> iframes = new ArrayList<WebElement>();
                iframes.addAll(driver.findElements(By.tagName("iframe")));
                iframes.addAll(driver.findElements(By.tagName("frame")));
                if (iframes != null && !iframes.isEmpty()) {
                    for (WebElement iframeId : iframes) {
                        iframe.add(iframeId);
                        element = find(by, iframe, all);
                        iframe.remove(iframeId);
                        if (element != null) {
                        	if (element instanceof List <?> ) {
                        		if (((List <WebElement>)element).size() > 0 ) {
                        			break;
                        		}
                        		else {
                        			element = null;
                        		}
                        	}
                        	else {
                        		break;
                        	}
                        }
                    }
                }            
            }
        }
        if (!all && element != null)
            lastElementUsed = (WebElement) element;
        
        if (element == null){
        	if (iframe != null){
        		log.info("elemento es nulo al final / iframe: " + iframe.get(0).getAttribute("name"));
        	}
        	else {
        		log.info("elemento es nulo al final sin iframe");
        	}
        }
        
        return element;
    }

*/

    // private WebElement find2(By by) {
    // List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
    // List<WebElement> iframesQueue = new ArrayList<WebElement>();
    // WebElement element = null;
    // if( !iframes.isEmpty() ) {
    // for (WebElement iframe : iframes) {
    // driver.switchTo().defaultContent();
    // WebDriverWait wait = new WebDriverWait(driver,10);
    // try {
    // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe.getAttribute("id")));
    // } catch (TimeoutException e1) {
    // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe.getAttribute("name")));
    // }
    // try {
    //// element = wait.until(ExpectedConditions.elementToBeClickable(by));
    // element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    // } catch (Exception e) {
    // iframesQueue.add(iframe);
    // element = nestedIframeSearch(by, iframesQueue);
    // }
    // if(element != null) {
    // return element;
    // }
    // }
    // }
    // if(element == null) {
    // driver.switchTo().defaultContent();
    //// element = driver.findElement(by);
    // WebDriverWait wait = new WebDriverWait(driver,10);
    // element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    // }
    // return element;
    // }
    //
    // private WebElement nestedIframeSearch(By by, List<WebElement> parents){
    // List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
    // iframes.addAll(driver.findElements(By.tagName("frame")));
    // WebElement element = null;
    // if( !iframes.isEmpty() ) {
    // for (WebElement iframe : iframes) {
    // driver.switchTo().defaultContent();
    // for (WebElement auxiliar : parents) {
    // WebDriverWait wait = new WebDriverWait(driver,10);
    // try {
    // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(auxiliar.getAttribute("id")));
    // } catch (TimeoutException e) {
    // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe.getAttribute("name")));
    // }
    // }
    // WebDriverWait wait = new WebDriverWait(driver,10);
    // try {
    // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe.getAttribute("id")));
    // } catch (TimeoutException e1) {
    // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe.getAttribute("name")));
    // }
    // try {
    //// element = wait.until(ExpectedConditions.elementToBeClickable(by));
    // element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    // } catch (Exception e) {
    // parents.add(iframe);
    // element = nestedIframeSearch(by, parents);
    // }
    // if(element != null) {
    // return element;
    // }
    // }
    // }
    // //if iframe is empty, we the remove last parent for next iteration
    // parents.remove((parents.size()-1));
    // return null;
    // }

    /**
     * Search an element for the timeout specified
     * 
     * @param by to element
     * @param timeout search time out
     * @return WebElement
     * @throws AgentException
     */
    public WebElement quickFindElement(By by, FlawedTimeUnit timeout) throws AgentException {
        searchingQuick = true;
        WebElement element = null;
        FlawedTimeUnit clock = new FlawedTimeUnit();
        final FlawedTimeUnit timeBetweenSearches = FlawedTimeUnit.seconds(1);
        while(clock.isLesserThan(timeout)) {
            clock.waitFor(timeBetweenSearches);
            element = (WebElement) findElement(by);
            if (element != null) {
                lastElementUsed = element;
                break;
            }
        }
        searchingQuick = false;
        return element;
    }

    /**
     * Wait a vanish an element
     * 
     * @param element to vanish
     * @param timeOut
     * @throws AgentException
     */
    private void vanish(Object element, FlawedTimeUnit timeOut) throws AgentException {
        By by = (By) element;
        boolean vanished = false;
        int attempts = 10;
        int currentAttempt = 0;

        while (!vanished && currentAttempt < attempts) {
            vanished = (quickFindElement(by, timeOut) == null);
            currentAttempt++;
        }
        //log.info("Element vanished: " + vanished);
    }

    /*
     * (non-Javadoc)
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
        try {
            driver.navigate().to(url);
        } catch (TimeoutException e) {
            driver.close();
            throw new AgentException("\nTHE BROWSER WAS BLOCKED:\n", e, this);
        }
    }

    @Override
    public String getTextValue(Object element) throws AgentException {
        return find((By) element).getText();
    }

    @Override
    public String getValue(Object element, String attribute) throws AgentException {
        return ((WebElement) element).getAttribute(attribute);
    }

    @Override
    public String getValue(By element, String attribute) throws AgentException {
        return find((By) element).getAttribute(attribute);
    }

    @Override
    public List<Map<String, Object>> getGrid(Object id) throws AgentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void selectInCombobox(Object xpath, Object value) throws AgentException {
        boolean isSelected = false;
        Select select = new Select(quickFindElement((By) xpath, DEFAULT_DRIVER_QUICK_SEARCH));
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
        ((JavascriptExecutor) driver)
                .executeScript("window.moveTo(0,0); window.resizeTo(screen.width, screen.height);");
    }

    @Override
    public Object hover(Object xpath) throws AgentException {
        Actions action = new Actions(driver);
        WebElement we = findInAllIframe((By) xpath);
        action.moveToElement(we).perform();
        return null;
    }

    @Override
    public void scrollIntoView(Object element) throws AgentException {
        WebElement webElement = find((By) element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                webElement);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object findElement(Object by) throws AgentException {
        By element = (By) by;
        WebElement res = null;
        /*try {
            res = findInAllIframe(element);
        } catch (Exception e) {
            if (searchingQuick) {
                log.info("Element not displayed:  " + by.toString());
            } else {
                log.info(driver.getPageSource());
                throw e;
            }
        }
        return res;*/
        res = findInAllIframe(element);
        return res;
    }

    @Override
    public void back() throws AgentException {
        driver.navigate().back();
    }

    @Override
    public Object findElements(Object by) {
        return findAllInAllIframe((By) by);
    }

    @Override
    public void longPressKey(Object key) {
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys((CharSequence[]) key).perform();
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return ((TakesScreenshot) driver).getScreenshotAs(outputType);
    }

    @Override
    public int getHeight() {
        return Integer.parseInt(((JavascriptExecutor) driver).executeScript("Math.max(document.documentElement.clientHeight, window.innerHeight || 0)").toString());
    }

    @Override
    public int getWidth() {
        return Integer.parseInt(((JavascriptExecutor) driver).executeScript("Math.max(document.documentElement.clientWidth, window.innerWidth || 0)").toString());
    }

    @Override
    public BufferedImage takeScreenshotAsBuffer() throws AgentException{
        log.info("taking screenShot");
        File scrFile = getScreenshotAs(OutputType.FILE);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(scrFile);
        } catch (IOException e) {
            throw new AgentException(e, this);
        }
        return bufferedImage;
    }

    @Override
    public void clickInTheScreen(int fingers, int positionX, int positionY, FlawedTimeUnit duration) throws AgentException{
        // TODO find a way of doing this
        throw new AgentException("doing this is bad practice", this);
    }

    /**
     * WebAgent Methods
     */

    /**
     * waits until condition is met or the driver times out
     * @param condition the condition that must be met
     */
    public void waitUntil(ExpectedCondition<WebElement> condition) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        //wait.until(condition);
    }

    @Override
    public Process sendCommand(String command) throws AgentException {
        try {
            return Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new AgentException(e,this);
        }
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

    @Override
    public void swipe(SwipeDirection direction) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void swipe(int startx, int starty, int endx, int endy, FlawedTimeUnit duration) {
        // TODO Auto-generated method stub
        
    }

    /**
     * Agrega un archivo a un attachemnt
     *
     * @author Rodrigo Crespillo
     * @version 1.0 12/07/2017
     *
     * @param fileButtom
     * @param filePath */
    @Override
    public void selectFile(By fileButtom, String filePath) {
        WebElement file_buttom = driver.findElement(fileButtom);
        file_buttom.sendKeys(filePath);
    }

    @Override
    public void selectTextFile(String filePath) throws IOException {
        ((JavascriptExecutor) driver).executeScript(returnScriptForDropzone(filePath, "text / txt"));
    }


    public String returnScriptForDropzone(String filePath, String fileType) throws IOException {
        String dropzoneSript = "var myZone, blob, base64Image; myZone = Dropzone.forElement('#my-dropzone');" +
                "base64Image = '" + convertFileToBase64String(filePath) + "';" +
                "function base64toBlob(r,e,n){e=e||\"\",n=n||512;for(var t=atob(r),a=[],o=0;o<t.length;o+=n){for(var l=t.slice(o,o+n),h=new Array(l.length),b=0;b<l.length;b++)h[b]=l.charCodeAt(b);var v=new Uint8Array(h);a.push(v)}var c=new Blob(a,{type:e});return c}" +
                "blob = base64toBlob(base64Image, '" + fileType + " ');" +
                "blob.name = 'lala.txt';" +
                "myZone.addFile(blob);";
        return dropzoneSript;
    }

    public static String convertFileToBase64String(String fileName) throws IOException {

        File file = new File(fileName);
        int length = (int) file.length();
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[length];
        reader.read(bytes, 0, length);
        reader.close();
        String encodedFile = Base64.getEncoder().encodeToString(bytes);

        return encodedFile;
    }

    /**
     * Selecciona una fecha desdde la ui del date picker
     *
     * @authro Rodrigo Crespillo
     * @version 1.0 14/07/2017
     *
     * @param aDate
     */
    @Override
    public void selectDateFromUi(Date aDate){

        By datePicker = By.cssSelector("#ui-datepicker-div");
        By prevButton = By.cssSelector("#ui-datepicker-div a.ui-datepicker-prev");
        By nextButton = By.cssSelector("#ui-datepicker-div a.ui-datepicker-next");
        By closeButton = By.cssSelector("#ui-datepicker-div button.ui-datepicker-close");
        By todayButton = By.cssSelector("#ui-datepicker-div button.ui-datepicker-close");
        By calendatDays = By.cssSelector("#ui-datepicker-div table.ui-datepicker-calendar td[data-handler='selectDay']:not(.ui-datepicker-other-month)");


        String s_day = new SimpleDateFormat("d").format(aDate);
        String s_year =  new SimpleDateFormat("yyyy").format(aDate);
        String s_month = new SimpleDateFormat("M").format(aDate);

        int day = Integer.parseInt(s_day);
        int  month = Integer.parseInt(s_month);
        month -= 1;
        int year = Integer.parseInt(s_year);

        Boolean dateMath = false;

        while (!dateMath){

            WebElement prev_button = driver.findElement(prevButton);
            WebElement next_button = driver.findElement(nextButton);
            WebElement close_button = driver.findElement(closeButton);
            WebElement today_button = driver.findElement(todayButton);

            ArrayList<WebElement> daysElements = (ArrayList<WebElement>) driver.findElements(calendatDays);
            for (WebElement dayElement : daysElements){
                WebElement dayInside = dayElement.findElement(By.cssSelector("a.ui-state-default"));
                int trueDay = Integer.parseInt(dayInside.getText());

               if (Integer.parseInt(dayElement.getAttribute("data-year")) < year){
                   log.info("Faltan anhos, next!");
                    next_button.click();
                    break;
                }
                if (Integer.parseInt(dayElement.getAttribute("data-year")) > year){
                    log.info("Sobran anhos, prev!");
                    prev_button.click();
                    break;
                }

               if (Integer.parseInt(dayElement.getAttribute("data-month")) < month){
                   log.info("Faltan meses, next!");
                    next_button.click();
                    break;
                }
                if (Integer.parseInt(dayElement.getAttribute("data-month")) > month){
                    log.info("Sobran meses, prev!");
                    prev_button.click();
                    break;
                }

                if (trueDay != day) continue;

                log.info("Encontro el dia");
                dayElement.click();
                dateMath = true;
                break;
            }
        }
    }


    /**
     * Busca y selecciona una opcion en un autoComplete
     *
     * @author Rodrigo Crespillo
     * @version 1.0 17/07/2017
     * @param optionToSearch
     * @param autoCompleteName      */
    @Override
    public void searchOption(String optionToSearch, String autoCompleteName) throws AgentException {

        String searhBoxPath = "#" + autoCompleteName + "_folderSearchAutocomplete";
        String optionsBoxPath = ".ui-autocomplete a";

        By searhBox = By.cssSelector(searhBoxPath);
        By optionsBox = By.cssSelector(optionsBoxPath);

        WebElement searhBox_input = driver.findElement(searhBox);
        searhBox_input.sendKeys(optionToSearch);
        if (checkElementIsDisplayed(optionsBox)){
            markOptionSelectorMultiple(optionToSearch, optionsBox);
        }

    }

    /**
     * Compara si un String es igual a otro
     *
     * @author Rodrigo Crespillo
     * @version 1.0 25/07/2017
     *
     * @param thisOne
     * @param equalToThis
     */
    @Override
    public void aceptStringIfEqualTo(String thisOne, String equalToThis) {
        Assert.assertEquals(thisOne, equalToThis);
        log.info("expected [" + thisOne + "], found [" + equalToThis +"]");
    }

    @Override
    public void selectFormWhere(String field, String valueEqualTo) throws AgentException {
        int index = getFieldValueIndex(field);
        if (fieldFound(index)){
            WebElement cell = getCellByValueInColumn(valueEqualTo, index);
            if (cell != null){
                doubleClick(cell);
                doubleClick(cell);
            }
        }
    }

    @Override
    public void selectFormWhere(String field, ArrayList<String> options) {
        int index = getFieldValueIndex(field);
        if (fieldFound(index)) {
            for (String option: options){
                WebElement checkBox = getCheckBoxforOption(option, index);
                if (checkBox != null) {
                    checkBox.click();
                }
            }
        }
    }


    @Override
    public boolean getIsChecked(By cssSelector) {
        String isChecked = find((By) cssSelector).getAttribute("checked");
        if (isChecked == null){
            isChecked = "false";
        }
       return isChecked.equals("true");
    }

    @Override
    public void aceptIfBoolean(boolean isChecked_control, boolean isChecked) {
        if (isChecked){
            Assert.assertTrue(isChecked_control);
        }else {
            Assert.assertFalse(isChecked_control);
        }
    }

    @Override
    public void aceptSelectOption(By selectByCss, String optionExpected) {
        Select select_select = new Select(find(selectByCss));
        String option_selected = select_select.getFirstSelectedOption().getText();
        Assert.assertEquals(option_selected, optionExpected);
        log.info("optionExpected: " + optionExpected + ", optionFind: " + option_selected);
    }

    @Override
    public void aceptSelectOptions(By optionList, ArrayList<String> optionsExpected) {
        Select select = new Select(find(optionList));
        ArrayList<WebElement> selectOptions = (ArrayList<WebElement>) select.getOptions();
        ArrayList<String> options = getElementsListText(selectOptions);
        Assert.assertTrue(listEqualsNoOrder(options, optionsExpected));
    }

    @Override
    public void aceptSearchMultiple(By selectOption, ArrayList<String> optionsExpected) {
        ArrayList<WebElement> selectValues = (ArrayList<WebElement>) driver.findElements(selectOption);
        ArrayList<String> options = getElementsListText(selectValues);
        Assert.assertTrue(listEqualsNoOrder(options, optionsExpected));
    }

    @Override
    public void selectOptionMenu(By documentsOptionsSelector, String optionExpected) {
        ArrayList<WebElement> optionsMenu = (ArrayList<WebElement>) findElements(documentsOptionsSelector);
        for (WebElement option : optionsMenu){
            WebElement insideOption = option.findElement(By.cssSelector("div.menu-text"));
            if (insideOption.getText().equals(optionExpected)){
                option.click();
                break;
            }
        }
    }



    public ArrayList<String> getElementsListText(ArrayList<WebElement> selectOptions){
        ArrayList<String> options = new ArrayList<>();
        for (WebElement option : selectOptions){
            options.add(option.getText());
        }
        return options;
    }

    public static <T> boolean listEqualsNoOrder(List<T> l1, List<T> l2) {
        final Set<T> s1 = new HashSet<>(l1);
        final Set<T> s2 = new HashSet<>(l2);
        log.info("find [" + s1.toString() +"] expected [" + s2.toString() + "]");
        return s1.equals(s2);
    }

    private int getFieldValueIndex(String field){
        String tableHeadPath = ".pq-td-div > span[title]";
        By tableHead_byCSS = By.cssSelector(tableHeadPath);
        driver.switchTo().defaultContent();
        ArrayList <WebElement> tableHead_elements = (ArrayList<WebElement>) driver.findElements(tableHead_byCSS);
        for (WebElement field_element : tableHead_elements){ //img[@id='rama6385']
            String columnTitle = field_element.getText();
            if (columnTitle.equals(field)){
                return tableHead_elements.indexOf(field_element);
            }
        }
        return -1;
    }

    private WebElement getCheckBoxforOption(String valueEqualTo, int column){
        int index = column + 1;
        String tableRowPath = "tr.pq-grid-row[pq-row-indx]";
        By tableRow = By.cssSelector(tableRowPath);
        ArrayList<WebElement> tableRows_elements = (ArrayList<WebElement>) driver.findElements(tableRow);
        for (WebElement row : tableRows_elements){
            String rowValue = row.findElement(By.cssSelector("td[pq-col-indx='" + index + "'] a")).getText();
            if (rowValue.equals(valueEqualTo)){
                return row.findElement(By.cssSelector("td[pq-col-indx='0'] input"));
            }
        }
        return null;
    }

    private WebElement getCellByValueInColumn(String valueEqualTo, int column) {
        int index = column + 1;
        String tableCellColumnPath = "tr.pq-grid-row td[pq-col-indx='" + index + "'] a";
        By tableCellColumn = By.cssSelector(tableCellColumnPath);
        ArrayList<WebElement> tableCellColumn_elements = (ArrayList<WebElement>) driver.findElements(tableCellColumn);
        for (WebElement cell : tableCellColumn_elements) {
            String cellText = cell.getText();
            if (cellText.equals(valueEqualTo)) {
                return cell;
            }
        }
        return null;
    }

    private boolean fieldFound(int index){
        return index >= 0;
    }

}
