package org.fundacionjala.pivotal.api;

import java.util.HashMap;
import java.util.Map;

import static org.fundacionjala.pivotal.framework.util.Constants.STORIES_ENDPOINT;
import static org.fundacionjala.pivotal.framework.util.Constants.STORY_ENDPOINT;
/**
 * Created by xain on 4/29/2017.
 */
public final class StoryManager {

    /**
     * Private constructor.
     */
    private StoryManager() {

    }

    /**
     * Creates a story with the supplied name in the specified project.
     * @param projectId The project in which the Story will be created.
     * @param name      The name of the story.
     * @return          The ID of the new Story.
     */
    public static int createStory(int projectId, String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return RequestManager.postRequest(String.format(STORIES_ENDPOINT, projectId), parameters)
                .body().jsonPath().get("id");
    }

    /**
     * Deletes the specified story in the specified project.
     * @param projectId the ID of the project containing the story.
     * @param storyId the ID of the story to delete.
     */
    public static void deleteStory(int projectId, int storyId) {
        RequestManager.deleteRequest(String.format(STORY_ENDPOINT, projectId, storyId));
    }

}
