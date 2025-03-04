package command;

import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * A command to list all tasks in the task list.
 * This command displays all tasks to the user or a message if the task list is empty.
 */
public class ListCommand extends Command {
    private final StringBuilder response = new StringBuilder();

    /**
     * Executes the list command. This method displays all tasks in the task list
     * or a message indicating that the task list is empty.
     *
     * @param taskList The task list containing the tasks to be displayed.
     * @param ui       The user interface to display the tasks or messages.
     * @param storage  The storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            response.append(Ui.countTask(0));
        } else {
            response.append(Ui.printList());
            response.append("\n");
            response.append(taskList);
        }
    }

    @Override
    public String getResponse() {
        return response.toString();
    }
}
