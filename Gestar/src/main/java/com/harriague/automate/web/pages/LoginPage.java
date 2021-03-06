package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

public interface LoginPage extends Page {

	void hacerLogin() throws AgentException, InterruptedException;

    void doLogin(String usuario, String instance) throws AgentException, InterruptedException;
	
	void hacerLogin(String usuario, String pwd, String instance) throws AgentException, InterruptedException;
	
	void hacerLogout() throws AgentException;
	
	void pruebo() throws AgentException;

    void doLoginWithPassAndTextInstance(String user, String pass, String instance) throws AgentException;

	void switchURL(String url) throws AgentException;
}
