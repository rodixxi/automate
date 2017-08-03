package com.harriague.automate.web.steps;

import com.harriague.automate.core.test.RunStory;
import org.testng.annotations.Parameters;

import java.util.List;

public class ControlsCheck extends RunStory {

    @Override
    protected List<String> storyPaths() {

        storyPath = "./AllControlsCheckValuesGestar.story";

        findBeforeAndAfter();
        return story;
    }
}
