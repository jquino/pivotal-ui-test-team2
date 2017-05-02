package org.fundacionjala.pivotal.pages.task;

import org.fundacionjala.pivotal.api.ProjectManager;
import org.fundacionjala.pivotal.api.StoryManager;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.pivotal.pages.dashboard.Dashboard;
import org.fundacionjala.pivotal.pages.stories.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by xain on 4/29/2017.
 */
public class AddTaskTests {

    private static final String PROJECT_NAME = "MyTestProject";
    private static final String STORY_NAME = "MyTestStory";
    private static final String TASK_DESCRIPTION = "MyNewTask";
    private int projectId;
    private Dashboard dashboard;

    /**
     * Given the the user has a project with a story.
     */
    @BeforeMethod(groups = {"Functional"})
    public void setup() {
        projectId = ProjectManager.createProject(PROJECT_NAME);
        StoryManager.createStory(projectId, STORY_NAME);
        dashboard = Login.loginAsPrimaryUser();
    }

    /**
     * Verify it a task can be added to a story.
     */
    @Test(groups = {"Functional"})
    public void shouldAddATask() {
        dashboard.clickOnProject(PROJECT_NAME);
        Story story = new Story();
        story.clickOnExpanderStory();
        story.addTask(TASK_DESCRIPTION);

        assertTrue(story.isThereATaskWithDescription(TASK_DESCRIPTION));
    }

    /**
     * Cleanup method.
     */
    @AfterMethod(groups = {"Functional"})
    public void cleanup() {
        ProjectManager.deleteProject(projectId);
    }
}
