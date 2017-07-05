package com.harriague.automate.web.steps;

import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.LoginPage;
import org.jbehave.core.annotations.When;

public class AllControlsCheckGestar extends StepBase {

    @When("me conecto a gestar con el usuario: $usuario sin pass  a la instancia $instance")
    public void loginGestar(String usuario, String instance) throws InterruptedException, Exception {
        String pwd = "";
        getPage(LoginPage.class).hacerLogin(usuario, pwd, instance);
    }

}
