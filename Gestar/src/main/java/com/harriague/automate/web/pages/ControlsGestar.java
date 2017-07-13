package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

import java.util.ArrayList;

public interface ControlsGestar extends Page{

    void crearNuevoArchivoEnFormulario() throws AgentException, InterruptedException;

    void seleccionarTabPanel(String tab) throws AgentException, InterruptedException;

    void ingresoInputACampoRequerido(String input, String campo) throws AgentException, InterruptedException;

    void ingresoInputACampo(String input, String campo) throws AgentException, InterruptedException;

    void ingresoInputACampoNumerico(String input, String campo) throws AgentException;

    void ingresoInputACampoMultiple(String input, String campo) throws AgentException;

    void ingresoInputACampoPassword(String input, String campo) throws AgentException;

    void openPopup(String url, String popup) throws AgentException;

    void cargarDate(String date, String hh, String mm, String dtpicker) throws AgentException;

    void selectOption(String opcion, String selector) throws AgentException;

    void selectMultipleoptions(ArrayList<String> options, String selector) throws AgentException;
}
