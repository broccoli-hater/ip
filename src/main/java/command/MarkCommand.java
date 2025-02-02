package command;

import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * A command to mark a task as completed in the task list.
 * This command marks the task at the specified index as completed, displays a confirmation message,
 * and saves the updated task list to storage.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as completed.
     *
     * @param index The index of the task to be marked as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command. This method marks the task at the specified index as completed,
     * displays a confirmation message, and saves the updated task list to storage.
     * If the index is invalid, it displays an error message.
     *
     * @param taskList The task list containing the task to be marked.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.get(index).markCompleted();
            storage.saveData(taskList);
            Ui.markTask(taskList.get(index));
        } catch (IndexOutOfBoundsException e) {
            Ui.taskNotFound();
        }
    }
}
