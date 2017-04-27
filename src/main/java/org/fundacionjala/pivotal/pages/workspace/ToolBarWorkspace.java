package org.fundacionjala.pivotal.pages.workspace;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.fundacionjala.pivotal.framework.util.Constants.ELEMENT_COULD_NOT_BE_FOUND;
import static org.fundacionjala.pivotal.framework.util.Constants.IMPLICIT_WAIT_TIME;

/**
 * Class that represents the Workspace page
 * tool bar with its elements to be used.
 */
public class ToolBarWorkspace extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(Workspace.class.getSimpleName());

    @FindBy(css = "a[data-aid='navTab-settings']")
    private WebElement settingsWorkspaceLink;

    @FindBy(className = "raw_context_name")
    private WebElement workspaceNameText;

    @FindBy(className = "page_header_container")
    private WebElement toolBarContainer;

    /**
     * Mehtod that lead us to the workspace page options.
     *
     * @return the setting workspace instance
     */
    public SettingWorkspace clickSettingsWorkspaceLink() {
        settingsWorkspaceLink.click();
        return new SettingWorkspace();
    }

    /**
     * Method that returns the workspace name.
     *
     * @return the value of the workspace name
     */
    public String getWorkspaceNameText() {
        String workspaceName = "";
        try {
            wait.until(ExpectedConditions.visibilityOf(toolBarContainer));
            workspaceName = workspaceNameText.getText();
        } catch (NoSuchElementException e) {
            LOGGER.warn(ELEMENT_COULD_NOT_BE_FOUND, e);
        } finally {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        }
        return workspaceName;
    }
}
