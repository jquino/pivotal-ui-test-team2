package org.fundacionjala.pivotal.framework.selenium;

import org.openqa.selenium.WebDriverException;

/**
 * This class handles the logic related to what
 * kind of browser will be used in the test.
 */
public final class FactoryDriver {

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
            case REMOTE:
                return new RemoteBrowser();
            default:
                throw new WebDriverException("Browser not found : " + browser);
        }
    }
}
