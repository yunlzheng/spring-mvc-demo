package org.phenix.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertiesConfiguration extends PropertyPlaceholderConfigurer {
	private final static Map<String, String> DICT = new HashMap<String, String>();
	private final static String NULL_STRING_MESSAGE = "For input string: null";

	@Override
	protected void processProperties(ConfigurableListableBeanFactory factory, Properties props) throws BeansException {
		super.processProperties(factory, props);

		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(placeholderPrefix, placeholderSuffix, valueSeparator, ignoreUnresolvablePlaceholders);

		for (Object key : props.keySet()) {
			String k = key.toString();
			String v = props.getProperty(k);
			String r = helper.replacePlaceholders(v, props);

			DICT.put(k, r);
		}
	}

	/**
	 * <p>
	 * Returns the value of the certain named property in a string type.
	 * </p>
	 * 
	 * @param name
	 *            The property name, cannot be blank.
	 * 
	 * @return The string, or null if the named property does not exist.
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown when the "name" is blank.
	 */
	public static String getStringValue(String name) throws IllegalArgumentException {
		Validate.isTrue(StringUtils.isNotBlank(name), "The 'name' is blank");

		return DICT.get(name);
	}

	/**
	 * <p>
	 * Returns the value of the certain named property in a long type.
	 * </p>
	 * 
	 * @param name
	 *            The property name, cannot be blank.
	 * 
	 * @return The long.
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown when the "name" is blank.
	 * @throws NumberFormatException
	 *             Thrown when the value cannot be parsed into a long.
	 */
	public static long getLongValue(String name) throws IllegalArgumentException, NumberFormatException {

		try {
			String value = getStringValue(name);

			return Long.parseLong(value);

		} catch (NumberFormatException ex) {

			if ("null".equals(ex.getMessage())) {
				throw new NumberFormatException(NULL_STRING_MESSAGE);
			}

			throw ex;
		}
	}

	/**
	 * <p>
	 * Returns the value of the certain named property in an int type.
	 * </p>
	 * 
	 * @param name
	 *            The property name, cannot be blank.
	 * 
	 * @return The int.
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown when the "name" is blank.
	 * @throws NumberFormatException
	 *             Thrown when the value cannot be parsed into an int.
	 */
	public static int getIntValue(String name) throws IllegalArgumentException, NumberFormatException {
		return (int) getLongValue(name);
	}

	/**
	 * <p>
	 * Returns the value of the certain named property in a short type.
	 * </p>
	 * 
	 * @param name
	 *            The property name, cannot be blank.
	 * 
	 * @return The short.
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown when the "name" is blank.
	 * @throws NumberFormatException
	 *             Thrown when the value cannot be parsed into a short.
	 */
	public static short getShortValue(String name) throws IllegalArgumentException, NumberFormatException {
		return (short) getLongValue(name);
	}

	/**
	 * <p>
	 * Returns the value of the certain named property in a byte type.
	 * </p>
	 * 
	 * @param name
	 *            The property name, cannot be blank.
	 * 
	 * @return The byte.
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown when the "name" is blank.
	 * @throws NumberFormatException
	 *             Thrown when the value cannot be parsed into a byte.
	 */
	public static byte getByteValue(String name) throws IllegalArgumentException, NumberFormatException {
		return (byte) getLongValue(name);
	}

	/**
	 * <p>
	 * Returns the value of the certain named property in a double type.
	 * </p>
	 * 
	 * @param name
	 *            The property name, cannot be blank.
	 * 
	 * @return The double.
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown when the "name" is blank.
	 * @throws NumberFormatException
	 *             Thrown when the value cannot be parsed into a double.
	 */
	public static double getDoubleValue(String name) throws IllegalArgumentException, NumberFormatException {

		try {
			String value = getStringValue(name);

			return Double.parseDouble(value);

		} catch (NullPointerException ex) {
			throw new NumberFormatException(NULL_STRING_MESSAGE);
		}
	}

	/**
	 * <p>
	 * Returns the value of the certain named property in a float type.
	 * </p>
	 * 
	 * @param name
	 *            The property name, cannot be blank.
	 * 
	 * @return The float.
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown when the "name" is blank.
	 * @throws NumberFormatException
	 *             Thrown when the value cannot be parsed into a float.
	 */
	public static float getFloatValue(String name) throws IllegalArgumentException, NumberFormatException {
		return (float) getDoubleValue(name);
	}

	/**
	 * <p>
	 * Returns the value of the certain named property in a boolean type.
	 * </p>
	 * <p>
	 * Only string "true" (case insensitive) returns true, otherwise a false
	 * returns.
	 * </p>
	 * 
	 * @param name
	 *            The property name, cannot be blank.
	 * 
	 * @return The boolean.
	 * 
	 * @throws IllegalArgumentException
	 *             Thrown when the "name" is blank.
	 */
	public static boolean getBooleanValue(String name) throws IllegalArgumentException {
		String value = getStringValue(name);

		return "true".equalsIgnoreCase(value);
	}
}
