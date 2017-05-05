package org.fundacionjala.pivotal.pages.project;

import org.fundacionjala.pivotal.framework.util.CommonMethods;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fundacionjala.pivotal.api.ProjectManager.createProject;
import static org.fundacionjala.pivotal.api.ProjectManager.deleteProject;
import static org.testng.Assert.assertEquals;

/**
 * Created by xianqe on 5/3/2017.
 */
public class UpdateProjectTests {

    private static final String PROJECT = "My Project";
    private static final String NEW_PROJECT_NAME = "My updated project";
    private static final String NEW_DESCRIPTION = "updated description";
    private static final String NEW_INITIAL_VELOCITY = "12";
    private static final String NEW_DONE_ITERATIONS_TO_SHOW = "6";
    private Dashboard dashboard;
    private int projectId;

    /**
     * Before method.
     */
    @BeforeMethod(groups = {"Functional"})
    public void setUp() {
        projectId = createProject(PROJECT);
        dashboard = Login.loginAsPrimaryUser();
    }

    /**
     * Verify that the project name can be updated.
     */
    @Test(groups = {"Functional"})
    public void testUpdateProjectName() {
        //When
        Project project = dashboard.clickOnProject(PROJECT);
        ProjectSettings projectSettings = project.clickSettingTab();
        projectSettings.setProjectTitleTestField(NEW_PROJECT_NAME);
        projectSettings.clickSaveButton();

        //Then
        assertEquals(projectSettings.getProjectName(), NEW_PROJECT_NAME);
    }

    /**
     * Verify that the description can be updated.
     */
    @Test
    public void testUpdateProjectDescription() {
        //When
        Project project = dashboard.clickOnProject(PROJECT);
        ProjectSettings projectSettings = project.clickSettingTab();
        projectSettings.setProjectDescriptionTestField(NEW_DESCRIPTION);
        projectSettings.clickSaveButton();

        //Then
        assertEquals(projectSettings.getProjectDescription(), NEW_DESCRIPTION);
    }

    /**
     * Verify we can update "Initial velocity".
     */
    @Test
    public void testUpdateInitialVelocity() {
        //When
        Project project = dashboard.clickOnProject(PROJECT);
        ProjectSettings projectSettings = project.clickSettingTab();
        projectSettings.setProjectInitialVelocityTestField(NEW_INITIAL_VELOCITY);
        projectSettings.clickSaveButton();

        //Then
        assertEquals(projectSettings.getProjectInitialVelocity(), NEW_INITIAL_VELOCITY);
    }

    /**
     * Verify we can update "Number of Done Iterations to Show".
     */
    @Test
    public void testUpdateNumberOfDoneIterationsToShow() {
        //When
        Project project = dashboard.clickOnProject(PROJECT);
        ProjectSettings projectSettings = project.clickSettingTab();
        projectSettings.setProjectNumOfDoneIterationsToShowTestField(NEW_DONE_ITERATIONS_TO_SHOW);
        projectSettings.clickSaveButton();

        //Then
        assertEquals(projectSettings.getProjectIterationsToShow(), NEW_DONE_ITERATIONS_TO_SHOW);
    }

    /**
     * Cleanup method.
     */
    @AfterMethod(groups = {"Functional"})
    public void cleanUp() {
        deleteProject(projectId);
    }

    /**
     * After class method.
     */
    @AfterClass
    public void tearDown() {
        String message = "Finishing test";
        CommonMethods.quitProgram(message);
    }
}
