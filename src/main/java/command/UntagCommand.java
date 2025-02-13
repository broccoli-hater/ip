package command;

import task.Task;
import task.TaskList;
import util.Storage;
import util.Ui;

import java.util.ArrayList;

/**
 * A command to add a tag to a task in the task list.
 * In the form: tag {index} {#tag}
 */
public class UntagCommand extends Command {
    private final int index;
    private final ArrayList<String> tagList;
    private final StringBuilder response = new StringBuilder();

    /**
     * Constructs an TagCommand with the specified task.
     *
     * @param index The index of the task to which the tag is to be added.
     */
    public UntagCommand(int index, ArrayList<String> tagList) {
        this.index = index;
        this.tagList = tagList;
    }

    /**
     * Executes the tag command. This method adds a tag to a task in the task list,
     *
     * @param taskList The task list containing the task to be tagged.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        response.append(Ui.removeTags());
        response.append("\n");

        Task newTask = taskList.get(index);
        newTask.removeTags(tagList);
        taskList.set(index, newTask);
        storage.saveData(taskList);

        response.append(taskList.get(index).toString());
    }

    @Override
    public String getResponse(){
        return response.toString();
    }
}
