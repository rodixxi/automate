package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class LookupBoxObjectsITIL extends LookUpBoxObject {
    public LookupBoxObjectsITIL(String name, String title) {
        super(name, title);
        String searchButtonAux = getControlTablePath() + " img#searchEntity";
        setSearchButtonPath(searchButtonAux);
    }

    public LookupBoxObjectsITIL(String name) {
        super(name);
        String searchButtonAux = getControlTablePath() + " img#searchEntity";
        setSearchButtonPath(searchButtonAux);
    }

}
