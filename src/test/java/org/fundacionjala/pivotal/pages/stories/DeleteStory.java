package org.fundacionjala.pivotal.pages.stories;

import org.fundacionjala.pivotal.api.ProjectManager;
import org.fundacionjala.pivotal.api.StoryManager;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

/**
 * Created by Automation on 5/4/2017.
 */
public class DeleteStory {
    private static final String PROJECT_NAME = "TestProject2";
    private static final String STORY_TITLE = "TestStory2";
    private int projectId;
    private Dashboard dashboard;

    /**
     * Given that the user has a project, and a User Story.  setup class
     */
    @BeforeMethod(groups = {"Functional"})
    public void setup() {
        projectId = ProjectManager.createProject(PROJECT_NAME); //api
        StoryManager.createStory(projectId, STORY_TITLE); //api
        dashboard = Login.loginAsPrimaryUser();
    }

    /**
     * When we have an existent Story, Then verify that we can delete it.
     */
    @Test(groups = {"Functional"})
    public void updateStoryLA() {
        dashboard.clickOnProject(PROJECT_NAME);
        Story story = new Story();
        story.clickOnExpanderStoryLA(STORY_TITLE); //opens the right Story "expandable" when more than one is available
        story.clickOnDeleteStoryButton();
        story.clickOnConfirmDeleteStoryButton();
        assertFalse(story.existsStoryTitle(STORY_TITLE));
    }

    /**
     * Cleanup method.
     */
    @AfterMethod(groups = {"Functional"})
    public void cleanup() {
        ProjectManager.deleteProject(projectId); //api
    }
}
