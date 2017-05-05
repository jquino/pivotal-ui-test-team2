package org.fundacionjala.pivotal.pages.workspace;

import org.fundacionjala.pivotal.framework.selenium.DriverManager;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fundacionjala.pivotal.api.WorkspaceManager.deleteWorkspace;
import static org.fundacionjala.pivotal.api.WorkspaceManager.createWorkspace;

/**
 * Created by xain on 4/27/2017.
 */
public class UpdateWorkspaceTests {
    private static final String WORKSPACE = "MyWorkspace";
    private static final String NEW_WORKSPACE_NAME = "MyWorkspace-Modified";
    private Dashboard dashboard;
    private int workspaceId;

    /**
     * Before method.
     */
    @BeforeMethod(groups = {"Functional"})
    public void setUp() {
        workspaceId = createWorkspace(WORKSPACE);
        dashboard = Login.loginAsPrimaryUser();
        dashboard.clickWorkspacesTab();
    }

    /**
     * Verify that the name of the workspace can be updated.
     */
    @Test(groups = {"Functional"})
    public void shouldUpdateWorkspaceName() {
        // When the user edits the name of the workspace
        Workspace workspace = dashboard.clickNameWorkspaceLink(WORKSPACE);
        SettingWorkspace settingWorkspace = workspace.getToolBarWorkspace().clickSettingsWorkspaceLink();
        settingWorkspace.setNameWorkspaceTestField(NEW_WORKSPACE_NAME);
        settingWorkspace.clickSaveChangesWorkspaceLink();

        // Then the name of the workspace is updated
        Assert.assertEquals(settingWorkspace.getWorkspaceName(), NEW_WORKSPACE_NAME);

    }

    /**
     * Cleanup method.
     */
    @AfterMethod(groups = {"Functional"})
    public void cleanUp() {
        deleteWorkspace(workspaceId);
    }

    /**
     * After class method.
     */
    @AfterClass
    public void tearDown() {
        DriverManager.getInstance().quitDriver();
    }
}
