package org.fundacionjala.pivotal.pages.dashboard;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.api.RequestManager;
import org.fundacionjala.pivotal.pages.BasePage;
import org.fundacionjala.pivotal.pages.accounts.Accounts;
import org.fundacionjala.pivotal.pages.project.CreateProject;
import org.fundacionjala.pivotal.pages.project.Project;
import org.fundacionjala.pivotal.pages.setting.Setting;
import org.fundacionjala.pivotal.pages.workspace.CreateWorkspace;
import org.fundacionjala.pivotal.pages.workspace.Workspace;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.clickWebElement;
import static org.fundacionjala.pivotal.framework.util.Constants.IMPLICIT_FAIL_WAIT_TIME;
import static org.fundacionjala.pivotal.framework.util.Constants.IMPLICIT_WAIT_TIME;
import static org.fundacionjala.pivotal.framework.util.Constants.WAIT_TIME;

/**
 * This class represents the Dashboard page.
 */
public class Dashboard extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(Dashboard.class.getName());

    private static final String CREATE_WORKSPACE_BUTTON_WAS_NOT_FOUND_MSG = "Create Workspace Button was not found";
    private static final String CREATE_PROJECT_BUTTON_WAS_NOT_FOUND_MSG = "Create Project Button was not found";

    @FindBy(css = ".tc_profile_dropdown .tc_dropdown_name")
    private WebElement userNameText;

    @FindBy(css = "a[href='/accounts']")
    private WebElement accountOption;

    @FindBy(id = "create_new_project_button")
    private WebElement createProjectButton;

    @FindBy(css = "[data-aid = 'create-workspace-button']")
    private WebElement createWorkspaceButton;

    @FindBy(css = "li[class='notice']")
    private WebElement deleteMessageText;

    @FindBy(id = "notice")
    private WebElement messageDeleteWorkspace;

    @FindBy(id = "my_workspaces")
    private WebElement workspaceContainer;

    @FindBy(xpath = "//span[text() = 'Projects']")
    private WebElement projectsTab;

    @FindBy(xpath = "//span[text() = 'Workspaces']")
    private WebElement workspacesTab;

    private final String workspaceNameLocator = "//a[text() = '%s']";

    private static final int POLLING = 5;

    /**
     * Method that changes the timeout and clicks on Create Project or
     * Workspace button and restore to default timeout.
     * @param webElement Create Project or Workspace button web element.
     * @param errorMessage Error message when the web element was not found.
     */
    private void clickOnCreateProjectOrWorkspaceButton(WebElement webElement, String errorMessage) {
        try {
            wait.withTimeout(WAIT_TIME, SECONDS);
            clickWebElement(webElement);
        } catch (NoSuchElementException e) {
            LOGGER.warn(errorMessage, e);
            throw new NoSuchElementException(errorMessage, e);
        } finally {
            wait.withTimeout(WAIT_TIME, SECONDS);
        }
    }

    /**
     * Method that retrieves the web element when
     * a project have been removed.
     * @return the web element.
     */
    public WebElement getDeleteMessageText() {
        return deleteMessageText;
    }

    /**
     * Method that clicks the link to create a project
     * and we retrieve the new create project instance.
     *
     * @return the CreateReport instance
     */
    public CreateProject clickCreateProjectButton() {
        clickOnCreateProjectOrWorkspaceButton(createProjectButton, CREATE_PROJECT_BUTTON_WAS_NOT_FOUND_MSG);
        return new CreateProject();
    }

    /**
     * Method that clicks the link to create a new workspace
     * and we retrieve a new workspace instance.
     *
     * @return the CreateWorkspace instance
     */
    public CreateWorkspace clickCreateWorkspaceButton() {
        clickOnCreateProjectOrWorkspaceButton(createWorkspaceButton, CREATE_WORKSPACE_BUTTON_WAS_NOT_FOUND_MSG);
        return new CreateWorkspace();
    }

    /**
     * Method that retrieves the user name text.
     *
     * @return the user name text
     */
    public String getUserNameText() {
        String userName = "";
        try {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_FAIL_WAIT_TIME, SECONDS);
            userName = userNameText.getText();
        } catch (NoSuchElementException e) {
            LOGGER.warn("User name element not found", e);

        } finally {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, SECONDS);
        }
        return userName;
    }

    /**
     * Method that gets the text of the delete message text.
     *
     * @return the text of the delete message
     */
    public String getMessageTextDelete() {
        return deleteMessageText.getText();
    }

    /**
     * This method is used to enter to main page of
     * project created using its name.
     *
     * @param projectName This parameter is the project name of project created
     * @return the project main page
     */
    public Project clickOnProject(String projectName) {
        try {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_FAIL_WAIT_TIME, SECONDS);
            WebElement projectNameLink = fluentWait(By.xpath("//a[contains(.,'" + projectName + "')]"));
            projectNameLink.click();
        } catch (NoSuchElementException e) {
            LOGGER.warn("The Project button web element not was find ", e);
            throw new NoSuchElementException("The Web element not was find ", e);
        } finally {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, SECONDS);
        }
        return new Project();
    }

    /**
     * Method that specifies the fluent wait.
     *
     * @param locator value to play with the explicit timeout
     * @return the webelement object
     */
    public WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS)
                .pollingEvery(POLLING, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        return wait.until(driver1 -> driver1.findElement(locator));
    }

    /**
     * Method that returns the settings link given
     * the name of the projects.
     *
     * @param nameProjects value to define the project settings we want
     * @return the Setting instance object
     */
    public Setting clickSettingsLink(String nameProjects) {
        refreshPage();
        WebElement taskElement = driver.findElement(By
                .xpath("//*[@class='hover_link settings' and @href=\"/projects/" + nameProjects + "/settings\"]"));
        clickWebElement(taskElement);
        return new Setting();
    }

    /**
     * Method that does the steps for select account option.
     *
     * @return the Accounts instance
     */
    public Accounts selectAccountOption() {
        clickWebElement(userNameText);
        clickWebElement(accountOption);
        return new Accounts();
    }

    /**
     * Method that click the workspace name and
     * obtaining the new Workspace instance.
     *
     * @param nameWorkspace the Workspace instance
     * @return a new Workspace instance object
     */
    public Workspace clickNameWorkspaceLink(String nameWorkspace) {
        WebElement nameWorkspaceLink = driver.findElement(By.xpath(String.format("//a[.= '%s']", nameWorkspace)));
        nameWorkspaceLink.click();
        return new Workspace();
    }

    /**
     * Method that retrieves the text from the
     * message when we delete a workspace.
     *
     * @return the message text for a workspace deleted
     */
    public String getMessageDeleteWorkspace() {
        return messageDeleteWorkspace.getText();
    }

    /**
     * Method that retrieves the user name.
     *
     * @param value the email of the user
     * @return the user name obtained
     */
    public String getUserName(String value) {

        final String endPointProfile = "/me";
        final String fieldEmail = "email";
        final String fieldUserName = "username";
        final String email = RequestManager.getRequest(endPointProfile).jsonPath().get(fieldEmail);
        if (value.equalsIgnoreCase(email)) {
            return RequestManager.getRequest(endPointProfile).jsonPath().get(fieldUserName);
        }
        return value;
    }

    /**
     * Method that clicks on Project tab.
     */
    public void clickProjectsTab() {
        clickWebElement(projectsTab);
    }

    /**
     * Method that clicks on Workspaces tab.
     */
    public void clickWorkspacesTab() {
        clickWebElement(workspacesTab);
    }

    /**
     * Method to click on a workspace name of the list.
     * @param workspaceName Name of the workspace.
     * @return Workspace instance.
     */
    public Workspace clickOnWorkspaceName(String workspaceName) {
        By by = By.xpath(String.format(workspaceNameLocator, workspaceName));
        clickWebElement(by);
        return new Workspace();
    }
}
