package org.fundacionjala.pivotal.api;

import java.util.HashMap;
import java.util.Map;

import static org.fundacionjala.pivotal.framework.util.Constants.PROJECTS_ENDPOINT;

/**
 * Created by xain on 4/29/2017.
 */
public final class ProjectManager {
    /**
     * Private constructor.
     */
    private ProjectManager() {

    }

    /**
     * Create a project through the REST API.
     * @param name      The name of the project.
     * @return          The ID of the created project.
     */
    public static int createProject(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return RequestManager.postRequest(PROJECTS_ENDPOINT, parameters).body().jsonPath().get("id");
    }

    /**
     * Deletes the specified project.
     * @param projectId The ID of the project to delete.
     */
    public static void deleteProject(int projectId) {
        StringBuilder endpoint = new StringBuilder(PROJECTS_ENDPOINT);
        endpoint.append(projectId);
        RequestManager.deleteRequest(endpoint.toString());
    }
}
