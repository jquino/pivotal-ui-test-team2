package org.fundacionjala.pivotal.framework.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This class initializes the Firefox Selenium Web Driver.
 */
public class Firefox implements IDriver {

    private static final String WEB_DRIVER_PATH = "drivers/geckodriver.exe";

    private static final String WEB_DRIVER_KEY = "webdriver.gecko.driver";

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver initDriver() {
        System.setProperty(WEB_DRIVER_KEY, WEB_DRIVER_PATH);
        return new FirefoxDriver();
    }
}
