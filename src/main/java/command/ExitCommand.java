package command;

import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * A command to exit the application.
 * This command displays a farewell message to the user when executed.
 */
public class ExitCommand extends Command {
    private final StringBuilder response = new StringBuilder();

    /**
     * Executes the exit command. This method displays a farewell message to the user.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface to display the farewell message.
     * @param storage  The storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveData(taskList);
        response.append(Ui.sayBye());
    }

    @Override
    public String getResponse() {
        return response.toString();
    }
}
