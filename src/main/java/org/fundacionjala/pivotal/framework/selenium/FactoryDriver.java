package org.fundacionjala.pivotal.framework.selenium;

import org.openqa.selenium.WebDriverException;

/**
 * This class handles the logic related to what
 * kind of browser will be used in the test.
 */
public final class FactoryDriver {

    private static final String BROWSER_NOT_FOUND_MSG = "Browser not found.";

    /**
     * Class constructor.
     */
    private FactoryDriver() {
    }

    /**
     * Method that returns the subclasses depending
     * of the type of browser that will be used.
     * @param browser the value that contains the browser name designated
     * @return the subclass instance created
     */
    public static IDriver getDriver(String browser) {
        switch (Browser.valueOf(browser.toUpperCase())) {
            case CHROME:
                return new Chrome();
            case FIREFOX:
                return new Firefox();
            case BROWSERSTACK:
                return new BrowserStack();
            case SAUCELABS:
                return new SauceLabs();
            default:
                throw new WebDriverException(BROWSER_NOT_FOUND_MSG);
        }
    }
}
