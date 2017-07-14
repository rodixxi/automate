package com.harriague.automate.web.steps;

import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.LoginPage;
import com.harriague.automate.web.pages.ControlsGestar;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import java.util.ArrayList;
import java.util.Map;

public class AllControlsCheckGestar extends StepBase {

    @When("me conecto a gestar con el usuario: $usuario sin pass a la instancia $instance")
    public void loginGestar(String usuario, String instance) throws InterruptedException, Exception {
        getPage(LoginPage.class).hacerLogin(usuario, instance);
    }

    @When("crear arhivo nuevo")
    public void crearNuevoArchivoEnFormulario() throws Exception {
        getPage(ControlsGestar.class).crearNuevoArchivoEnFormulario();
    }

    @When("seleccionar tabPanel $tab")
    public void seleccionarTabPanel(String tab) throws Exception {
        getPage(ControlsGestar.class).seleccionarTabPanel(tab);
    }

    @When("ingreso $input para el campo requerido: $campo")
    public void ingresoInputACampoRequerido(String input, String campo) throws Exception{
        getPage(ControlsGestar.class).ingresoInputACampoRequerido(input, campo);
    }

    @When("ingreso $input en $campo")
    public void ingresoInputACampo(String input, String campo) throws Exception{
        getPage(ControlsGestar.class).ingresoInputACampo(input, campo);
    }

    @When("ingreso $input para el campo numerico: $campo")
    public void ingresoInputACampoNumerico(String input, String campo) throws Exception{
        getPage(ControlsGestar.class).ingresoInputACampoNumerico(input, campo);
    }

    @When("ingreso $input para campo de texto multiple: $campo")
    public void ingresoInputACampoMultiple(String input, String campo) throws Exception{
        getPage(ControlsGestar.class).ingresoInputACampoMultiple(input, campo);
    }

    @When("ingreso $input para el campo password: $campo")
    public void ingresoInputACampoPassword(String input, String campo) throws Exception{
        getPage(ControlsGestar.class).ingresoInputACampoPassword(input, campo);
    }

    @When("adjuntar el archivo: $url, en andjunto: $adjunto")
    public void openPopup(String url, String adjunto) throws Exception {
        getPage(ControlsGestar.class).openPopup(url, adjunto);
    }

    @When("cargo manualmente la fecha: $date, con $hh:$mm  en DTPicker: $dtpicker")
    public void cargarDateManual(String date, String hh, String mm, String dtpicker) throws Exception {
        getPage(ControlsGestar.class).cargarDateManual(date, hh, mm, dtpicker);
    }

    @When("cargo por calendario la fecha: $date, en DTPicker: $dtpicker")
    public void cargarDateCalendario(String date, String dtpicker) throws Exception {
        getPage(ControlsGestar.class).cargarDateCalendario(date, dtpicker);
    }

    @When("selecciono opcion: $opcion, de el selector: $selector")
    public void selectOption(String opcion, String selector) throws Exception {
        getPage(ControlsGestar.class).selectOption(opcion, selector);
    }

    @When("selecciono las opciones: $tableOptions del selector multiple: $selector")
    public void selectMultipleoptions(ExamplesTable $tableOptions, String selector) throws Exception {
        ArrayList<String> options = new ArrayList();
        for (Map<String,String> row : $tableOptions.getRows()){
            String option = row.get("options");
            options.add(option);
        }
        getPage(ControlsGestar.class).selectMultipleoptions(options, selector);
    }
}
