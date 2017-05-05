package org.fundacionjala.pivotal.framework.util;

/**
 * This class will let us use constants variables through the classes.
 */
public final class Constants {

    public static final int IMPLICIT_PROJECT_WAIT = 30;

    public static final int IMPLICIT_WAIT_TIME = 60;

    public static final int WAIT_TIME = 60;

    public static final int LOAD_PAGE_TIME = 60;

    public static final int IMPLICIT_FAIL_WAIT_TIME = 30;

    public static final int DELETE_STATUS_CODE = 204;

    public static final int SUCCESS_STATUS_CODE = 200;

    public static final String DASHBOARD_URL = "https://www.pivotaltracker.com/dashboard";

    public static final String PROJECTS_ENDPOINT = "/projects/";

    public static final String STORIES_ENDPOINT = "/projects/%d/stories";

    public static final String STORY_ENDPOINT = "/projects/%d/stories/%d";

    public static final String TASKS_ENDPOINT = "/projects/%d/stories/%d/tasks";

    public static final String TASK_ENDPOINT = "/projects/%d/stories/%d/tasks/%d";

    public static final String WORKSPACES_ENDPOINT = "/my/workspaces/";

    public static final String ATTRIBUTE_ID = "id";

    public static final String ATTRIBUTE_VALUE = "value";

    public static final String REGEX_INSIDE_BRACKETS = "[\\[]+[\\w.]+[^\\(]+\\]";

    public static final String REGEX_HALF_BRACKET = "[";

    public static final String REGEX_BRACKETS = "^\\[|\\]|\\.";

    public static final String ERROR_ACCOUNT_MESSAGE_TEXT = "please select or create an account for the new project";

    public static final String ERROR_PROJECT_TITLE_TEXT = "project name can't be blank";

    public static final String ELEMENT_COULD_NOT_BE_FOUND = "The element could not be found";

    public static final String DASHBOARD_PAGE = "/dashboard";

    public static final String RED_COLOR = "#dd7270";

    /**
     * Class constructor.
     */
    private Constants() {
    }
}
