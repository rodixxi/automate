package com.harriague.automate.core.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.harriague.automate.core.runner.JBehaveRunner;


/**
 * Base Test case class
 */
public class TestBase extends JBehaveRunner {

    /*
     * Test case status flag
     */
    private boolean runStatus = true;

    /*
     * Stories list
     */
    protected List<String> story = new ArrayList<String>();

    /*
     * Logger
     */
    protected Logger log = Logger.getLogger(TestBase.class.getName());

    /*
     * Story path
     */
    protected String storyPath;


    /**
     * Run a story
     *
     * @param storyPath
     *            story Path
     * @throws Throwable
     */
    public void runTest() throws Throwable {
        initilize();
        printStartBanner();
        JBehaveRunner.setTestName(getStoryName());

        try {
            runner();
        } catch (Exception e) {
            boolean error = checkInReport(e, "TestErrorsScenariosFailed",
                    "scenariosFailed=");
            if (System.getProperty("TestErrorsScenariosPending") == null) {
                System.setProperty("TestErrorsScenariosPending", "0");
            }
            boolean pending = checkInReport(e, "TestErrorsScenariosPending",
                    "scenariosPending=");
            if (error || pending) {
                this.runStatus = false;
                throw new Throwable(e.getMessage(), e);
            }
        } finally {
            printEndBanner();
        }
    }

    /**
     * Run this test case
     * @throws Throwable
     */
    public void runner() throws Throwable {
        try {
            embedder.runStoriesAsPaths(story);
        } finally {
            embedder.generateCrossReference();
        }
    }


    /**
     * Print the start banner
     */
    private void printStartBanner() {
        log.info("--------------------------------------------------------------------------");
        log.info("Start story " + getStoryName() + ".");
        log.info("--------------------------------------------------------------------------");
        storyToRun();
    }

    /**
     * Print the end banner
     */
    private void printEndBanner() {
        String status = (runStatus) ? "Passed" : "Failed";
        log.info("--------------------------------------------------------------------------");
        log.info("End story " + getStoryName() + "." );
        log.info("Test status : " + status);
        log.info("--------------------------------------------------------------------------");
    }

    /**
     * Print the end banner
     */
    private void storyToRun() {
        String message = "";
        for (String story : this.story) {
            message = (story.contains("Before")) ? "Exist a before story for this testcase."
                    : message;
            if (story.contains("After")) {
                message = (message.equals("")) ? "Exist a after story for this testcase."
                        : message.replaceAll("story for this testcase.",
                        "and after story for this testcase.");
            }
        }
        if(!message.equals("")) {
            log.info(message);
            log.info("--------------------------------------------------------------------------");
        }
    }

    /**
     * Get story name
     *
     * @return String story name
     */
    private String getStoryName() {
        if(story.isEmpty()) {
            storyPaths();
        }
        String[] path = story.get(0).replaceAll("After", "").replaceAll("Before", "").split("/");
        return path[path.length - 1].split("\\.")[0];
    }

    /**
     * Check a value in the JBehave report
     * @param e exception
     * @param propertyName property name
     * @param propertyTemplate property to check
     * @return <true> the value is change
     * @throws Throwable
     */
    public boolean checkInReport(Exception e, String propertyName,
                                 String propertyTemplate) throws Throwable {
        if (System.getProperty(propertyName) == null) {
            setProperty(propertyName, "1");
            return true;
        } else {
            if (!e.getMessage().contains(
                    propertyTemplate + System.getProperty(propertyName))) {
                setProperty(
                        propertyName,
                        ""
                                + (Integer.valueOf(System
                                .getProperty(propertyName)) + 1));
                return true;
            }
        }
        return false;
    }

    /**
     * Set a property
     * @param propertyName property name
     * @param propertyValue property value
     */
    private void setProperty(String propertyName, String propertyValue) {
        System.setProperty(propertyName, propertyValue);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        if (!System.getProperty(propertyName).equals(propertyValue)) {
            System.setProperty(propertyName, propertyValue);
        }
    }
}
