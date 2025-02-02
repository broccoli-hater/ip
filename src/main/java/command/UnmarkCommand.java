package command;

import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * A command to unmark a task as completed in the task list.
 * This command unmarks the task at the specified index, displays a confirmation message,
 * and saves the updated task list to storage.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index of the task to be unmarked.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command. This method unmarks the task at the specified index,
     * displays a confirmation message, and saves the updated task list to storage.
     * If the index is invalid, it displays an error message.
     *
     * @param taskList The task list containing the task to be unmarked.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.get(index).unmarkCompleted();
            storage.saveData(taskList);
            Ui.unmarkTask(taskList.get(index));
        } catch (IndexOutOfBoundsException e) {
            Ui.taskNotFound();
        }
    }
}
