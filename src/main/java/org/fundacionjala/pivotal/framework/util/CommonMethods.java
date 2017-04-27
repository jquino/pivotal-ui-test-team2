package org.fundacionjala.pivotal.framework.util;

import java.util.ArrayList;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.fundacionjala.pivotal.framework.selenium.DriverManager;
import org.fundacionjala.pivotal.pages.accounts.AccountSetting;
import org.fundacionjala.pivotal.pages.accounts.Accounts;
import org.fundacionjala.pivotal.pages.accounts.CreateAccountForm;

import static org.fundacionjala.pivotal.api.RequestManager.deleteRequest;
import static org.fundacionjala.pivotal.api.RequestManager.getRequest;
import static org.fundacionjala.pivotal.framework.selenium.DriverManager.getInstance;
import static org.fundacionjala.pivotal.framework.util.Constants.ATTRIBUTE_ID;
import static org.fundacionjala.pivotal.framework.util.Constants.PROJECTS_ENDPOINT;
import static org.fundacionjala.pivotal.framework.util.Constants.WORKSPACES_ENDPOINT;


/**
 * This class groups the generic methods.
 */
public final class CommonMethods {

    private static final WebDriverWait WEB_DRIVER_WAIT = getInstance().getWait();

    private static final Logger LOGGER = Logger.getLogger(CommonMethods.class.getSimpleName());

    /**
     * Class constructor.
     */
    private CommonMethods() {
    }

    /**
     * This Method return false o true if the element is present.
     *
     * @param webElement the web element to be verified
     * @return true or false
     */
    public static boolean isElementPresent(WebElement webElement) {
        try {
            webElement.getTagName();
            return true;
        } catch (WebDriverException e) {
            LOGGER.error("Web element not found", e);
            return false;

        }
    }

    /**
     * This method sets the Web Element.
     *
     * @param webElement the object to be catched and the replaced
     * @param text the value that will be filled on the web element
     */
    public static void setWebElement(WebElement webElement, String text) {
        WEB_DRIVER_WAIT.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * This Method do clicks the element.
     *
     * @param webElement the object to be clicked.
     */
    public static void clickWebElement(WebElement webElement) {
        WEB_DRIVER_WAIT.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * This Method sets the state of
     * the checkbox element.
     *
     * @param webElement the checkbox to be set
     * @param enable the state to be configured
     */
    public static void setCheckBox(WebElement webElement, boolean enable) {
        if (enable) {
            unCheckBox(webElement);
        } else {
            checkBox(webElement);
        }
    }

    /**
     * This Method sets the unchecked state to
     * the checkbox element.
     *
     * @param webElement the checkbox to be unchecked
     */
    private static void unCheckBox(WebElement webElement) {
        if (!webElement.isSelected()) {
            webElement.click();
        }
    }

    /**
     * This Method sets the checked state to
     * the checkbox element.
     *
     * @param webElement the checkbox to be checked
     */
    private static void checkBox(WebElement webElement) {
        if (webElement.isSelected()) {
            webElement.click();
        }
    }

    /**
     * Selects one element present on the list.
     *
     * @param webElementSelect the web element to be selected
     * @param element the value type to be selected
     */
    public static void selectAElementComboBox(WebElement webElementSelect, String element) {
        Select oSelect = new Select(webElementSelect);
        oSelect.selectByValue(element);
    }

    /**
     * This method converts a Select element.
     *
     * @param webElement the element to be converted
     * @return the selected element
     */
    public static Select convertASelect(WebElement webElement) {
        return new Select(webElement);
    }

    /**
     * Deletes all Projects created by the API.
     */
    public static void deleteAllProjects() {
        ArrayList<Map<String, ?>> jsonAsArrayList = JsonPath.from(getRequest(PROJECTS_ENDPOINT).asString()).get("");
        if (jsonAsArrayList.isEmpty()) {
            for (Map<String, ?> object : jsonAsArrayList) {
                deleteRequest(PROJECTS_ENDPOINT + object.get(ATTRIBUTE_ID).toString());
            }
        }
    }

    /**
     * Deletes all Project workspace created by the API.
     */
    public static void deleteAllWorkspaces() {
        ArrayList<Map<String, ?>> jsonAsArrayList = JsonPath.from(getRequest(WORKSPACES_ENDPOINT).asString()).get("");
        if (jsonAsArrayList.isEmpty()) {
            for (Map<String, ?> object : jsonAsArrayList) {
                deleteRequest(WORKSPACES_ENDPOINT + object.get(ATTRIBUTE_ID).toString());
            }
        }
    }

    /**
     * Deletes all accounts created by the API.
     */
    public static void deleteAccounts() {
        DriverManager.getInstance().getDriver().get("https://www.pivotaltracker.com/accounts");
        Accounts accounts = new Accounts();
        try {
            while (isElementPresent(accounts.getManageAccountBtn())) {
                AccountSetting accountSetting = accounts.manageAccount().clickSettingTab();
                accounts = accountSetting.deleteAccount();
            }
            CreateAccountForm createAccountForm = accounts.clickNewAccountBtn();
            createAccountForm.setAccountNameTextField("SYSTEM");
            createAccountForm.clickCreateAccountBtn();
            DriverManager.getInstance().getDriver().get("https://www.pivotaltracker.com/dashboard");
        } catch (NullPointerException e) {
            LOGGER.error("Element null", e);
            throw new NoSuchElementException("Element not found");
        }
        DriverManager.getInstance().getDriver().get("https://www.pivotaltracker.com/dashboard");
    }

    /**
     * This method closes the browser used.
     * @param message the string to be displayed
     */
    public static void quitProgram(String message) {
        LOGGER.info("Element null " + message);
        Runtime.getRuntime().runFinalization();
    }
}
