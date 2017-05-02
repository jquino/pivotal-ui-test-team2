package org.fundacionjala.pivotal.pages.task;

import org.fundacionjala.pivotal.api.ProjectManager;
import org.fundacionjala.pivotal.api.StoryManager;
import org.fundacionjala.pivotal.api.TaskManager;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.fundacionjala.pivotal.pages.stories.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

/**
 * Created by xain on 5/1/2017.
 */
public class DeleteTaskTests {
    private static final String PROJECT_NAME = "MyTestProject";
    private static final String STORY_NAME = "MyTestStory";
    private static final String TASK_DESCRIPTION = "MyNewTask";
    private int projectId;
    private Dashboard dashboard;

    /**
     * Given the the user has a task in a story of a project.
     */
    @BeforeMethod(groups = {"Functional"})
    public void setup() {
        projectId = ProjectManager.createProject(PROJECT_NAME);
        int storyId = StoryManager.createStory(projectId, STORY_NAME);
        TaskManager.createTask(projectId, storyId, TASK_DESCRIPTION);
        dashboard = Login.loginAsPrimaryUser();
    }

    /**
     * Verify that a task can be deleted.
     */
    @Test(groups = {"Functional"})
    public void shouldDeleteATask() {
        dashboard.clickOnProject(PROJECT_NAME);
        Story story = new Story();
        story.clickOnExpanderStory();
        story.deleteTask(TASK_DESCRIPTION);

        assertFalse(story.isThereATaskWithDescription(TASK_DESCRIPTION));
    }

    /**
     * Cleanup method.
     */
    @AfterMethod(groups = {"Functional"})
    public void cleanup() {
        ProjectManager.deleteProject(projectId);
    }
}
