package org.fundacionjala.pivotal.pages;

import org.fundacionjala.pivotal.framework.util.PropertiesInfo;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;

/**
 * Class that represents the main page of Pivotal Tracker
 * in the case that a user is not logged yet so he can click
 * the sign in button.
 */
public class PivotalHome extends BasePage {

    @FindBy(css = ".btn-login")
    private WebElement signInLink;

    /**
     * Class constructor.
     */
    public PivotalHome() {
        driver.get(PropertiesInfo.getInstance().getUrl());
    }

    /**
     * Method that clicks the sign in button
     * and creates a new Login instance.
     *
     * @return the Login page instance
     */
    public Login clickSingInLink() {
        clickWebElement(signInLink);
        return new Login();
    }
}
