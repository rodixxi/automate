package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class FolderOptionsBar {

    private String documentsMenuPath = "#documentsMenu a";
    private String quickMenuPath = "div#quickOptionsMenu";
    private String newFormPath = quickMenuPath + " div[title='Nuevo'] a";
    private String exportFormPath = quickMenuPath + " a#exportLink";
    private String quickOptionsPath = quickMenuPath + " a#searchLink";
    private String refreshPath = quickMenuPath + " a#refreshLink";
    private String printPath = quickMenuPath + " a#printLink";
    private String viewButtonsPath = "div#viewButtonsContainer";
    private String filtersPath = viewButtonsPath + " a#filtersLink";
    private String configPath = viewButtonsPath + " div#viewsmenu i.icon-cog";
    private String viewEditPath = viewButtonsPath + " li#viewEdit";
    private String newViewPath = viewButtonsPath = " li#newView";
    private String viewAdminPath = viewButtonsPath = " li#viewAdmin";
    private String viewsSelectPath = "div#rightSideMenuContainer select#viewsSelect";
    private String documentsOptionsPath = "div#documentsMenu ul ul li:not(.separator):not(.hidden) a";

    private By idSelector = By.cssSelector("#leftSideMenuContainer");
    private By newFormSelector = By.cssSelector(newFormPath);
    private By exportFormSelector = By.cssSelector(exportFormPath);
    private By quickOptionsSelecotr = By.cssSelector(quickOptionsPath);
    private By refreshSelector = By.cssSelector(refreshPath);
    private By printSelector = By.cssSelector(printPath);
    private By filtersSelector = By.cssSelector(filtersPath);
    private By configSelector = By.cssSelector(configPath);
    private By viewEditSelector = By.cssSelector(viewEditPath);
    private By newViewSelector = By.cssSelector(newViewPath);
    private By viewAdminSelector = By.cssSelector(viewAdminPath);
    private By viewsSelectSelector = By.cssSelector(viewsSelectPath);
    private By documentsOptionsSelector = By.cssSelector(documentsOptionsPath);

    public FolderOptionsBar(By idSelector) {
        this.idSelector = idSelector;
    }

    public FolderOptionsBar() {

    }

    public By getIdSelector() {
        return idSelector;
    }

    public By getNewFormSelector() {
        return newFormSelector;
    }

    public By getExportFormSelector() {
        return exportFormSelector;
    }

    public By getQuickOptionsSelecotr() {
        return quickOptionsSelecotr;
    }

    public By getRefreshSelector() {
        return refreshSelector;
    }

    public By getPrintSelector() {
        return printSelector;
    }

    public By getFiltersSelector() {
        return filtersSelector;
    }

    public By getConfigSelector() {
        return configSelector;
    }

    public By getViewEditSelector() {
        return viewEditSelector;
    }

    public By getNewViewSelector() {
        return newViewSelector;
    }

    public By getViewAdminSelector() {
        return viewAdminSelector;
    }

    public By getViewsSelectSelector() {
        return viewsSelectSelector;
    }

    public By getDocumentsOptionsSelector() {
        return documentsOptionsSelector;
    }
}
