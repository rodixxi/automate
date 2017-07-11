package com.harriague.automate.web.control;
import org.openqa.selenium.By;

import java.util.ArrayList;


public class TextBox extends Control{

    private String height;
    private String width;

    private Boolean isNumeric = false;
    private Boolean isRequired = false;

    public enum Modes{
        one_line,
        multiple_line,
        password
    }

    private Modes mode = Modes.one_line;

    public TextBox(String etiqueta) {
        super(etiqueta);
        getControlByName();
    }

    public TextBox(String etiqueta, Modes mode) {
        super(etiqueta);
        this.mode = mode;
        getControlByName();

    }

    public TextBox(String nombre, String etiqueta, Boolean isNumeric, Boolean isRequired){
        super(nombre, etiqueta);
        this.isNumeric = (isNumeric) ? isNumeric : false;
        this.isRequired = (isRequired) ? isRequired: false;
        getControlById();
        getControlByName();

    }


    public Boolean getNumeric() {
        return isNumeric;
    }

    public void setIsNumeric() {
        isNumeric = !isNumeric;
        try {
            getControlById();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        getControlByName();
    }

    public Boolean getRequired() { return isRequired; }

    public void setIsRequired() {
        isRequired = !isRequired;
        try {
            getControlById();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        getControlByName();
    }

    public Modes getMode() {
        return mode;
    }

    public void setMode(Modes mode) {
        this.mode = mode;
    }


    @Override
    public void getControlById() throws NullPointerException{
        if (getNombre() != ""){
            String id = "//" + ifModeMultipleLine() + "[@id='" + getNombre() + "' and @name='" + getNombre() + "'" + getAttributes() + "]";
            setXpathSelectorById(By.xpath(id));
        }
        else throw new NullPointerException();
    }

    @Override
    public void getControlById(String id) {
        id = "//" + ifModeMultipleLine() + "[@id='" + id + "' and @name='" + id + "'" + getAttributes() + "]";
        super.getControlById(id);
    }

    @Override
    public void getControlByName() {
        String name = "//span[text()='" + getEtiqueta() + "']/ancestor::td[1]/following::td[1]/" + ifModeMultipleLine() + (getAttributes() != "" ? "[" + getAttributes() + "]" : "");
        setXpathSelectorByName(By.xpath(name));
    }

    @Override
    public void getControlByName(String name) {
        name = "//span[text()='" + name + "']/ancestor::td[1]/following::td[1]/" + ifModeMultipleLine() + (getAttributes() != "" ? "[" + getAttributes() + "]" : "") ;
        super.getControlByName(name);
    }

    public String ifRequired(){
        String text = (isRequired ? "@isrequired='1'": "" );
        return text;
    }


    public String ifNumeric(){
        String text = (isNumeric ? "@onblur": "" );
        return text;
    }

    public String ifModeMultipleLine(){
        String text = (mode == Modes.multiple_line ? "textarea" : "input");
        return text;
    }

    public String ifModePassword(){
        String text = (mode == Modes.password ? "@type='password'" : "");
        return text;
    }

    public String getAttributes(){
        String text = "";
        ArrayList<String> attributes = new ArrayList<String>();
        attributes.add(ifRequired());
        attributes.add(ifModePassword());
        attributes.add(ifNumeric());
        while(attributes.remove("")) {}
        int attrSize = attributes.size();
        //text += ( attrSize > 0 ? "[" : "");
        int attrLeft = attrSize;
        for (String a : attributes){
           text += a;
           attrLeft --;
           text += (attrLeft > 0 ? " and ": "");
        }
        //text += ( attrSize > 0 ? "]" : "");
        return text;
    }
}
