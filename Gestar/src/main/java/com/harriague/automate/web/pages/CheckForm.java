package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;

public interface CheckForm {

    void getFormWhere(String field, String valueEqualTo) throws AgentException;
}
