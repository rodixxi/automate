package com.harriague.automate.core.runner;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.jbehave.core.io.InvalidStoryResource;
import org.jbehave.core.io.LoadFromClasspath;

public class LoadFromClasspathUtf8 extends LoadFromClasspath {

    public LoadFromClasspathUtf8(Class<?> loadFromClass) {
        super(loadFromClass);
    }

    public LoadFromClasspathUtf8(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    public String loadResourceAsText(String resourcePath) {
        InputStream stream = resourceAsStream(resourcePath);
        try {
        	System.err.println("ISO-8859-1- " + IOUtils.toString(stream, "ISO-8859-1"));
        	System.err.println("UTF-8- " + IOUtils.toString(stream, "UTF-8"));
        	System.err.println("UTF-16- " + IOUtils.toString(stream, "UTF-16"));
        	
            return IOUtils.toString(stream, "UTF-16");
        } catch (IOException e) {
            throw new InvalidStoryResource(resourcePath, stream, e);
        }
    }
}