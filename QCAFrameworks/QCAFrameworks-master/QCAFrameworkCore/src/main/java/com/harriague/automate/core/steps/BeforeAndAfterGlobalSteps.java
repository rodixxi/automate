package com.harriague.automate.core.steps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStories;
import com.harriague.automate.core.conf.Constants;
import com.harriague.automate.core.device.DeviceManager;
import com.harriague.automate.core.report.LogReport;
import com.harriague.automate.core.report.ScreenshotReport;
import com.harriague.automate.core.report.ScreenshotTaker;
import com.harriague.automate.core.runner.JBehaveRunner;

public class BeforeAndAfterGlobalSteps {

    protected final Logger log = Logger.getLogger(BeforeAndAfterGlobalSteps.class.getName());

    private List<String> stories = new ArrayList<String>();

    /**
     * Close all agents
     * 
     * @throws InterruptedException
     * @throws IOException
     * @throws AgentException
     */
    @AfterScenario(uponOutcome = AfterScenario.Outcome.SUCCESS)
    public void afterScenario() throws Exception {
        log.info("After Scenario on success");
        stories.add(JBehaveRunner.getScriptName());
    }

    /**
     * After suite
     * 
     * @throws Exception
     */
    @AfterStories
    public void afterSuite() throws Exception {
        log.info("Afters stories");
        DeviceManager.closeDevices();
        LogReport.createLogReport();
        LogReport.addLog(stories, Constants.LOG_GROUP_LOGS);

    }

    /**
     * After scenario
     * 
     * @throws Exception
     */
    @AfterScenario(uponOutcome = AfterScenario.Outcome.FAILURE)
    public void afterScenarioFailure() throws Exception {
        log.info("After Scenario on failure");
        try{
        File screenshotFile = ScreenshotTaker.takeScreenShot(JBehaveRunner.getScriptName(),
                DeviceManager.getCurrentDevice(), DeviceManager.getCurrentApp());
        ScreenshotReport.addScreenshot(screenshotFile);
        stories.add(JBehaveRunner.getScriptName());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
