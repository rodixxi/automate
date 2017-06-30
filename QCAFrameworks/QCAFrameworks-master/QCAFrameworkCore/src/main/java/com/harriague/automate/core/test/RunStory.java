package com.harriague.automate.core.test;

import java.lang.reflect.Field;
import java.util.List;

import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import com.harriague.automate.core.runner.JBehaveRunner;


/**
 */
public class RunStory extends TestBase implements ITest {

	/**
	 * Story path
	 */
	protected String storyPath;

	/**
	 * Tc Name
	 */
	protected String name;

	@Parameters({ "storyName" })
	@BeforeClass
	public void setStoryAndName(String storyName) {
		this.name = storyName.split("/")[storyName.split("/").length - 1].split("\\.")[0];
		JBehaveRunner.setTestName(name);
		this.storyPath = storyName;
	}

	/**
	 * Run Test case
	 */
	@Test
	public void run() throws Throwable {
		runTest();
	}

	/**
	 * Inject story in junit
	 */
	@Override
	protected List<String> storyPaths() {

		storyPath = (storyPath == null) ? System.getProperty(com.harriague.automate.core.conf.Parameters.RUNNER_PARAMETER_STORY_NAME) : storyPath;

		findBeforeAndAfter();
		return story;
	}

	/**
	 * Find before and after test
	 */
	public void findBeforeAndAfter() {

		if(this.getClass().getResourceAsStream(storyPath.substring(1).replaceAll(".story", "Before.story")) != null) {
			story.add(storyPath.replaceAll(".story", "Before.story"));
		}
		story.add(storyPath);
		if(this.getClass().getResourceAsStream(storyPath.substring(1).replaceAll(".story", "After.story")) != null) {
			story.add(storyPath.replaceAll(".story", "After.story"));
		}
	}

	/**
	 * Get test name
	 * @return String Test name
	 */
	@Override
	public String getTestName() {
		return name;
	}

	/**
	 * Set test case name
	 * @param result
	 */
	@AfterMethod
	public void SetResultTestName(ITestResult result) {
		try {
			BaseTestMethod bm = (BaseTestMethod)result.getMethod();
			Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
			f.setAccessible(true);
			f.set(bm, name);
		}catch (Exception ex) {
			System.out.println("ex" + ex.getMessage());
		}
	}
}
