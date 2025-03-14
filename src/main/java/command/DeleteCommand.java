package command;

import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * A command to delete a task from the task list.
 * This command removes the task at the specified index, displays a confirmation message,
 * and saves the updated task list to storage.
 */
public class DeleteCommand extends Command {
    private final int index;
    private final StringBuilder response = new StringBuilder();


    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command. This method removes the task at the specified index,
     * displays a confirmation message, and saves the updated task list to storage.
     * If the index is invalid, it displays an error message.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            response.append(Ui.deleteTask(taskList.get(index)));
            response.append("\n");
            taskList.remove(index);
            storage.saveData(taskList);
            response.append(Ui.countTask(taskList.size()));
        } catch (IndexOutOfBoundsException e) {
            response.replace(0, response.length(), Ui.printTaskNotFound());
        }
    }

    @Override
    public String getResponse() {
        return response.toString();
    }
}
