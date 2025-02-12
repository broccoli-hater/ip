package command;

import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * A command to handle errors in user input.
 * This command displays appropriate error messages based on the type of error encountered.
 */
public class ErrorCommand extends Command {
    private final String error;
    private final StringBuilder response = new StringBuilder();

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
            response.append(Ui.printDescriptionNotFound());
        }
        case "empty deadline" -> {
            response.append(Ui.printDeadLineNotFound());
        }
        case "empty start time" -> {
            response.append(Ui.printStartTimeNotFound());
        }
        case "empty end time" -> {
            response.append(Ui.printEndTimeNotFound());
        }
        case "empty index" -> {
            response.append(Ui.printIndexNotFound());
        }
        case "empty keyword" -> {
            response.append(Ui.printEmptyKeyword());
        }
        case "invalid time format" -> {
            response.append(Ui.printIncorrectTimeFormat());
        }
        default -> {
            response.append(Ui.promptAgain());
        }
        }
    }

    @Override
    public String getResponse(){
        return response.toString();
    }
}
