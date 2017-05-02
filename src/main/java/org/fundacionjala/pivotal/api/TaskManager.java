package org.fundacionjala.pivotal.api;

import java.util.HashMap;
import java.util.Map;

import static org.fundacionjala.pivotal.framework.util.Constants.TASKS_ENDPOINT;
import static org.fundacionjala.pivotal.framework.util.Constants.TASK_ENDPOINT;

/**
 * Created by xain on 4/30/2017.
 */
public final class TaskManager {

    /**
     * Private constructor.
     */
    private TaskManager() {

    }

    /**
     * Create a task in the specified story.
     * @param projectId the ID of the project containing the story.
     * @param storyId the ID of the story in which the task will be created.
     * @param description the description of the task.
     * @return the ID of the created task.
     */
    public static int createTask(int projectId, int storyId, String description) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("description", description);
        return RequestManager.postRequest(String.format(TASKS_ENDPOINT, projectId, storyId), parameters)
                .body().jsonPath().get("id");
    }

    /**
     * Delete the specified task in the specified story.
     * @param projectId the ID of the project containing the story.
     * @param storyId the ID of the story in which the task will be created.
     * @param taskId the ID of the task to be deleted.
     */
    public static void deleteTask(int projectId, int storyId, int taskId) {
        RequestManager.deleteRequest(String.format(TASK_ENDPOINT, projectId, storyId, taskId));
    }
}
