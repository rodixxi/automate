package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Accordion extends Control {

    private String accordion_path = "ui-accordion-accordion_" + getName() + "-header-0";
    private By accordion = By.cssSelector(accordion_path);

    public Accordion(String name, String title) {
        super(name, title);
    }

    public Accordion(String name) {
        super(name);
    }

    public By getAccordion() {
        return accordion;
    }
}
