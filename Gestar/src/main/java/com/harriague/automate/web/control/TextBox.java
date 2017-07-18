package com.harriague.automate.web.control;

import org.openqa.selenium.By;

import java.util.ArrayList;


public class TextBox extends Control {

    private String height;
    private String width;

    private Boolean isNumeric = false;
    private Boolean isRequired = false;

    public enum Modes {
        one_line,
        multiple_line,
        password
    }

    private Modes mode = Modes.one_line;

    public TextBox(String name) {
        super(name);
        setCssSelectorById();
    }

    public TextBox(String name, Modes mode) {
        super(name);
        this.mode = mode;
        setCssSelectorById();

    }

    public TextBox(String name, Boolean isNumeric, Boolean isRequired) {
        super(name);
        this.isNumeric = (isNumeric) ? isNumeric : false;
        this.isRequired = (isRequired) ? isRequired : false;
        setCssSelectorById();

    }

    public Boolean getNumeric() {
        return isNumeric;
    }

    public void setIsNumeric() {
        isNumeric = !isNumeric;
        setCssSelectorById();
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setIsRequired() {
        isRequired = !isRequired;
        setCssSelectorById();
    }

    public Modes getMode() {
        return mode;
    }

    public void setMode(Modes mode) {
        this.mode = mode;
    }


    @Override
    public void setCssSelectorById() {
        String id = ifModeMultipleLine() + "#" + getName() + "[name='" + getName() + "']" + getAttributes();
        setCssSelector(By.cssSelector(id));
    }

    @Override
    public void setCssSelectorById(String id) {
        id = ifModeMultipleLine() + "#" + id + "[name='" + id + "']" + getAttributes();
        super.setCssSelectorById(id);
    }

    public String ifRequired() {
        String text = (isRequired ? "isrequired='1'" : "");
        return text;
    }


    public String ifNumeric() {
        String text = (isNumeric ? "onblur" : "");
        return text;
    }

    public String ifModeMultipleLine() {
        String text = (mode == Modes.multiple_line ? "textarea" : "input");
        return text;
    }

    public String ifModePassword() {
        String text = (mode == Modes.password ? "type='password'" : "");
        return text;
    }

    public String getAttributes() {
        String text = "";
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add(ifRequired());
        attributes.add(ifModePassword());
        attributes.add(ifNumeric());
        while (attributes.remove("")) {
        }
        int attrSize = attributes.size();
        for (String a : attributes) {
            text += "[" + a + "]";
        }
        return text;
    }
}
