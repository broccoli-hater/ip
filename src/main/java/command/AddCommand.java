package command;

import task.Task;
import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * A command to add a task to the task list.
 * This command is responsible for adding a new task, displaying a confirmation message,
 * and saving the updated task list to storage.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command. This method adds the task to the task list,
     * displays a confirmation message, and saves the updated task list to storage.
     *
     * @param taskList The task list to which the task will be added.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.addTask();
        taskList.add(task);
        storage.saveData(taskList);
        System.out.print("    ");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        Ui.countTask(taskList.size());
    }
}
