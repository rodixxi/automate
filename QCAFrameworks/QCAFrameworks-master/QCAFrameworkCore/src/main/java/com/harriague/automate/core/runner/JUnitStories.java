package com.harriague.automate.core.runner;

import java.util.List;

import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.embedder.Embedder;

import com.harriague.automate.core.exceptions.ExecutionException;

public abstract class JUnitStories extends ConfigurableEmbedder {

	/**
	 * Run Test with junit capabilities
	 */
    public void run() throws Throwable {
    	initilize();
        Embedder embedder = getEnbedder();
        try {
            embedder.runStoriesAsPaths(storyPaths());
        } finally {
            embedder.generateCrossReference();
        }
    }

    /**
     * Initialize jbehave capabilities
     */
    protected void initilize() throws ExecutionException {
    	 configuredEmbedder().embedderControls().doBatch(false)
 				.doGenerateViewAfterStories(true)
 				.doIgnoreFailureInStories(false)
 				.doIgnoreFailureInView(false)
          .doSkip(false)
          .doVerboseFailures(false)
          .doVerboseFiltering(false)
          .useStoryTimeoutInSecs(1200)
          .useThreads(1);
    }
    
    /**
     * Get enbedder
     * @return enbedder
     */
    protected Embedder getEnbedder() {
    	return configuredEmbedder();
    }
    
    /**
     * Get all stories paths
     * @return List<String> stories path
     */
    protected abstract List<String> storyPaths();

}
