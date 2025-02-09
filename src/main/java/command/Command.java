package command;

import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * An abstract command.
 * This class serves as the base class for all specific command implementations.
 */
public abstract class Command {
    /**
     * Executes the command.
     * This method is intended to be overridden by subclasses
     * to provide specific implementations for each command type.
     *
     * @param task    The task list on which the command operates.
     * @param ui      The user interface to display messages or interact with the user.
     * @param storage The storage to save or load data.
     */
    public void execute(TaskList task, Ui ui, Storage storage) {
    }

    public String getResponse() {
        return "";
    }
}

