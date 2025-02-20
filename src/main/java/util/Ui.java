package util;

import task.Task;

/**
 * Handles user interaction and displays messages to the user.
 * This class is responsible for reading user input and providing feedback.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public static String sayHello() {
        String greeting = "Greetings! It is I, Sir Talks-A-Lot. \n"
                + "Ah, so you wish to converse with one such as myself? \n"
                + "A noble knight, sworn to honor and valor, holder of great wisdom and unyielding strength? \n"
                + "Very well, I shall indulge thee in thy request. \n"
                + "Speak now, peasant, What dost thou seek from a knight of my stature?";
        return greeting;
    }

    /**
     * Displays a farewell message to the user.
     */
    public static String sayBye() {
        String bye = "Hah! A quick departure, is it? Very well, then. \n"
                + "Farewell for now.";
        return bye;
    }

    /**
     * Prompts the user to re-enter their input due to invalid or unclear input.
     */
    public static String promptAgain() {
        return "Speak clearly! I know not what such words mean. Attempt once more.";
    }

    /**
     * Confirms that a task has been added to the list.
     */
    public static String addTask() {
        String addTask = "Verily, I have inscribed this task upon the list.\n"
                + "Let no task go unfulfilled and no duty unrecorded!";
        return addTask;
    }

    /**
     * Displays the number of tasks currently in the list.
     *
     * @param count The number of tasks in the list.
     */
    public static String countTask(int count) {
        String countTask = "";
        if (count == 0) {
            countTask = "Thou hast " + count + " tasks upon the list.";
        } else if (count == 1) {
            countTask = "Thou hast " + count + " task upon the list. A worthy pursuit!";
        } else {
            countTask = "Thou hast " + count + " tasks upon the list, each one a worthy pursuit!";
        }
        return countTask;
    }

    /**
     * Displays a header before listing tasks that matches the keyword.
     */
    public static String findTask() {
        return "Here lie the matching tasks in thy list that align "
                + "with thy query, as decreed by my boundless wisdom and keen eye!";
    }

    /**
     * Displays a header before listing all tasks.
     */
    public static String printList() {
        return "Hear ye! These are the tasks upon thy list, as decreed by thine own hand:";
    }

    /**
     * Confirms that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     */
    public static String deleteTask(Task task) {
        return "Noted, then. I have seen fit to remove this trivial task. \n"
                + "    " + task.toString();
    }

    /**
     * Confirms that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     */
    public static String markTask(Task task) {
        return "Behold! A task completed! A most noble accomplishment. \n"
                + task.toString();
    }

    /**
     * Confirms that a task has been unmarked as completed.
     *
     * @param task The task that was unmarked as completed.
     */
    public static String unmarkTask(Task task) {
        return "Alas, a task left to be conquered. Its time has not yet come to pass.\n"
                + task.toString();
    }

    /**
     * Informs the user that the specified task does not exist.
     */
    public static String printTaskNotFound() {
        return "It seems no such task exists, for I have heard no mention of it, "
                + "nor does it appear to fall within the realm of possibility.";
    }

    /**
     * Informs the user that the task description cannot be empty.
     */
    public static String printDescriptionNotFound() {
        return "The description cannot be left empty, for how would one know what is to be done?";
    }

    /**
     * Informs the user that the task deadline cannot be empty.
     */
    public static String printDeadLineNotFound() {
        return "The deadline cannot be left empty, for how would one know when the task is to be done?";
    }

    /**
     * Informs the user that the event start time cannot be empty.
     */
    public static String printStartTimeNotFound() {
        return "The start time cannot be left empty, for how would one know when the event is?";
    }

    /**
     * Informs the user that the event end time cannot be empty.
     */
    public static String printEndTimeNotFound() {
        return "The end time cannot be left empty, for how would one know when the event concludes?";
    }

    /**
     * Informs the user that the time format is incorrect and provides the correct format.
     */
    public static String printIncorrectTimeFormat() {
        return "The chronicles of time must be inscribed in the noble and proper manner: yyyy-mm-dd!";
    }

    /**
     * Informs the user that the index is not found.
     */
    public static String printIndexNotFound() {
        return "The index must be stated, for how else would one know on which task to perform your command on?";
    }

    /**
     * Informs the user that no matching tasks are found.
     */
    public static String printNoMatchingTasksFound() {
        return "Alas, thy search has yielded naught but emptiness! \n"
                + "Reforge thy query and try again.";
    }

    /**
     * Informs the user that the keyword field is empty.
     */
    public static String printEmptyKeyword() {
        return "The keyword field is empty, a void most dire and unacceptable! \n"
                + "Rectify this error, or face the consequences of thy folly!";
    }

    /**
     * Informs the user that the tag fields are empty.
     */
    public static String printEmptyTag() {
        return """
                The tag field is empty, a void most dire and unacceptable!\s
                Tags must be written in the sacred format: #tag, or they shall be case aside\
                as unworthy of mine attention!\s
                """;
    }

    /**
     * Informs the user that the specified tags have been added.
     */
    public static String addTags() {
        return "Verily, 'tis done! The tags are added, by my command and noble favor!";
    }

    /**
     * Informs the user that the specified tags have been removed.
     */
    public static String removeTags() {
        return "Understood. The tags are no more, vanquished by my command and unyielding strength of will!";
    }

    /**
     * Informs the user that the index is invalid.
     */
    public static String printInvalidIndex() {
        return "The index thou dost seek to wield must be a positive integer, "
                + "and it must correspond to a task within the sacred scroll of thy task list.";
    }
}
