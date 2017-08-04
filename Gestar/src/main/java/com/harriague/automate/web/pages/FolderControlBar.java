package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;

public interface FolderControlBar {

    void atFolderLevel() throws AgentException;

    void selectView(String viewName);
}
