package org.fundacionjala.pivotal.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Class that obtains the properties defined that will
 * be filled inside the properties file and then to be
 * used on automated tests.
 */
public final class PropertiesInfo {

    private static final Logger LOGGER = Logger.getLogger(PropertiesInfo.class.getSimpleName());

    private static final String CONFIG_PROPERTIES = "gradle.properties";

    private static final String PROXY_PORT = "proxyPort";

    private static final String PROXY_HOST = "proxyHost";

    private static final String REMOTE_TEST_NAME = "remoteTestName";

    private static final String EMAIL = "email";

    private static final String ACCESS_SECURITY = "password";

    private static final String URL = "url";

    private static final String URL_API = "urlApi";

    private static final String API_TOKEN = "apiToken";

    private static final String REMOTE_ACCESS_KEY = "remoteAccessKey";

    private static final String REMOTE_USER = "remoteUser";

    private static final String BROWSER = "browser";

    private static final String REMOTE_BROWSER = "remoteBrowser";

    private static final String REMOTE_BROWSER_VERSION = "remoteBrowserVersion";

    private static final String REMOTE_PLATFORM = "remotePlatform";

    private static final String REMOTE_TEST_HOST_URL = "remoteTestHostUrl";

    private static PropertiesInfo instance;

    private Properties properties;

    /**
     * Class constructor.
     */
    private PropertiesInfo() {
        loadProperties();
    }

    /**
     * It gets the instance of the properties info object.
     *
     * @return the object instance
     */
    public static PropertiesInfo getInstance() {
        if (instance == null) {
            instance = new PropertiesInfo();
        }
        return instance;
    }

    /**
     * This method is used to load the .&nbsp;properties file
     * which contains the configurations for the project.
    */
    private void loadProperties() {
        try {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(CONFIG_PROPERTIES);
                properties = new Properties();
                properties.load(fileInputStream);
            } finally {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.warn("The properties file couldn't be found", e);
        } catch (IOException e) {
            LOGGER.warn("A problem of type", e);
        }
    }

    /**
     * Method that returns the properties of a given value.
     *
     * @param propertyKey the element to check if is present
     * @return the values of the properties
     */
    public String getProperty(String propertyKey) {
        String propertyValue = System.getProperty(propertyKey);
        if (propertyValue == null) {
            propertyValue = properties.getProperty(propertyKey);
        }
        return propertyValue;
    }

    /**
     * Method that returns the email property.
     *
     * @return the email property
     */
    public String getEmail() {
        return getProperty(EMAIL);
    }

    /**
     * Method that returns the password property.
     *
     * @return the password property
     */
    public String getPassword() {
        return getProperty(ACCESS_SECURITY);
    }

    /**
     * Method that returns the url property.
     *
     * @return the url property
     */
    public String getUrl() {
        return getProperty(URL);
    }

    /**
     * Method that returns the url of the API property.
     *
     * @return url of the API
     */
    public String getUrlApi() {
        return getProperty(URL_API);
    }

    /**
     * Method that returns the API token property.
     *
     * @return the API token required property
     */
    public String getApiToken() {
        return getProperty(API_TOKEN);
    }

    /**
     * Method that returns the remote user property.
     *
     * @return the required remote user property
     */
    public String getRemoteUser() {
        return getProperty(REMOTE_USER);
    }

    /**
     * Method that returns the remote access key property.
     *
     * @return the required remote access key property
     */
    public String getRemoteAccessKey() {
        return getProperty(REMOTE_ACCESS_KEY);
    }

    /**
     * Method that returns the browser property.
     *
     * @return the required browser property
     */
    public String getBrowser() {
        return getProperty(BROWSER);
    }

    /**
     * Method that returns the remote browser property.
     *
     * @return the required remote browser property
     */
    public String getRemoteBrowser() {
        return getProperty(REMOTE_BROWSER);
    }

    /**
     * Method that returns the remote browser version property.
     *
     * @return the required remote browser version property
     */
    public String getBrowserVersion() {
        return getProperty(REMOTE_BROWSER_VERSION);
    }

    /**
     * Method that returns the platform property.
     *
     * @return the required platform property
     */
    public String getPlatform() {
        return getProperty(REMOTE_PLATFORM);
    }

    /**
     * Method that returns the proxy property.
     *
     * @return the required proxy property
     */
    public String getProxy() {
        return !getProxyHost().isEmpty() ? String.format("http://%s:%s", getProxyHost(), getProxyPort()) : null;
    }

    /**
     * Method that returns the proxy host property.
     *
     * @return the required proxy host property
     */
    public String getProxyHost() {
        return getProperty(PROXY_HOST);
    }

    /**
     * Method that returns the proxy port property.
     *
     * @return the required proxy port property
     */
    public String getProxyPort() {
        return getProperty(PROXY_PORT);
    }

    /**
     * Method that returns the remote test name property.
     *
     * @return the required remote test name property
     */
    public String getRemoteTestName() {
        return getProperty(REMOTE_TEST_NAME);
    }

    /**
     * Method that returns the remote test host url property.
     *
     * @return the required remote test host url property
     */
    public String getRemoteTestHostUrl() {
        return getProperty(REMOTE_TEST_HOST_URL);
    }
}
