package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;

import java.util.ArrayList;

public interface CheckForm {

    void getFormWhere(String field, String valueEqualTo) throws AgentException;

    void selectFormWhere(String field, ArrayList<String> options);
}
