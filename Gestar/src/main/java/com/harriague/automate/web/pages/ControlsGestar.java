package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

import java.text.ParseException;
import java.util.ArrayList;

public interface ControlsGestar extends Page{

    void crearNuevoArchivoEnFormulario() throws AgentException, InterruptedException;

    void seleccionarTabPanel(String tab) throws AgentException, InterruptedException;

    void ingresoInputACampoRequerido(String input, String campo) throws AgentException, InterruptedException;

    void ingresoInputACampo(String input, String campo) throws AgentException, InterruptedException;

    void ingresoInputACampoNumerico(String input, String campo) throws AgentException;

    void ingresoInputACampoMultiple(String input, String campo) throws AgentException;

    void ingresoInputACampoPassword(String input, String campo) throws AgentException;

    void attachFile(String url, String popup) throws AgentException;

    void cargarDateManual(String date, String hh, String mm, String dtpicker) throws AgentException;

    void selectOption(String opcion, String selector) throws AgentException;

    void selectMultipleOptions(ArrayList<String> options, String selector) throws AgentException;

    void cargarDateCalendario(String date, String dtpicker) throws AgentException, ParseException;

    void selectMultipleOptionsAll(String selector) throws AgentException;

    void deselectMultipleOptions(ArrayList<String> options, String selector) throws AgentException;

    void deselectMultipleOptionsAll(String selector) throws AgentException;

    void buscoOpcionAutoComplete(String search, String autoComplete, String orden) throws AgentException;
}
