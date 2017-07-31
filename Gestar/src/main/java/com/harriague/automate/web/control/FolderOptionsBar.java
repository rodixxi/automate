package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class FolderOptionsBar {

    private By idSelector = By.cssSelector("#leftSideMenuContainer");

    public FolderOptionsBar(By idSelector) {
        this.idSelector = idSelector;
    }

    public FolderOptionsBar() {

    }

    public By getIdSelector() {
        return idSelector;
    }
}
