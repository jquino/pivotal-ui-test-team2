package org.fundacionjala.pivotal.pages.stories;

import org.fundacionjala.pivotal.api.ProjectManager;
import org.fundacionjala.pivotal.api.StoryManager;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by Automation on 5/4/2017.
 */
public class UpdateStory {
    private static final String PROJECT_NAME = "TestProject3";
    private static final String STORY_TITLE = "TestStory3";
    private static final String UPDATED_STORY_TITLE = "UpdatedTestStory3";
    private static final String UPDATED_STORY_DESCRIPTION = "Updated short test story description.";
    private int projectId;
    private Dashboard dashboard;

    /**
     * Given that the user has a project, and a User Story. setup class
     */
    @BeforeMethod(groups = {"Functional"})
    public void setup() {
        projectId = ProjectManager.createProject(PROJECT_NAME); //api
        StoryManager.createStory(projectId, STORY_TITLE); //api
        dashboard = Login.loginAsPrimaryUser();
    }

    /**
     * When we have update data for an existent Story, Then verify that the old story has been updated.
     */
    @Test(groups = {"Functional"})
    public void updateStoryLA() {
        dashboard.clickOnProject(PROJECT_NAME);
        Story story = new Story();
        story.clickOnExpanderStoryLA(STORY_TITLE); //opens the right Story "expandable" when more than one is available
        story.setStoryTitleTextArea(UPDATED_STORY_TITLE);
        story.setDescriptionTextarea(UPDATED_STORY_DESCRIPTION);
        story.clickOnCloseStoryButton();
        assertTrue(story.existsStoryTitle(UPDATED_STORY_TITLE));
    }

    /**
     * Cleanup method.
     */
    @AfterMethod(groups = {"Functional"})
    public void cleanup() {
        ProjectManager.deleteProject(projectId); //api
    }
}
