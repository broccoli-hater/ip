package command;

import task.TaskList;
import util.Ui;
import util.Storage;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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
