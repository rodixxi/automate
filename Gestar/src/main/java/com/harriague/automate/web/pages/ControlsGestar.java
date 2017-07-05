package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

public interface ControlsGestar extends Page{

    void crearNuevoArchivoEnFormulario() throws AgentException, InterruptedException;

    void seleccionarTabPanel(String tab) throws AgentException, InterruptedException;

}
