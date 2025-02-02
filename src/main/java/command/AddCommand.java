package command;

import task.Task;
import task.TaskList;
import util.Ui;
import util.Storage;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

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
