package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public interface Attachment extends ControlInterface{

    By getInputButton();

    By getAddButton();

    By getDeleteButton();

    By getCloseButton();

    By getAttachButton();

    By getAttatchName();
}
