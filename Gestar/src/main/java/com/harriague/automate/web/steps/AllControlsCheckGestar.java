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
}
