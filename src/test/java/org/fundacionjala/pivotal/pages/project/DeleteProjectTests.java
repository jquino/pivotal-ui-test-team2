package org.fundacionjala.pivotal.pages.project;

import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.fundacionjala.pivotal.api.ProjectManager.createProject;
import static org.testng.Assert.assertEquals;

/**
 * Class that represents test cases for delete project.
 *
 * @author Giovanny Uscamaita on 5/3/2017.
 */
public class DeleteProjectTests {

    private static final String PROJECT_TO_BE_DELETED = "Project1";
    private Dashboard dashboard;

    /**
     * Before method.
     */
    @BeforeMethod(groups = {"Functional"})
    public void setUp() {
        //Given
        createProject(PROJECT_TO_BE_DELETED);
        dashboard = Login.loginAsPrimaryUser();
    }

    /**
     * Test that verifies that the project is deleted.
     */
    @Test(groups = {"Functional"})
    public void testDeleteProject() {
        //When
        Project project = dashboard.clickOnProject(PROJECT_TO_BE_DELETED);
        ProjectSettings projectSettings = project.clickSettingTab();
        DeleteProjectAlert deleteProjectAlert = projectSettings.clickDeleteProjectButton();
        deleteProjectAlert.confirmDeleteProject();

        //Then
        String actualMessage = dashboard.getMessageTextDelete();
        String expectedMessage = String.format("%s %s", PROJECT_TO_BE_DELETED, "was successfully deleted.");
        assertEquals(actualMessage, expectedMessage);
    }
}
