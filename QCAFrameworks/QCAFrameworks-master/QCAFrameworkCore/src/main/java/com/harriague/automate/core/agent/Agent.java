package com.harriague.automate.core.agent;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.structures.FlawedTimeUnit;
import com.harriague.automate.core.structures.ScrollDirection;
import com.harriague.automate.core.structures.SwipeDirection;

import org.openqa.selenium.support.ui.ExpectedCondition;



public interface Agent {

    /**
     * Start agent
     * 
     * @param applicationName application name
     * @throws AgentException
     */
    void start(String applicationName) throws AgentException;

    /**
     * Navigate to url in browser
     * 
     * @param url to navigate
     * @throws AgentException
     */
    void navigateTo(String url) throws AgentException;

    /**
     * Waits for an element to vanish from view.
     * 
     * @param element
     * @throws AgentException
     */
    public void waitForVanish(Object element) throws AgentException;

    /**
     * Waits for an element to vanish from screen, in a certain amount of time.
     * 
     * @param element
     * @param timeout
     * @throws AgentException
     */
    public void waitForVanish(Object element, FlawedTimeUnit timeout) throws AgentException;

    /**
     * Writes a given text in an element.
     * 
     * @param element
     * @throws AgentException
     */
    public void writeInElement(Object element, String text) throws AgentException;

    /**
     * Takes a screenshot.
     * 
     * @throws AgentException
     */
    public void takeScreenshot() throws AgentException;

    /**
     * Highlights an element.
     * 
     * @param element
     * @throws AgentException
     */
    public void highlightElement(Object element) throws AgentException;

    /**
     * Sees if an element is displayed in screen.
     * 
     * @param element
     * @return
     * @throws AgentException
     */
    public boolean checkElementIsDisplayed(Object element) throws AgentException;

    /**
     * Sees if an element is displayed in screen, in a certain amount of time.
     * 
     * @param element
     * @param timeout
     * @return
     * @throws AgentException
     */
    public boolean checkElementIsDisplayed(Object element, FlawedTimeUnit timeout);

    /**
     * Sends a key press.
     * 
     * @param key
     * @throws AgentException
     */
    public void pressKey(Object key) throws AgentException;

    /**
     * Click in an element.
     * 
     * @param element
     * @throws AgentException
     */
    public void click(Object element) throws AgentException;

    /**
     * Right click in an element.
     * 
     * @param element
     * @throws AgentException
     */
    public void rightClick(Object element) throws AgentException;

    /**
     * Double click in an element.
     * 
     * @param element
     * @throws AgentException
     */
    public void doubleClick(Object element) throws AgentException;
    
    /**
     * Scrolls the view a certain amount in the given direction.
     * 
     * @param direction
     * @param amount
     * @throws AgentException
     */
    public void scroll(ScrollDirection direction, int amount) throws AgentException;

    public boolean alertIspresent() throws AgentException;
    
    public void aceptAlert() throws AgentException; 
    
    public void cancelAlert() throws AgentException;

    /**
     * Select a date(dd/mm/yyyy) from a datePicker control.
     * @param aDate
     * @param anElement
     * @throws AgentException
     * @author rcrespillo
     */
    public void selectDateFromDatePicker(Object anElement, Date aDate) throws AgentException;

    /**
     * Select a selector option
     * @param selector
     * @param option
     * @throws AgentException
     * @author rcrespillo
     */
    public void selectSelectorOption(String selector, String option) throws AgentException;
    /**
     * Close agent
     */
    public void close();

    /**
     * Get a text from element
     * 
     * @param element to find and read
     * @return String Text
     * @throws AgentException
     */
    public String getTextValue(Object element) throws AgentException;

    /**
     * Get a property form element
     * 
     * @param element to find
     * @param attribute property to read
     * @return String property value
     * @throws AgentException
     */
    public String getValue(Object element, String attribute) throws AgentException;

    /**
     * Get a grid
     * 
     * @param id of the grid
     * @return Map grid values
     * @throws AgentException
     */
    public List<Map<String, Object>> getGrid(Object id) throws AgentException;

    /**
     * Select a option in a combobox
     * 
     * @param xpath combo box Id
     * @param value option
     * @throws AgentException
     */
    public void selectInCombobox(Object element, Object value) throws AgentException;

    /**
     * Maximize window
     * 
     * @throws AgentException
     */
    public void maximizeWindows() throws AgentException;

    /**
     * Switch betwen popup and principal windows
     *
     * @return parentWindowHandler
     * @throws AgentException
     */
    public String switchToPopup() throws AgentException;

    /**
     * Switch to principal windows
     *
     * @param parentWindowHandler
     * @throws AgentException
     */
    public void switchToPopup(String parentWindowHandler) throws AgentException;

    /**
     * Hover in element
     * 
     * @param xpath element
     * @throws AgentException
     */
    public Object hover(Object element) throws AgentException;

    public void scrollIntoView(Object element) throws AgentException;

    public Object findElement(Object by) throws AgentException;

    public void back() throws AgentException;

    void swipe(SwipeDirection direction);

    void swipe(int startx, int starty, int endx, int endy, FlawedTimeUnit duration);

    public Object findElements(Object by);

    void longPressKey(Object key);

    <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException;

    /**
     * Gets the window height
     */
    int getHeight();

    /**
     * Gets the window width
     */
    int getWidth();

    /**
     * Convenience method that takes a screenshot of the device and returns a BufferedImage for
     * further processing.
     * 
     * @return screenshot from the device as BufferedImage
     * @throws AgentException 
     */
    public BufferedImage takeScreenshotAsBuffer() throws AgentException;

    public void clickInTheScreen(int fingers, int positionX, int positionY, FlawedTimeUnit duration) throws AgentException;

    //public void waitUntil(ExpectedCondition<WebElement> condition) throws AgentException ;
    
    //public void waitUntil(int _time) ;
    
    
    public Process sendCommand(String command) throws AgentException;

    public Process waitForCommand(String command) throws AgentException;
    
   //public void SwitchDriver(Object by) throws AgentException;

    /**
     * Rodrigo Crespillo
     * /12/07/2017
     * ver 1.0
     * Agrega un archivo a un attachemnt
     * @param fileButtom
     * @param filePath
     */
    public void selectFile(String fileButtom, String filePath);
    
    }
