package util;

import java.util.Scanner;

import task.Task;

/**
 * Handles user interaction and displays messages to the user.
 * This class is responsible for reading user input and providing feedback.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads a command from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a line break to separate sections of the conversation.
     */
    public static void breakLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a welcome message to the user.
     */
    public static void sayHello() {
        String greeting = "Greetings! It is I, Sir Talks-A-Lot. \n"
                + "Ah, so you wish to converse with one such as myself? \n"
                + "A noble knight, sworn to honor and valor, holder of great wisdom and unyielding strength? \n"
                + "Very well, I shall indulge thee in thy request. \n"
                + "Speak now, peasant, What dost thou seek from a knight of my stature?";
        System.out.println(greeting);
    }

    /**
     * Displays a farewell message to the user.
     */
    public static void sayBye() {
        String bye = "Hah! A quick departure, is it? Very well, then. \n"
                + "Farewell for now.";
        System.out.println(bye);
    }

    /**
     * Prompts the user to re-enter their input due to invalid or unclear input.
     */
    public static void promptAgain() {
        System.out.println("Speak clearly! I know not what such words mean. Attempt once more.");
    }

    /**
     * Confirms that a task has been added to the list.
     */
    public static void addTask() {
        String addTask = "Verily, I have inscribed this task upon the list.\n"
                + "Let no task go unfulfilled and no duty unrecorded!";
        System.out.println(addTask);
    }

    /**
     * Displays the number of tasks currently in the list.
     *
     * @param count The number of tasks in the list.
     */
    public static void countTask(int count) {
        String countTask = "";
        if (count == 0) {
            countTask = "Thou hast " + count + " tasks upon the list.";
        } else if (count == 1) {
            countTask = "Thou hast " + count + " task upon the list. A worthy pursuit!";
        } else {
            countTask = "Thou hast " + count + " tasks upon the list, each one a worthy pursuit!";
        }
        System.out.println(countTask);
    }

    /**
     * Displays a header before listing tasks that matches the keyword.
     */
    public static void findTask() {
        System.out.println("Here lie the matching tasks in thy list that align "
                + "with thy query, as decreed by my boundless wisdom and keen eye!");
    }

    /**
     * Displays a header before listing all tasks.
     */
    public static void printList() {
        System.out.println("Hear ye! These are the tasks upon thy list, as decreed by thine own hand:");
    }

    /**
     * Confirms that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     */
    public static void deleteTask(Task task) {
        System.out.println("Noted, then. I have seen fit to remove this trivial task.");
        System.out.print("    ");
        System.out.println(task.toString());
    }

    /**
     * Confirms that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     */
    public static void markTask(Task task) {
        System.out.println("Behold! A task completed! A most noble accomplishment.");
        System.out.println(task.toString());
    }

    /**
     * Confirms that a task has been unmarked as completed.
     *
     * @param task The task that was unmarked as completed.
     */
    public static void unmarkTask(Task task) {
        System.out.println("Alas, a task left to be conquered. Its time has not yet come to pass.");
        System.out.println(task.toString());
    }

    /**
     * Informs the user that the specified task does not exist.
     */
    public static void taskNotFound() {
        System.out.println("It seems no such task exists, for I have heard no mention of it, "
                + "nor does it appear to fall within the realm of possibility.");
    }

    /**
     * Informs the user that the task description cannot be empty.
     */
    public static void descriptionNotFound() {
        System.out.println("The description cannot be left empty, for how would one know what is to be done?");
    }

    /**
     * Informs the user that the task deadline cannot be empty.
     */
    public static void deadLineNotFound() {
        System.out.println("The deadline cannot be left empty, for how would one know when the task is to be done?");
    }

    /**
     * Informs the user that the event start time cannot be empty.
     */
    public static void startTimeNotFound() {
        System.out.println("The start time cannot be left empty, for how would one know when the event is?");
    }

    /**
     * Informs the user that the event end time cannot be empty.
     */
    public static void endTimeNotFound() {
        System.out.println("The end time cannot be left empty, for how would one know when the event concludes?");
    }

    /**
     * Informs the user that the time format is incorrect and provides the correct format.
     */
    public static void incorrectTimeFormat() {
        System.out.println("The chronicles of time must be inscribed in the noble and proper manner: yyyy-mm-dd!");
    }
}
