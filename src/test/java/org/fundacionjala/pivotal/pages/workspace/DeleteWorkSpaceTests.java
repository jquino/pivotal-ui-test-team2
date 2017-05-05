package org.fundacionjala.pivotal.pages.workspace;

import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fundacionjala.pivotal.api.WorkspaceManager.createWorkspace;
import static org.testng.Assert.assertEquals;

/**
 * Created by jose rioja on 4/27/2017.
 */
public class DeleteWorkSpaceTests {
    private static final String WORKSPACE_NAME_TO_DELETE = "Workspace_To_Delete";
    private Dashboard dashboardPage;

    /**
     * Before method.
     */
    @BeforeMethod(groups = {"Functional"})
    public void setUp() {
        // Given
        createWorkspace(WORKSPACE_NAME_TO_DELETE);
        dashboardPage = Login.loginAsPrimaryUser();
        dashboardPage.clickWorkspacesTab();
    }

    /**
     * Test to verify that a workspace is deleted.
     */
    @Test(groups = {"Functional"})
    public void testDeleteWorkspace() {
        // When
        Workspace workspace = dashboardPage.clickOnWorkspaceName(WORKSPACE_NAME_TO_DELETE);
        SettingWorkspace settingWorkspace = workspace.getToolBarWorkspace().clickSettingsWorkspaceLink();
        DeleteWorkspace deleteWorkspace = settingWorkspace.clickDeleteWorkspaceLink();
        dashboardPage = deleteWorkspace.clickConfirmDeleteLink();

        // Then
        String actualMessage = dashboardPage.getMessageDeleteWorkspace();
        String expectedMessage = String.format("%s %s", WORKSPACE_NAME_TO_DELETE, "was successfully deleted.");
        assertEquals(actualMessage, expectedMessage);
    }
}
