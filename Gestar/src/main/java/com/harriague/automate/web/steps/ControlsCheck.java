package com.harriague.automate.web.steps;

import com.harriague.automate.core.test.RunStory;
import org.testng.annotations.Parameters;

import java.util.List;

public class ControlsCheck extends RunStory {

    @Parameters({ "storyName" })
    protected List<String> storyPaths(String storyName) {

        storyPath = storyName;

        findBeforeAndAfter();
        return story;
    }
}
