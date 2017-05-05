package org.fundacionjala.pivotal.framework.selenium;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.fundacionjala.pivotal.framework.util.PropertiesInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.fundacionjala.pivotal.framework.util.Constants.IMPLICIT_WAIT_TIME;
import static org.fundacionjala.pivotal.framework.util.Constants.LOAD_PAGE_TIME;
import static org.fundacionjala.pivotal.framework.util.Constants.WAIT_TIME;

/**
 * This class provides basic methods to manage
 * the Selenium driver and initialize the
 * logger main resources path.
 */
public final class DriverManager {

    private static final String SRC_MAIN_RESOURCES_LOG4J_PROPERTIES = "src/main/resources/log4j.properties";

    private static DriverManager instance;

    private WebDriver driver;

    private WebDriverWait wait;

    /**
     * Class constructor.
     */
    private DriverManager() {
        initWebDriver();
        PropertyConfigurator.configure(SRC_MAIN_RESOURCES_LOG4J_PROPERTIES);
    }

    /**
     * This method gathers the instance
     * of the web driver needed.
     * @return the the DriverManager object instance
     */
    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    /**
     * Method that initializes the time waits for the
     * web driver.
     */
    private void initWebDriver() {
        driver = FactoryDriver.getDriver(PropertiesInfo.getInstance().getBrowser().toUpperCase()).initDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(LOAD_PAGE_TIME, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, WAIT_TIME);
    }

    /**
     * It gathers the driver needed.
     * @return the instance driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Finishes the instance driver.
     */
    public void quitDriver() {
        driver.quit();
    }

    /**
     * It gathers the driver timeout configured.
     * @return the instance timeout
     */
    public WebDriverWait getWait() {
        return wait;
    }
}
