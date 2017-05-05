package org.fundacionjala.pivotal.pages.project;

import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.fundacionjala.pivotal.api.ProjectManager.deleteProject;
import static org.fundacionjala.pivotal.framework.util.Constants.RED_COLOR;
import static org.testng.Assert.assertEquals;

/**
 * Class that verifies the Add project cases.
 * The first one is creating a project
 * The second and third one is to verify the error message
 * that appears when we don't fill the fields needed on the form.
 *
 * @author  Giovanny Uscamaita on 5/3/2017.
 */
public class AddProjectTests {

    private static final String PROJECT_NAME = "TestProject";
    private Dashboard dashboard;
    private Project project;
    private SoftAssert softAssert;

    /**
     * Before method.
     */
    @BeforeMethod (groups = {"Functional"})
    public void setUp() {
        //Given
        dashboard = Login.loginAsPrimaryUser();
        softAssert = new SoftAssert();
    }

    /**
     * Test that creates a project given the project name
     * and the account name.
     */
    @Test (groups = {"Functional"})
    public void testAddProject() {
        //When
        CreateProject createProject = dashboard.clickCreateProjectButton();
        String projectName = "TestProject";
        String testAccount = "Account1";
        createProject.setProjectName(projectName);
        createProject.clickPublicProjectPrivacy();
        createProject.setAccountDropDown(testAccount);
        project = createProject.clickCreateProject();

        //Then
        assertEquals(PROJECT_NAME, project.getTitle());
    }

    /**
     * Test that verifies the error message for project name
     * text field.
     */
    @Test
    public void testErrorMsgWhenProjectNameIsEmpty() {
        //When
        CreateProject createProject = dashboard.clickCreateProjectButton();
        String testAccount = "Account2";
        createProject.setAccountDropDown(testAccount);
        createProject.clickCreateProject();

        //Then
        String expectedMessage = "Enter a name for your project";
        String actualMessage = createProject.getProjectTitleMessage();
        softAssert.assertEquals(expectedMessage, actualMessage);
        softAssert.assertEquals(createProject.getProjectNameTextFieldColor(), RED_COLOR);
        softAssert.assertAll();
    }

    /**
     * Test that verifies the error message for account field.
     */
    @Test
    public void testErrorMsgWhenAccountNameIsEmpty() {
        //When
        CreateProject createProject = dashboard.clickCreateProjectButton();
        String projectName = "Project2";
        createProject.setProjectName(projectName);
        createProject.clickCreateProject();

        //Then
        String expectedMessage = "Please select or create and account for the new project";
        String actualMessage = createProject.getAccountMessage();
        softAssert.assertEquals(expectedMessage, actualMessage);
        softAssert.assertEquals(createProject.getAccountTextFieldColor(), RED_COLOR);
        softAssert.assertAll();
    }

    /**
     * After groups method.
     */
    @AfterMethod(groups = {"Functional"})
    public void deleteTheProject() {
        ProjectSettings projectSettings = project.clickSettingTab();
        String projectId = projectSettings.getProjectId();
        deleteProject(Integer.parseInt(projectId));
    }
}
