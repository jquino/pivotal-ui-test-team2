package org.fundacionjala.pivotal.api;

import java.util.HashMap;
import java.util.Map;

import static org.fundacionjala.pivotal.framework.util.Constants.WORKSPACES_ENDPOINT;

/**
 * Created by Jose Rioja on 4/27/2017.
 */
public final class WorkspaceManager {

    /**
     * WorkspaceManager constructor.
     */
    private WorkspaceManager() {
    }

    /**
     * Method to create a workspace via API.
     * @param name Workspace name.
     * @return ID of the created workspace.
     */
    public static int createWorkspace(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return RequestManager.postRequest(WORKSPACES_ENDPOINT, parameters).body().jsonPath().get("id");
    }

    /**
     * Method to delete a workspace via API.
     * @param workspaceId ID of the workspace to delete.
     */
    public static void deleteWorkspace(int workspaceId) {
        StringBuilder endpoint = new StringBuilder(WORKSPACES_ENDPOINT);
        endpoint.append(workspaceId);
        RequestManager.deleteRequest(endpoint.toString());
    }
}
