package command;

import task.TaskList;
import util.Ui;
import util.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.sayBye();
    }
}
