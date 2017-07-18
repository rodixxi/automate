package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Checkbox extends Control {


    public Checkbox(String name, String title) {
        super(name, title);
        setCssSelector();
    }

    public Checkbox(String name) {
        super(name);
        setCssSelector();
    }

    @Override
    public void setCssSelector() {
        By aux = By.cssSelector(getName() + "_chk");
        super.setCssSelector();
    }
}
