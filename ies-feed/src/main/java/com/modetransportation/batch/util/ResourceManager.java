package com.modetransportation.batch.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import com.modetransportation.batch.exception.KeyNotFoundException;
import com.modetransportation.batch.exception.NotInitializedException;

public class ResourceManager {

	private final static String CONFIG_FILE_EXTENSION = ".properties";
	private final static String PROPERTIES_FILE = "properties.properties";
	private final static String NOT_INITIALIZED = "ResourceManager is not initialized.\n\r\tProperties Directory : ";
	private final static String PATH_SEPARATOR = "/";

	private Properties properties = null; // to store all the properties
	private static ResourceManager instance = null;

	private static String propertiesDirectory = null;

	private static String propertiesFile = null;

	// Cache for storing properties file
	private static HashMap propertiesCache = new HashMap();

	/**
	 * Load properties based on the given directory location and file name. If
	 * the file name is not passed, load all files under the directory.
	 * 
	 * @param propDir
	 *            absolute path of the directory containing the properties file.
	 * @param filename
	 *            name of the properties file.
	 */
	private ResourceManager(String propDir, String fileName) {
		propertiesDirectory = propDir;
		propertiesFile = fileName;
		properties = loadProperties();
	}

	/**
	 * This method initializes resource manager with all the properties under
	 * the given directory. If this method is called more than once properties
	 * do not get changed.
	 * 
	 * @param propDir
	 *            absolute path of the diretory containing all the properties
	 *            files.
	 * @exception A
	 *                runtime exception is thrown if properties could not be
	 *                loaded. In the event of any exception the calling
	 *                application should make sure that the application should
	 *                not continue to excecute.
	 */
	public static void init(String propDir) throws Exception {
		init(propDir, null);
	}

	/**
	 * Returns the boolean value to which the specified key is mapped in the
	 * properties.
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @return true if the given key is mapped to true; false if the key is not
	 *         mapped to any other values other than "true"; a run time
	 *         exception is thrown if the given key is not found in the
	 *         properties.
	 */
	public static boolean getBoolean(String keyName) {
		String str = instance.getString(keyName);
		return Boolean.valueOf(str).booleanValue();
	}

	/**
	 * Returns boolean value associated with key if available. Otherwise,
	 * returns the supplied default value
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @param defaultVal
	 *            default Value
	 */
	public static boolean getBoolean(String keyName, boolean defaultVal) {
		try {
			return getBoolean(keyName);
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * Returns the floating point value to which the specified key is mapped in
	 * the properties.
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @return the value to which the key is mapped in the properties; a run
	 *         time exception is thrown if the given key is not found or if the
	 *         key is mapped to a non-numeric value.
	 */
	public static float getFloat(String keyName) {
		String str = instance.getString(keyName);
		return Float.parseFloat(str);
	}

	/**
	 * Returns float value associated with key if available. Otherwise, returns
	 * the supplied default value
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @param defaultVal
	 *            default Value
	 */
	public static float getFloat(String keyName, float defaultVal) {
		try {
			return getFloat(keyName);
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * Returns the integer value to which the specified key is mapped in the
	 * properties.
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @return the value to which the key is mapped in the properties; a run
	 *         time exception is thrown if the given key is not found or if the
	 *         key is mapped to a non-numeric value.
	 */
	public static int getInt(String keyName) {
		String str = instance.getString(keyName);
		return Integer.parseInt(str);
	}

	/**
	 * Returns int value associated with key if available. Otherwise, returns
	 * the supplied default value
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @param defaultVal
	 *            default Value
	 */
	public static int getInt(String keyName, int defaultVal) {
		try {
			return getInt(keyName);
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * Returns the long integer value to which the specified key is mapped in
	 * the properties.
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @return the value to which the key is mapped in the properties; a run
	 *         time exception is thrown if the given key is not found or if the
	 *         key is mapped to a non-numeric value.
	 */
	public static long getLong(String keyName) {
		String str = instance.getString(keyName);
		return Long.parseLong(str);
	}

	/**
	 * Returns long value associated with key if available. Otherwise, returns
	 * the supplied default value
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @param defaultVal
	 *            default Value
	 */
	public static long getLong(String keyName, long defaultVal) {
		try {
			return getLong(keyName);
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * Returns the double value to which the specified key is mapped in the
	 * properties.
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @return the value to which the key is mapped in the properties; a run
	 *         time exception is thrown if the given key is not found or if the
	 *         key is mapped to a non-numeric value.
	 */
	public static double getDouble(String keyName) {
		String str = instance.getString(keyName);
		return Double.parseDouble(str);
	}

	/**
	 * Returns double value associated with key if available. Otherwise, returns
	 * the supplied default value
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @param defaultVal
	 *            default Value
	 */
	public static double getDouble(String keyName, double defaultVal) {
		try {
			return getDouble(keyName);
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * Get the instance of the ResourceManager
	 */
	private static ResourceManager getInstance() {
		return instance;
	}

	/**
	 * Returns the clone of application properties being used by this component.
	 * Throws a runtime exception if the singleton object is not initialized.
	 * 
	 * @return clone of application properties.
	 */
	public static Properties getProperties() {
		if (!isInitialized()) {
			throw new NotInitializedException(NOT_INITIALIZED
					+ propertiesDirectory);
		}
		return (Properties) instance.properties.clone();
	}

	/**
	 * Returns the string value to which the specified key is mapped in the
	 * properties.
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @return the value to which the key is mapped in the properties; throws a
	 *         run time exception if the given if the key is not found in the
	 *         properties.
	 */
	public static String getString(String keyName) {
		if (!isInitialized()) {
			throw new NotInitializedException(NOT_INITIALIZED
					+ propertiesDirectory);
		}

		String str = (String) instance.properties.get(keyName);
		if (str == null) {
			throw new KeyNotFoundException("ResourceManager:Key not found - \""
					+ keyName + "\"");
		}
		return str;
	}

	/**
	 * Returns String value associated with key if available. Otherwise, returns
	 * the supplied default value
	 * 
	 * @param keyName
	 *            a key in the properties.
	 * @param defaultVal
	 *            default Value
	 */
	public static String getString(String keyName, String defaultVal) {
		try {
			return getString(keyName);
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * This method initializes resource manager with the given file name under
	 * the given directory. If this method is called more than once properties
	 * do not get changed.
	 * 
	 * @param propDir
	 *            absolute path of the diretory containing all the given file.
	 * @param fileName
	 *            file name to be loaded for the properties.
	 * @exception A
	 *                runtime exception is thrown if properties could not be
	 *                loaded. In the event of any exception the calling
	 *                application should make sure that the application should
	 *                not continue to excecute.
	 */
	public static void init(String propDir, String fileName) throws Exception {
		if (instance == null) {
			instance = new ResourceManager(propDir, fileName);

			// check if properties are intialized
			if (!isInitialized()) {
				throw new NotInitializedException(NOT_INITIALIZED
						+ propertiesDirectory);
			}
		}
	}

	/**
	 * Checks to see if the given key is present in the list of properties.
	 * 
	 * @param keyName
	 *            key to search.
	 * @return true if found, false if not.
	 */
	public static boolean isFound(String keyName) {
		boolean found = false;
		try {
			found = (getString(keyName) != null);
		} catch (KeyNotFoundException e) {
			// do nothing
		}
		return found;
	}

	/**
	 * Tests to see if resource manager is initialized.
	 * 
	 * @return true if this component is intialized, else false.
	 */
	public static boolean isInitialized() {
		return ((instance != null) && (instance.properties != null) && !instance.properties
				.isEmpty());
	}

	/**
	 * Reset resource manager.
	 * 
	 * ONLY TO BE USED WHEN TESTING
	 * 
	 * Typically used when multiple tests must be run from within JUnit. In such
	 * cases, if each test has its own properties file, the ResourceManager must
	 * be reset by each test case so that the properties associated with the
	 * test will be loaded. Typical usage: ResourceManager.init("d:\temp",
	 * "emailmanagertest"); ResourceManager.reset("d:\temp",
	 * "emailmanagertest"); The init() will make sure that the ResourceManager
	 * is initialized. The reset() will reset the properties attribute of the
	 * ResourceManager.
	 */
	public static void reset(String propDir, String fileName) throws Exception {
		init(propDir, fileName);
		propertiesDirectory = propDir;
		propertiesFile = fileName;
		instance.properties = instance.loadProperties();
	}

	/**
	 * Loads properties from all the files in the directory specified to the
	 * constructor. Also, add file name and directory path in hash table
	 * 
	 * @return most recent application properties.
	 * @exception generic
	 *                exception.
	 */
	private Properties loadProperties() {
		Properties p = null;
		Properties p1 = null;

		// Attempt to load properties from resource stream
		// If that fails, then load from file
		p = loadPropertiesFromResourceStream();
		if (p == null)
			p = loadPropertiesFromFile();

		return p;
	}

	/** */
	private Properties loadPropertiesFromFile() {
		Properties p = new Properties();
		File file = null;

		if (propertiesFile == null)
			file = new File(propertiesDirectory);
		else
			file = new File(propertiesDirectory, propertiesFile.trim()
					+ CONFIG_FILE_EXTENSION);

		if (!file.exists()) {
			if (propertiesFile == null)
				System.err.println("ResourceManager - invalid directory name: "
						+ propertiesDirectory);
			else
				System.err.println("ResourceManager - invalid directory name: "
						+ propertiesDirectory + " and/or file name "
						+ propertiesFile);
		}

		File[] files = null;

		// load properties from the files under given directory
		if (!file.isDirectory()) {
			files = new File[1];
			files[0] = file;
		} else
			files = file.listFiles();

		// In the event of any exception during loading properties retain
		// original properties.
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()
					&& files[i].toString().endsWith(CONFIG_FILE_EXTENSION)) {
				BufferedInputStream bis = null;
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(files[i]);
					bis = new BufferedInputStream(fis);
					p.load(bis);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (fis != null)
							fis.close();
					} catch (Exception e) { /* Ignore */
					}
					try {
						if (bis != null)
							bis.close();
					} catch (Exception e) { /* Ignore */
					}
				}
			}
		}

		return p;
	}

	/**
	 * This class loads properties from a specific file if propertiesFile is
	 * specified. Otherwise it looks for a file called properties.properties and
	 * load all properties files listed in this file.
	 */
	private Properties loadPropertiesFromResourceStream() {
		Properties p = null;
		String path = null;

		try {
			if (propertiesFile == null) {
				path = propertiesDirectory + "/" + PROPERTIES_FILE;
				InputStream is = this.getClass().getResourceAsStream(path);
				if (is != null) {
					p = new Properties();
					Properties tempProp = new Properties();
					InputStream tempIs = null;
					tempProp.load(is);
					Iterator iter = tempProp.values().iterator();
					while (iter.hasNext()) {
						String prop = (String) iter.next();
						tempIs = this.getClass().getResourceAsStream(
								propertiesDirectory + "/" + prop);
						if (tempIs != null)
							p.load(tempIs);
						tempIs.close();
					}
					is.close();
				}
			} else {
				path = propertiesDirectory + "/" + propertiesFile.trim()
						+ CONFIG_FILE_EXTENSION;
				InputStream is = this.getClass().getResourceAsStream(path);
				if (is != null) {
					p = new Properties();
					p.load(is);
					is.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}

	/**
	 * Method to test this component. This method expects one command line
	 * argument and that is the absolute path of the directory from where
	 * properties are to be read.
	 * 
	 * Test Procedure: Create test properties file with any name of your choice
	 * with the following 3 keys:
	 * 
	 * resource.autoreload.enabled=false resource.autoreload.refreshtime=15000
	 * 
	 * string.value=test value boolean.value1=junk boolean.value2=true
	 * integer.value1= integer.value2=100 float.value1=12abc.00
	 * float.value2=123.45 prop.reload.test = 10
	 * 
	 * Run this program passing it location of the test properties file. You see
	 * the following output for the given keys:
	 * 
	 * OUTPUT REASON
	 * 
	 * List of all properties (name-value pairs) under the given directory.
	 * 
	 * test value value of he key "string.value" false boolean equivalent of key
	 * "boolean.value1" true boolean equivalent of key "boolean.value2"
	 * java.lang.NumberFormatException invalid integer value of key
	 * "integer.value1" 100 integer value of key "integer.value2"
	 * java.lang.NumberFormatException invalid numeric value of key
	 * "float.value1" 123.45 float value of key "float.value2"
	 * java.lang.RuntimeException tries to read the value of key
	 * ""iamnotakey.value" which is not present in the properties file.
	 * 
	 * 
	 * Output of the program will be followed by the key-value pair of the key
	 * "prop.reload.test": prop.reload.test = 10 prop.reload.test = 10
	 * 
	 * Now open the properties file and set the property prop.reload.test to 20.
	 * You will see same output as above for the key "prop.reload.test". For
	 * simplicity values of other keys is not printed. This is because the
	 * property resource.autoreload.enabled is still false.
	 * 
	 * Now open the properties file again and set resource.autoreload.enabled to
	 * true. After few seconds you will start seeing the ouput as below for the
	 * key "prop.reload.test": prop.reload.test = 20 prop.reload.test = 20
	 * 
	 * If you want to stop reloading properties set resource.autoreload.enabled
	 * to false. You can always go back and set resource.autoreload.enabled to
	 * true to start reloading properties at any time without having to restart
	 * the applications.
	 * 
	 * Pass an invalid directory in the command line argument to test the init
	 * method
	 * 
	 * @param args
	 *            [] array of command line arguments.
	 */
	public static void main(String[] args) {
		if (args.length < 1 || args.length > 2) {
			System.out
					.println("Usage: java ResourceManager <<properties directory>>");
			System.out
					.println("       java ResourceManager <<properties directory>> <<file name>>");
			System.exit(0);
		}

		// Initialize the resource manager
		try {

			ResourceManager.init(args[0], null);
			ResourceManager.getProperties().list(System.out);

			System.out.println(ResourceManager.getString("string.value"));
			System.out.println(ResourceManager.getBoolean("boolean.value1"));
			System.out.println(ResourceManager.getBoolean("boolean.value2"));
			try {
				System.out.println(ResourceManager.getInt("integer.value1"));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			System.out.println(ResourceManager.getInt("integer.value2"));
			try {
				System.out.println(ResourceManager.getFloat("float.value1"));
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			System.out.println();
			System.out.println(ResourceManager.getFloat("float.value2"));

			try {
				System.out
						.println(ResourceManager.getFloat("iamnotakey.value"));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
