package com.harriague.automate.core.tests;

import org.junit.Assert;
import org.junit.Test;

import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.test.CoreTestCaseBase;
import com.harriague.automate.core.utils.ReadProperty;

/**
 * Test of the read property
 */
public class ReadPropertyTest extends CoreTestCaseBase {
	
	/**
	 * Key to find
	 */
	private final String KEY = "read.property";

	/**
	 * Test method
	 * @throws PropertyException
	 */
	@Test
	public void test() throws PropertyException {
		configureLog4j();
		Assert.assertTrue("Can not find property", ReadProperty.isPropertyExist(KEY));
		Assert.assertNotNull("Can not read property", ReadProperty.getProperty(KEY));
	}
}
