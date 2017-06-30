package com.harriague.automate.web.steps;

import java.util.List;

import com.harriague.automate.core.test.RunStory;


public class run extends RunStory {
	
	@Override
    protected List<String> storyPaths() {
		
	   storyPath = "./PlanningWeek.story";
		
	   findBeforeAndAfter();
       return story;
    }
}
