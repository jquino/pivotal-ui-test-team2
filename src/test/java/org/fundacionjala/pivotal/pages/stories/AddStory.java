package org.fundacionjala.pivotal.pages.stories;

import org.fundacionjala.pivotal.api.ProjectManager;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by Automation on 5/4/2017.
 */
public class AddStory {
    private static final String PROJECT_NAME = "TestProject1";
    private static final String STORY_TITLE = "TestStory1";
    private static final String STORY_TYPE = "Bug";
    private static final String STORY_DESCRIPTION = "Short test story description.";
    private int projectId;
    private Dashboard dashboard;

    /**
     * Given that the user has a project. setup class
     */
    @BeforeMethod(groups = {"Functional"})
    public void setup() {
        projectId = ProjectManager.createProject(PROJECT_NAME); //api
        dashboard = Login.loginAsPrimaryUser();
    }

    /**
     * When we have data for a New Story, Then verify that a new story can be added.
     */
    @Test(groups = {"Functional"})
    public void addStoryLA() {
        dashboard.clickOnProject(PROJECT_NAME);
        SideBarStories sideBarStories = new SideBarStories();
        Story story = sideBarStories.clickOnAddStoryButton();
        story.setStoryTitleTextArea(STORY_TITLE);
        story.setStoryType(STORY_TYPE);
        story.setDescriptionTextarea(STORY_DESCRIPTION);
        story.clickOnSaveStoryButton();
        assertTrue(story.existsStoryTitle(STORY_TITLE));
    }

    /**
     * Cleanup method.
     */
    @AfterMethod(groups = {"Functional"})
    public void cleanup() {
        ProjectManager.deleteProject(projectId); //api
    }
}
