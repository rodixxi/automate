package com.harriague.automate.core.runner;

import static org.jbehave.core.reporters.Format.CONSOLE;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.AbsolutePathCalculator;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.StepFinder;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;

import com.harriague.automate.core.conf.Constants;
import com.harriague.automate.core.conf.Parameters;
import com.harriague.automate.core.conf.PropertiesKeys;
import com.harriague.automate.core.exceptions.ExecutionException;
import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.report.FSStoryReporterBuilder;
import com.harriague.automate.core.utils.ReadProperty;
// import com.familysafety.test.conf.TestBase;
import com.thoughtworks.paranamer.NullParanamer;

@RunWith(JUnitReportingRunner2.class)
public class JBehaveRunner extends JUnitStories {

    private Logger log = null;
    private static String testName = "";

    protected Embedder embedder;
    /*
     * path of the story
     */
    protected String storyPath;

    @Override
    protected Embedder getEnbedder() {
        return embedder;
    }

    /**
     * Set a story path
     * 
     * @param storyPath story path
     */
    public void setStoryPath(String storyPath) {
        this.storyPath = storyPath;
    }

    /*
     * JBehave configuration
     */
    private Configuration configuration;

    /**
     * Configure log4j
     */
    public void configureLog4j(String name) {
        name = (name == null) ? System
                .getProperty(
                        com.harriague.automate.core.conf.Parameters.RUNNER_PARAMETER_STORY_NAME)
                .split("/")[System
                        .getProperty(
                                com.harriague.automate.core.conf.Parameters.RUNNER_PARAMETER_STORY_NAME)
                        .split("/").length - 1].split("\\.")[0]
                : name;
        System.setProperty("logfile.name",
                Constants.LOG4J_LOG_FILE_PATH + name + Constants.LOG4J_LOG_FILE_EXTENSION);
        LogManager.resetConfiguration();
        LogManager.shutdown();
        PropertyConfigurator.configure(Constants.LOG4J_CONFIGURATION_FILE_PATH);
    }

    /**
     * Constructor
     */
    public JBehaveRunner() {
        super();

        configureLog4j(getScriptName());
        if (System.getProperty(Parameters.RUNNER_PARAMETER_PLATFORM_NAME) == null) {
            try {
                System.setProperty(Parameters.RUNNER_PARAMETER_PLATFORM_NAME,
                        ReadProperty.getProperty(PropertiesKeys.module, null));
            } catch (PropertyException e) {
            }
        }
        this.log = Logger.getLogger(JBehaveRunner.class.getName());
        configuration = new Configuration() {};

        CrossReference xref = new CrossReference();



        configuration.useFailureStrategy(new RethrowingFailure());
        configuration.useKeywords(new LocalizedKeywords());
        configuration.usePathCalculator(new AbsolutePathCalculator());
        configuration.useParameterControls(new ParameterControls());
        configuration.useParameterConverters(new ParameterConverters());
        configuration.useParanamer(new NullParanamer());
        configuration.usePendingStepStrategy(new PassingUponPendingStep());
        configuration.useStepCollector(new MarkUnmatchedStepsAsPending());
        configuration.useStepdocReporter(new PrintStreamStepdocReporter());
        configuration.useStepFinder(new StepFinder());
        configuration.useStepMonitor(xref.getStepMonitor());
        configuration.useStepPatternParser(new RegexPrefixCapturingPatternParser());
        configuration.useStoryControls(new StoryControls());
        configuration.useStoryLoader(new LoadFromClasspath());
        configuration.useStoryParser(new RegexStoryParser(configuration.keywords()));
        configuration.useStoryPathResolver(new UnderscoredCamelCaseResolver());

        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");
        viewResources.put("encoding", "UTF-8");
        URL url = null;
        try {
            url = new File(System.getProperty("user.dir") + "/target/jbehave").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        configuration
                .useStoryReporterBuilder(
                        new FSStoryReporterBuilder().withCodeLocation(url).withDefaultFormats()
                                .withPathResolver(
                                        new FilePrintStreamFactory.ResolveToPackagedName())
                                .withViewResources(viewResources).withFormats(CONSOLE, Format.HTML, Format.XML)
                                .withFailureTrace(true).withFailureTraceCompression(true)
                                .withCrossReference(xref));
        configuration.useViewGenerator(new FreemarkerViewGenerator());

    }

    @Override
    protected void initilize() throws ExecutionException {
        configureLog4j(getScriptName());
        if (System.getProperty(Parameters.RUNNER_PARAMETER_PLATFORM_NAME) == null) {
            throw new ExecutionException(
                    "The parameter 'platform' is requiered. Set platform in execution line or in property file");
        }
        embedder = configuredEmbedder();
        embedder.embedderControls().doBatch(false).doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(true).doIgnoreFailureInView(false).doSkip(false)
                .doVerboseFailures(false).doVerboseFiltering(false).useStoryTimeoutInSecs(5000)
                .useThreads(1);
        log.info("Jbehave capabilities is started.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jbehave.core.ConfigurableEmbedder#configuration()
     */
    @Override
    public Configuration configuration() {
        return configuration;
    }

    protected String name() {
        return "nomre";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jbehave.core.ConfigurableEmbedder#stepsFactory()
     */
    @Override
    public InjectableStepsFactory stepsFactory() {
        ApplicationContext context =
                new SpringApplicationContextFactory(Constants.SPRING_CONTEXT_FILE)
                        .createApplicationContext();
        return new SpringStepsFactory(configuration(), context);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jbehave.core.junit.JUnitStories#storyPaths()
     */
    protected List<String> storyPaths() {
        List<String> story = new ArrayList<String>();
        log.info("Add the story " + storyPath);
        story.add(storyPath);
        return story;
    }

    /**
     * @return the testName
     */
    public static String getScriptName() {
        return testName;
    }

    /**
     * @param testName the testName to set
     */
    public static void setTestName(String testName) {
        JBehaveRunner.testName = testName;
    }

}
