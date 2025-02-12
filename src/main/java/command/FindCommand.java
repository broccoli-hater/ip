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
    private final StringBuilder response = new StringBuilder();

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
        TaskList matches = taskList.find(keyword);
        if (matches.isEmpty()) {
            response.append(Ui.printNoMatchingTasksFound());
        } else {
            assert !matches.isEmpty() : "Matching task list is found";
            response.append(Ui.findTask());
            response.append("\n");
            response.append(matches);
        }
    }

    @Override
    public String getResponse(){
        return response.toString();
    }
}
