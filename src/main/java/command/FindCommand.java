package command;

import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * A command to find tasks from the task list.
 * This command is responsible for finding any tasks that matches the keyword
 * from the task list and printing them out.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified task to be added.
     *
     * @param keyword The keyword to search the task list by.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command. This method finds and returns the task(s) in the
     * task list whose task description matches the keyword.
     *
     * @param taskList The task list to which the task will be added.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.findTask();
        taskList.find(keyword).print();
    }
}
