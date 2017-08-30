package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class DTPickerNew extends Control {

    private String buttonPath = "#" + getName() + "_div > span.input-group-addon";
    private String datePath = "#" + getName();
    private String prevButtonPath = "#"+ getName() +"_div .datepicker-days .prev";
    private String nextButtonPath = "#"+ getName() +"_div .datepicker-days .next";
    private String dayInCalendarPath = "#"+ getName() +"_div td:not(.old):not(.new)[data-action='selectDay']";
    private String toggleCalendarButtonPath = "#"+ getName() +"_div a[data-action='togglePicker']";
    private String closeCalendarButtonPath = "#"+ getName() +"_div a[data-action='close']";
    private String hoursSelectorPath = "#"+ getName() +"_div div.timepicker table.table-condensed span[data-action='showHours']";
    private String minutesSelectorPath = "#"+ getName() +"_div div.timepicker table.table-condensed span[data-action='showMinutes']";
    private String increaseMinutesButtonPath = "#"+ getName() +"_div a[data-action='incrementMinutes']";
    private String decreaseMinutesButtonPath = "#"+ getName() +"_div a[data-action='decrementMinutes']";


    private By button = By.cssSelector(buttonPath);
    private By date = By.cssSelector(datePath);
    private By prevButton = By.cssSelector(prevButtonPath);
    private By nextButton = By.cssSelector(nextButtonPath);
    private By dayInCalendar = By.cssSelector(dayInCalendarPath);
    private By toggleCalendarButton = By.cssSelector(toggleCalendarButtonPath);
    private By closeCalendarButton = By.cssSelector(closeCalendarButtonPath);
    private By hoursSelector = By.cssSelector(hoursSelectorPath);
    private By minutesSelector = By.cssSelector(minutesSelectorPath);
    private By increaseMinutesButton = By.cssSelector(increaseMinutesButtonPath);
    private By decreaseMinutesButton = By.cssSelector(decreaseMinutesButtonPath);



    public DTPickerNew(String name, String title) {
        super(name, title);
    }

    public DTPickerNew(String name) {
        super(name);

    }

    public By getButton() {
        return button;
    }

    public By getDate() {
        return date;
    }

    public By getPrevButton() {
        return prevButton;
    }

    public By getNextButton() {
        return nextButton;
    }

    public By getDayInCalendar() {
        return dayInCalendar;
    }

    public By getToggleCalendarButton() {
        return toggleCalendarButton;
    }

    public By getCloseCalendarButton() {
        return closeCalendarButton;
    }

    public By getHoursSelector() {
        return hoursSelector;
    }

    public By getMinutesSelector() {
        return minutesSelector;
    }

    public By getIncreaseMinutesButton() {
        return increaseMinutesButton;
    }

    public By getDecreaseMinutesButton() {
        return decreaseMinutesButton;
    }
}
