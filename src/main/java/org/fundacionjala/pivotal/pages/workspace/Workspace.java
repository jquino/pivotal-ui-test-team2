package org.fundacionjala.pivotal.pages.workspace;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.fundacionjala.pivotal.pages.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.fundacionjala.pivotal.framework.util.Constants.ELEMENT_COULD_NOT_BE_FOUND;
import static org.fundacionjala.pivotal.framework.util.Constants.IMPLICIT_WAIT_TIME;
import static org.fundacionjala.pivotal.framework.util.CommonMethods.getTextFieldValue;

/**
 * Class that represents the main workspace page with all
 * its contents to be used.
 */
public class Workspace extends BasePage {

    private static final Logger LOGGER = Logger.getLogger(Workspace.class.getSimpleName());

    private SideBarWorkspace sideBarWorkspace;

    private ToolBarWorkspace toolBarWorkspace;

    @FindBy(className = "raw_context_name")
    private WebElement workspaceNameText;

    @FindBy(css = ".tc_header_item.tc_header_logo")
    private WebElement returnDashboardLink;

    @FindBy(xpath = "//div[contains(@class, 'tn-PanelHeader__heading')]")
    private WebElement projectIntoWorkspaceNameText;

    @FindBy(className = "table")
    private WebElement panelContainerProjects;

    /**
     * Class constructor.
     */
    public Workspace() {
        sideBarWorkspace = new SideBarWorkspace();
        toolBarWorkspace = new ToolBarWorkspace();
    }

    /**
     * Method that exit from the workspace and
     * return to the dashboard instance.
     *
     * @return the dashboard instance
     */
    public Dashboard clickReturnDashboardLink() {
        returnDashboardLink.click();
        return new Dashboard();
    }

    /**
     * Method that returns as the workspace name.
     *
     * @return the value of the workspace name
     */
    public String getProjectIntoWorkspaceNameText() {
        String projectIntoWorkspaceName = "";
        try {
            wait.until(ExpectedConditions.visibilityOf(panelContainerProjects));
            projectIntoWorkspaceName = projectIntoWorkspaceNameText.getText();
        } catch (NoSuchElementException e) {
            LOGGER.warn(ELEMENT_COULD_NOT_BE_FOUND + e);
        } finally {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        }
        return projectIntoWorkspaceName;
    }

    /**
     * Method that returns the workspace id
     * from the url of the page.
     *
     * @return the workspace id
     */
    public String getIdWorkspace() {
        String url = driver.getCurrentUrl();
        String idWorkspace = "";
        Pattern p = Pattern.compile("[\\D]");
        Matcher m = p.matcher(url);
        if (m.find()) {
            idWorkspace = m.replaceAll("");
        }
        return idWorkspace;
    }

    /**
     * Method that returns the asserted map
     * of steps for workspace creation.
     *
     * @return the asserted map
     */
    public Map<WorkspaceSteps, Object> getAssertionMap() {
        Map<WorkspaceSteps, Object> assertionMap = new EnumMap<>(WorkspaceSteps.class);
        assertionMap.put(WorkspaceSteps.WORKSPACE_NAME, toolBarWorkspace.getWorkspaceNameText());
        return assertionMap;
    }

    /**
     * Method that returns the side bar workspace instance.
     *
     * @return the side bar workspace instance
     */
    public SideBarWorkspace getSideWorkspace() {
        return sideBarWorkspace;
    }

    /**
     * Method that returns the tool bar workspace instance.
     *
     * @return the tool bar workspace instance
     */
    public ToolBarWorkspace getToolBarWorkspace() {
        return toolBarWorkspace;
    }

    /**
     * Method that returns the workspace name.
     * @return Workspace name.
     */
    public String getWorkspaceName() {
        return getTextFieldValue(workspaceNameText);
    }
}
