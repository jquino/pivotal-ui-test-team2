package org.fundacionjala.pivotal.pages.project;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;
import static org.fundacionjala.pivotal.framework.util.Constants.IMPLICIT_FAIL_WAIT_TIME;
import static org.fundacionjala.pivotal.framework.util.Constants.IMPLICIT_PROJECT_WAIT;
import static org.fundacionjala.pivotal.framework.util.Constants.WAIT_TIME;

/**
 * Class that represents the project main page.
 */
public class Project extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(Project.class.getName());

    @FindBy(className = "tc_context_name")
    private WebElement projectName;

    @FindBy(css = "[data-aid='navTab-settings']")
    private WebElement settings;

    private static final int TIMEOUT = 45;

    /**
     * Method that clicks the Settings tab
     * and returns the Settings instance.
     *
     * @return the Settings instance
     */
    public ProjectSettings clickSettingTab() {
        try {
            wait.withTimeout(TIMEOUT, TimeUnit.SECONDS);
            clickWebElement(settings);
        } catch (NoSuchElementException e) {
            LOGGER.warn("The click tab Setting Element could not be found", e);
            throw new NoSuchElementException("Setting was not found", e);
        } catch (NullPointerException e) {
            LOGGER.error("Setting was not found", e);
            throw new NullPointerException();
        } finally {
            wait.withTimeout(WAIT_TIME, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(IMPLICIT_FAIL_WAIT_TIME, TimeUnit.SECONDS);
        }
        return new ProjectSettings();
    }

    /**
     * Method that verifies that the project title
     * is displayed or not.
     *
     * @return the boolean value for the title displayed
     */
    public boolean isProjectTitleDisplayed() {
        boolean projectTitleDisplayed = false;
        try {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_PROJECT_WAIT, TimeUnit.SECONDS);

            projectTitleDisplayed = projectName.isDisplayed();
        } catch (NoSuchElementException e) {
            LOGGER.warn("The Element could not be found", e);
        } finally {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_FAIL_WAIT_TIME, TimeUnit.SECONDS);

        }
        return projectTitleDisplayed;
    }

    /**
     * Method that retrieves the project title.
     *
     * @return the project title value
     */
    public String getTitle() {
        String projectTitle = "";
        try {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_PROJECT_WAIT, TimeUnit.SECONDS);
            projectTitle = projectName.getText();
        } catch (NoSuchElementException e) {
            LOGGER.warn("The element could not be found", e);
        } finally {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_FAIL_WAIT_TIME, TimeUnit.SECONDS);
        }
        return projectTitle;
    }
}
