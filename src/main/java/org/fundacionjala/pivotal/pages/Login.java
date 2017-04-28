package org.fundacionjala.pivotal.pages;

import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.fundacionjala.pivotal.framework.util.PropertiesInfo;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.setWebElement;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;
import static org.fundacionjala.pivotal.framework.util.Constants.DASHBOARD_PAGE;
import static org.fundacionjala.pivotal.framework.util.PageTransporter.goToURL;

/**
 * The class represents the Pivotal Tracker
 * login page with all the needed elements.
 */
public class Login extends BasePage {

    private static final PropertiesInfo PROPERTIES_INFO = PropertiesInfo.getInstance();

    @FindBy(id = "credentials_username")
    private WebElement userNameTestField;

    @FindBy(id = "credentials_password")
    private WebElement passwordTestField;

    @FindBy(css = ".app_signin_action_button")
    private WebElement signInBtn;

    /**
     * Method that writes the username parameter
     * into the name component.
     *
     * @param username the entry to be used as username
     */
    public void setUserNameTestField(String username) {
        setWebElement(userNameTestField, username);
    }

    /**
     * Method that writes the password parameter
     * into the password component.
     *
     * @param password the entry to be used as password
     */
    public void setPasswordTestField(String password) {
        setWebElement(passwordTestField, password);
    }

    /**
     * Method that checks a series of validations to verify
     * that the login with given parameters has been accomplished.
     *
     * @param userName the username used to validate the logged account
     * @param password the password used to validate the logged account
     * @return the home user page instance of Pivotal Tracker
     */
    public static Dashboard loginAs(String userName, String password) {
        Dashboard dashboard = new Dashboard();
        if (!dashboard.getUserNameText().equalsIgnoreCase(dashboard.getUserName(userName))) {
            PivotalHome pivotalHome = new PivotalHome();
            Login login = pivotalHome.clickSingInLink();
            login.setUserNameTestField(userName);
            login.clickSignInButton();
            login.setPasswordTestField(password);
            return login.clickSignInButton();
        } else {
            goToURL(DASHBOARD_PAGE);
        }
        return dashboard;
    }

    /**
     * Method that click the sign in button.
     *
     * @return the Dashboard instance needed on Pivotal's
     * main user page.
     */
    public Dashboard clickSignInButton() {
        clickWebElement(signInBtn);
        return new Dashboard();
    }

    /**
     * Method that uses the loginAs method to log in
     * as a primary user.
     *
     * @return the dashboard given the email and the password
     */
    public static Dashboard loginAsPrimaryUser() {
        return loginAs(PROPERTIES_INFO.getEmail(),
                PROPERTIES_INFO.getPassword());
    }
}
