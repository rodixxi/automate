package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class SelectControl extends Control{


    public SelectControl(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }

    public SelectControl(String nombre) {
        super(nombre);
        String path = "#" + getNombre();
        setCssSelectorById(path);
    }
}
