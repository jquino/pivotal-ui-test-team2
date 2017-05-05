package org.fundacionjala.pivotal.pages.workspace;

import org.fundacionjala.pivotal.api.WorkspaceManager;
import org.fundacionjala.pivotal.framework.selenium.DriverManager;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.fundacionjala.pivotal.framework.util.Constants.RED_COLOR;
import static org.testng.Assert.assertEquals;

/**
 * Created by jose rioja on 4/27/2017.
 */
public class AddWorkSpaceTests {
    private Dashboard dashboardPage;
    private Workspace workspace;
    private SoftAssert softAssert;

    /**
     * Before method.
     */
    @BeforeMethod(groups = {"Functional"})
    public void setUp() {
        // Given
        dashboardPage = Login.loginAsPrimaryUser();
        dashboardPage.clickWorkspacesTab();
        softAssert = new SoftAssert();
    }

    /**
     * Test to create a workspace.
     */
    @Test(groups = {"Functional"})
    public void testCreateWorkspace() {
        // When
        CreateWorkspace createWorkspace = dashboardPage.clickCreateWorkspaceButton();
        String workspaceName = "Workspace1";
        createWorkspace.setWorkspaceName(workspaceName);
        workspace = createWorkspace.clickCreateWorkspaceButton();

        // Then
        assertEquals(workspaceName, workspace.getWorkspaceName());
    }

    /**
     * Test to verify the error message displayed when workspace name is empty.
     */
    @Test
    public void testErrorMsgWhenWorkspaceNameIsEmpty() {
        // When
        CreateWorkspace createWorkspace = dashboardPage.clickCreateWorkspaceButton();
        createWorkspace.clickCreateWorkspaceButton();

        // Then
        String expectedMessage = "Workspace name can't be blank.";
        String actualMessage = createWorkspace.getMessageWorkspaceNameEmpty();
        softAssert.assertEquals(actualMessage, expectedMessage);
        softAssert.assertEquals(createWorkspace.getWorkspaceNameTextFieldColor(), RED_COLOR);
        softAssert.assertAll();
    }

    /**
     * Test to verify that the workspace creation is canceled.
     */
    @Test
    public void testCancelWorkspaceCreation() {
        // When
        CreateWorkspace createWorkspace = dashboardPage.clickCreateWorkspaceButton();
        String workspaceName = "Workspace2";
        createWorkspace.setWorkspaceName(workspaceName);
        createWorkspace.clickCancelCreateWorkspaceButton();

        // Then
        String expectedUrl = "https://www.pivotaltracker.com/dashboard";
        String actualUrl = DriverManager.getInstance().getDriver().getCurrentUrl();
        assertEquals(actualUrl, expectedUrl);
    }

    /**
     * Test to verify all the elements displayed in the Create Workspace window.
     */
    @Test
    public void testWorkspaceCreationUI() {
        // When
        CreateWorkspace createWorkspace = dashboardPage.clickCreateWorkspaceButton();

        // Then
        softAssert.assertEquals(createWorkspace.getCreateWorkspaceWindowsTitle(), "Create new workspace");
        softAssert.assertEquals(createWorkspace.getWorkspaceNameLabel(), "Workspace Name");
        String placeholder = "Enter a name for your workspace";
        softAssert.assertEquals(createWorkspace.getWorkspaceNamePlaceholderAttribute(), placeholder);
        softAssert.assertTrue(createWorkspace.isCancelButtonPresent());
        softAssert.assertTrue(createWorkspace.isCreateButtonPresent());
        softAssert.assertAll();
    }

    /**
     * After groups method.
     */
    @AfterMethod(groups = {"Functional"})
    public void deleteWorkspace() {
        String workspaceId = workspace.getIdWorkspace();
        WorkspaceManager.deleteWorkspace(Integer.parseInt(workspaceId));
        System.out.println("workspaceId: " + workspaceId);
    }
}
