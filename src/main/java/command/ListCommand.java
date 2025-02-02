package command;

import task.TaskList;
import util.Ui;
import util.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            Ui.countTask(0);
        } else {
            Ui.printList();
            taskList.print();
        }
    }
}
