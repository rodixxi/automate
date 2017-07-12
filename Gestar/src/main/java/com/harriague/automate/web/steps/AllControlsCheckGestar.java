package com.harriague.automate.web.steps;

import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.LoginPage;
import com.harriague.automate.web.pages.ControlsGestar;
import org.jbehave.core.annotations.When;

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

    @When("cargar el archivo: $url, en popup: $popup")
    public void openPopup(String url, String popup) throws Exception {
        getPage(ControlsGestar.class).openPopup(url, popup);
    }
}
