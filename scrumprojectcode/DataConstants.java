package scrumprojectcode;
/**
 * @author Jane Dalton
 * Data constants class defines data constants from json files
 */

public class DataConstants {
    /**
     * user data constants
     */
    protected static final String USER_FILE_NAME = "user.json";
    protected static final String USER = "user";
    protected static final String USER_ID = "user-id";
    protected static final String USER_FIRST_NAME = "first-name";
    protected static final String USER_LAST_NAME = "last-name";
    protected static final String USER_USERNAME = "user-name";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_PHONE_NUMBER = "phone-number";
    protected static final String USER_TYPE = "type";
    protected static final String USER_TASKS = "my-tasks";
    protected static final String USER_PROJECTS = "my-projects";

    /**
     * column data constants
     */
    protected static final String COLUMN_FILE_NAME = "column.json";
    protected static final String COLUMN = "column";
    protected static final String COLUMN_PROJECT_ID = "project-id";
    protected static final String COLUMN_ID = "column-id";
    protected static final String COLUMN_TITLE = "column-title";
    protected static final String COLUMN_TASKS = "column-tasks";

    /**
     * history data constants
     */
    protected static final String HISTORY_FILE_NAME = "history.json";
    protected static final String HISTORY = "history";
    protected static final String HISTORY_ID = "history-id";
    protected static final String HISTORY_USER = "history-user";
    protected static final String HISTORY_DETAILS = "history-details";
    protected static final String HISTORY_RECORDED_DATE = "recorded-date";

    /**
     * project data constants
     */
    protected static final String PROJECT_FILE_NAME = "project.json";
    protected static final String PROJECT = "project";
    protected static final String PROJECT_ID = "project-id";
    protected static final String PROJECT_TITLE = "project-title";
    protected static final String PROJECT_USERS = "assigned-users";
    protected static final String PROJECT_COLUMNS = "project-columns";

    /**
     * task data constants
     */
    protected static final String TASK_FILE_NAME = "task.json";
    protected static final String TASK_PROJECT_ID = "project-id";
    protected static final String TASK_COLUMN_ID = "column-id";
    protected static final String TASK_ = "task";
    protected static final String TASK_ID = "task-id";
    protected static final String TASK_TITLE = "task-title";
    protected static final String TASK_DESCRIPTION = "task-description";
    protected static final String TASK_USERS = "assigned-users";
    protected static final String TASK_HISTORY = "history";
    protected static final String TASK_COMMENTS = "comments";
    protected static final String TASK_CREATION_DATE = "creation-date";
    protected static final String TASK_DUE_DATE = "due-date";
}
