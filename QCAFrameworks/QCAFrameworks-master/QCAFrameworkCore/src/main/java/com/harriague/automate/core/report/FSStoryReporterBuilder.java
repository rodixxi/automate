package com.harriague.automate.core.report;

import java.lang.reflect.Constructor;

import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;

import com.harriague.automate.core.utils.ReadProperty;

public class FSStoryReporterBuilder extends StoryReporterBuilder {

    public FSStoryReporterBuilder() {
        super();
    }

    public StoryReporter build(String storyPath) {
        StoryReporter delegate = super.build(storyPath);
        try {
            String reporter = ReadProperty.getProperty("custom.reporter", null);
            Class[] type = { StoryReporter.class };
            if(reporter != null) {
                Class classDefinition = Class.forName(reporter);
                Constructor cons = classDefinition.getConstructor(type);
                Object[] obj = { delegate};
                return (StoryReporter) cons.newInstance(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FSStoryReporter(delegate);
        // return new AllureRunner(delegate);
    }
}
