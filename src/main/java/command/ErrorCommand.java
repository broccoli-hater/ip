package command;

import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * A command to handle errors in user input.
 * This command displays appropriate error messages based on the type of error encountered.
 */
public class ErrorCommand extends Command {
    private String error;

    /**
     * Constructs an ErrorCommand with the specified error type.
     *
     * @param error The type of error encountered (e.g., "empty description", "empty deadline").
     */
    public ErrorCommand(String error) {
        this.error = error;
    }

    /**
     * Executes the error command. This method displays an appropriate error message
     * based on the type of error specified during construction.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface to display error messages.
     * @param storage  The storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (error) {
        case "empty description" -> {
            Ui.descriptionNotFound();
        }
        case "empty deadline" -> {
            Ui.deadLineNotFound();
        }
        case "empty start time" -> {
            Ui.startTimeNotFound();
        }
        case "empty end time" -> {
            Ui.endTimeNotFound();
        }
        default -> {
            Ui.promptAgain();
        }
        }
    }
}
