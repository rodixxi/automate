package com.harriague.automate.web.steps;

import com.harriague.automate.core.test.RunStory;

import java.util.List;

public class Controls extends RunStory {

    @Override
    protected List<String> storyPaths() {

        storyPath = "./AllControlsCheckGestar.story";

        findBeforeAndAfter();
        return story;
    }
}
