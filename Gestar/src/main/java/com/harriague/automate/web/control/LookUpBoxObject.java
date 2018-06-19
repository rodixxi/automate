package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class LookUpBoxObject extends LookUpBoxAccounts {

    private String searchTypePath = "#lstType";
    private By searchByType = By.cssSelector(searchTypePath);

    public LookUpBoxObject(String name, String title) {
        super(name, title);
        String searchButtonAux = getSearchButtonPath() + "[src*='search']";
        setSearchButtonPath(searchButtonAux);
    }

    public LookUpBoxObject(String name) {
        super(name);
        String searchButtonAux = getSearchButtonPath() + "[src*='search']";
        setSearchButtonPath(searchButtonAux);
    }

    public By getSearchByType() {
        return searchByType;
    }

    public void setSearchByType(By searchByType) {
        this.searchByType = searchByType;
    }
}
